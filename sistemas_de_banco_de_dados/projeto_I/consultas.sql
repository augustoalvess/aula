       SELECT retirada.id AS cod_retirada,
	      atendente.nome AS atendente,
	      usuario.nome AS retirante,
	      situacao_de_retirada.descricao AS situacao,
	      retirada.data_retirada,
	      data_devolucao_prevista,
	      data_devolucao
         FROM retirada
   INNER JOIN usuario AS atendente
	   ON atendente.id = retirada.atendente_id
   INNER JOIN usuario
           ON usuario.id = retirada.usuario_retirante_id
   INNER JOIN situacao_de_retirada
	   ON situacao_de_retirada.id = retirada.situacao_id