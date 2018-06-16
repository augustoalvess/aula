/**
Somente contratos de alunos que estejam com sua situação atual entre os seguintes estados:
AJUSTE DE MATRÍCULA
MATRÍCULA
CONCLUSÃO DE TODAS AS DISCIPLINAS
PRÉ-MATRÍCULA
*/
COPY (
WITH instituicao AS (
         SELECT basPerson.*
           FROM basCompanyConf
INNER JOIN ONLY basPerson
	     ON basPerson.personId = basCompanyConf.personId
	  WHERE basCompanyConf.companyId = getParameter('BASIC', 'DEFAULT_COMPANY_CONF')::INT
),

contratos AS (
    SELECT *
      FROM (SELECT acdContract.personId,
		   acdMovementContract.contractId,
		   acdMovementContract.stateContractId,
		   acdStateContract.description,
		   acdStateContract.inOutTransition,
		   acdStateContract.isCloseContract,
		   acdMovementContract.stateTime,
		   MAX(acdMovementContract.stateTime) OVER (PARTITION BY acdMovementContract.contractId) AS maxStateTime
	      FROM acdMovementContract
        INNER JOIN acdStateContract
	        ON acdStateContract.stateContractId = acdMovementContract.stateContractId
	INNER JOIN acdContract
		ON acdContract.contractId = acdMovementContract.contractId
	     WHERE acdMovementContract.stateTime BETWEEN '2017-01-01'::DATE AND '2017-12-31'::DATE) mov
     WHERE mov.stateTime = mov.maxStateTime
       AND mov.inOutTransition = 'T'
       AND mov.stateContractId IN (28, 4, 9, 22)
     LIMIT 100
),

financeiro AS (
         SELECT DISTINCT 
	        contratos.contractId,
                ROUND(AVG(finEntry.value - COALESCE(beneficios.value, 0.00)) OVER (PARTITION BY contratos.contractId), 2) AS preco_medio,
                SUM((CASE WHEN finReceivableInvoice.balance = 0 AND pagamentos.value IS NOT NULL THEN 1 ELSE 0 END)) OVER (PARTITION BY contratos.contractId) AS quantidade_parcelas_quitadas,
		SUM((CASE WHEN finReceivableInvoice.balance = 0 AND pagamentos.value IS NOT NULL AND pagamentos.entryDate > finReceivableInvoice.maturityDate THEN 1 ELSE 0 END)) OVER (PARTITION BY contratos.contractId) AS quantidade_parcelas_quitadas_em_atrazo,
		SUM((CASE WHEN finReceivableInvoice.balance > 0 THEN 1 ELSE 0 END)) OVER (PARTITION BY contratos.contractId) AS quantidade_parcelas_em_aberto,
		SUM((CASE WHEN finReceivableInvoice.balance > 0 AND finReceivableInvoice.maturityDate < NOW()::DATE THEN 1 ELSE 0 END)) OVER (PARTITION BY contratos.contractId) AS quantidade_parcelas_em_aberto_atrazadas,
		SUM((CASE WHEN negociacoes.value > 0 THEN 1 ELSE 0 END)) OVER (PARTITION BY contratos.contractId) AS quantidade_parcelas_re_negociadas
           FROM contratos
INNER JOIN ONLY finReceivableInvoice
	     ON finReceivableInvoice.personId = contratos.personId
     INNER JOIN finEntry
	     ON finEntry.invoiceId = finReceivableInvoice.invoiceId
	    AND finEntry.contractId = contratos.contractId
	    AND finEntry.value > 0
	    AND finEntry.lancamentoEstornado IS FALSE
     INNER JOIN finOperation
	     ON finOperation.operationId = finEntry.operationId
	    AND finOperation.operationTypeId = 'D'
	    AND finOperation.operationId IN (1, 280, 300, 296, 297)
      LEFT JOIN finEntry beneficios
	     ON beneficios.invoiceId = finReceivableInvoice.invoiceId
	    AND beneficios.contractId = contratos.contractId
	    AND beneficios.value > 0
	    AND beneficios.lancamentoEstornado IS FALSE
	    AND beneficios.incentiveTypeId IS NOT NULL
      LEFT JOIN finEntry pagamentos
	     ON pagamentos.invoiceId = finReceivableInvoice.invoiceId
	    AND pagamentos.value > 0
	    AND pagamentos.lancamentoEstornado IS FALSE
	    AND pagamentos.operationId IN (30, 130, 242, 264, 266, 282, 376, 383, 389)
      LEFT JOIN finEntry negociacoes
	     ON negociacoes.invoiceId = finReceivableInvoice.invoiceId
	    AND negociacoes.value > 0
	    AND negociacoes.lancamentoEstornado IS FALSE
	    AND negociacoes.operationId IN (285)
          WHERE finReceivableInvoice.personId = contratos.personId
),

academico AS (
    SELECT DISTINCT semester.*,
                    SUM((CASE WHEN acdEnroll.statusId = 2 THEN 1 ELSE 0 END)) OVER (PARTITION BY semester.contractId) AS quantidade_disciplinas_aprovadas,
                    SUM((CASE WHEN acdEnroll.statusId = 3 THEN 1 ELSE 0 END)) OVER (PARTITION BY semester.contractId) AS quantidade_disciplinas_reprovadas_por_notas,
                    SUM((CASE WHEN acdEnroll.statusId = 4 THEN 1 ELSE 0 END)) OVER (PARTITION BY semester.contractId) AS quantidade_disciplinas_reprovadas_por_faltas,
                    SUM((CASE WHEN acdEnroll.statusId IN (5, 6) THEN 1 ELSE 0 END)) OVER (PARTITION BY semester.contractId) AS quantidade_disciplinas_canceladas,
		    COALESCE(AVG(acdEnroll.finalNote) OVER (PARTITION BY semester.contractId), 0) AS media_de_notas,
		    COALESCE(SUM(acdLearningPeriod.finalAverage) OVER (PARTITION BY semester.contractId), 0) AS media_de_notas_estipulada,
		    COALESCE(AVG(acdEnroll.frequency) OVER (PARTITION BY semester.contractId), 0) AS media_de_frequencia,
		    COALESCE(SUM(acdLearningPeriod.minimumFrequency) OVER (PARTITION BY semester.contractId), 0) AS media_de_frequencia_estipulada,
		    (acdTransferencia.transferenciaId IS NOT NULL) AS possui_transferencia_interna,
		    (protocolos.requestId IS NOT NULL) AS possui_protocolos_de_ajustes_de_matriculas
	       FROM (SELECT contratos.contractId,
	 	            get_semester_contract(contratos.contractId) AS semestre_atual
		       FROM contratos) semester
         INNER JOIN acdEnroll
                 ON acdEnroll.contractId = semester.contractId
         INNER JOIN acdGroup
	         ON acdGroup.groupId = acdEnroll.groupId
         INNER JOIN acdLearningPeriod
	         ON acdLearningPeriod.learningPeriodId = acdGroup.learningPeriodId
          LEFT JOIN acdTransferencia
	         ON acdTransferencia.contratoDeDestinoId = semester.contractId
          LEFT JOIN (SELECT ptcRequest.contractId,
                            ptcRequest.requestId,
                            MAX(ptcRequest.requestId) OVER (PARTITION BY ptcRequest.contractId) AS max_requestId
                       FROM ptcRequest
                 INNER JOIN ptcSubject
		         ON ptcSubject.subjectId = ptcRequest.subjectId
		      WHERE ptcRequest.contractId IS NOT NULL
		        AND ptcRequest.subjectId = -1) protocolos
	         ON protocolos.contractId = semester.contractId
	        AND protocolos.requestId = protocolos.max_requestId
)

	 SELECT COALESCE((EXTRACT(YEAR FROM age(CURRENT_DATE, basPhysicalPerson.dateBirth)) BETWEEN 16 AND 25)::INT, 1) AS idade_entre_16_e_25_anos,
		(basPhysicalPerson.sex = 'M')::INT AS sexo,
		COALESCE((basPhysicalPerson.maritalStatusId IN ('S', 'E'))::INT, 1) AS solteiro,
		COALESCE((basPhysicalPerson.ethnicOriginId = 1)::INT, 1) AS raca_branca,
		(instituicao.cityId = basPhysicalPerson.cityId)::INT AS mora_na_mesma_cidade,
		(basPhysicalPerson.carPlate IS NOT NULL)::INT AS possui_locomocao_propria,
		(basPhysicalPerson.specialNecessityId IS NOT NULL AND basPhysicalPerson.specialNecessityId <> 0)::INT AS possui_necessidade_especial,
		(basPhysicalPerson.dateDeath IS NOT NULL)::INT AS veio_a_obito,
		(basPhysicalPerson.workEmployerName IS NOT NULL)::INT AS esta_empregado,
		COALESCE((instituicao.cityId = basPhysicalPerson.cityIdWork)::INT, 1) AS trabalha_na_mesma_cidade_da_instituicao,
		COALESCE((basPhysicalPerson.cityId = basPhysicalPerson.cityIdWork)::INT, 1) trabalha_na_mesma_cidade_onde_mora,
		0 AS atua_na_area_do_curso, --Informação inexistente no sistema
		0 AS faixa_salarial_entre_800_e_1500, --Informação inexistente no sistema
		(COALESCE(financeiro.preco_medio, 0.00) BETWEEN 200 AND 600)::INT AS preco_medio_entre_200_e_600,
		(COALESCE(financeiro.preco_medio, 0.00) BETWEEN 601 AND 900)::INT AS preco_medio_entre_601_e_900,
		(COALESCE(financeiro.preco_medio, 0.00) >= 901)::INT AS preco_medio_superior_a_901,
		(COALESCE(financeiro.quantidade_parcelas_quitadas, 0) > 0)::INT AS possui_mensalidades_quitadas,
		(COALESCE(financeiro.quantidade_parcelas_quitadas_em_atrazo, 0) > 0)::INT AS possui_mensalidades_quitadas_em_atrazo,
		(COALESCE(financeiro.quantidade_parcelas_em_aberto, 0) > 0)::INT AS possui_mensalidades_em_aberto,
		(COALESCE(financeiro.quantidade_parcelas_em_aberto_atrazadas, 0) > 0)::INT AS possui_mensalidades_em_aberto_atrazadas,
		(COALESCE(financeiro.quantidade_parcelas_re_negociadas, 0) > 0)::INT AS possui_mensalidades_re_negociadas,
		(academico.semestre_atual BETWEEN 1 AND 4)::INT AS semestre_atual_entre_1_e_4,
		(academico.quantidade_disciplinas_aprovadas > 0)::INT AS possui_disciplinas_aprovadas,
		(academico.quantidade_disciplinas_reprovadas_por_notas > 0)::INT AS possui_disciplinas_reprovadas_por_notas,
		(academico.quantidade_disciplinas_reprovadas_por_faltas > 0)::INT AS possui_disciplinas_reprovadas_por_faltas,
		(academico.quantidade_disciplinas_canceladas > 0)::INT AS possui_disciplinas_canceladas,
		(academico.media_de_notas >= academico.media_de_notas_estipulada)::INT AS media_notas_maior_que_estipulado,
		(academico.media_de_frequencia >= academico.media_de_frequencia_estipulada)::INT AS media_frequencias_maior_que_estipulado,
		academico.possui_transferencia_interna::INT,
		academico.possui_protocolos_de_ajustes_de_matriculas::INT,
		0 AS evadido
	   FROM contratos
     INNER JOIN instituicao
             ON TRUE
     INNER JOIN acdContract
	     ON acdContract.contractId = contratos.contractId
INNER JOIN ONLY basPhysicalPerson
	     ON basPhysicalPerson.personId = acdContract.personId
      LEFT JOIN financeiro
	     ON financeiro.contractId = contratos.contractId
      LEFT JOIN academico
	     ON academico.contractId = contratos.contractId) TO '/home/augusto/Documentos/aula/topicos_especiais_em_computacao/dados_alunos_ativos.csv' WITH CSV;
	   
	    