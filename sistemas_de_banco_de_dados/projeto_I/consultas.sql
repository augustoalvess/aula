--Consulta totas as retiradas
       SELECT retirada.id AS cod_retirada,
	            atendente.nome AS atendente,
	            usuario.nome AS retirante,
	            situacao_de_retirada.descricao AS situacao,
	            retirada.data_retirada,
	            retirada.data_devolucao_prevista,
	            retirada.data_devolucao
         FROM retirada
   INNER JOIN usuario AS atendente
	         ON atendente.id = retirada.atendente_id
   INNER JOIN usuario
           ON usuario.id = retirada.usuario_retirante_id
   INNER JOIN situacao_de_retirada
	         ON situacao_de_retirada.id = retirada.situacao_id;

--Consulta todas as multas
       SELECT multa_da_retirada.id AS cod_multa,
	            retirada.id AS cod_retirada,
	            atendente.nome AS atendente,
	            usuario.nome AS retirante,
	            situacao_de_retirada.descricao AS situacao,
	            retirada.data_retirada,
	            retirada.data_devolucao_prevista,
	            retirada.data_devolucao,
	            livro.id AS cod_livro,
	            livro.nome AS livro,
	            multa_da_retirada.valor,
	            multa_da_retirada.data_emissao,
	            multa_da_retirada.data_vencimento,
	            multa_da_retirada.data_pagamento,
	            multa_da_retirada.valor_pagamento,
	            multa_da_retirada.saldo,
	            multa_da_retirada.cancelada
         FROM multa_da_retirada
   INNER JOIN retirada
           ON retirada.id = multa_da_retirada.retirada_id
   INNER JOIN usuario AS atendente
	         ON atendente.id = retirada.atendente_id
   INNER JOIN usuario
           ON usuario.id = retirada.usuario_retirante_id
   INNER JOIN situacao_de_retirada
	         ON situacao_de_retirada.id = retirada.situacao_id
   INNER JOIN livro
	         ON livro.id = multa_da_retirada.livro_id;

--Consulta todos os livros
       SELECT livro.id AS cod_livro,
              editora.id AS cod_editora,
              editora.nome AS editora,
              livro.nome AS livro,
              livro.edicao,
              livro.data_publicacao,
              livro.local_publicacao,
              livro.qtd_total,
              livro.qtd_disponivel,
              group_concat(genero.descricao SEPARATOR ', ') AS genero,
              group_concat(autor.nome SEPARATOR ', ') AS autor
         FROM livro
   INNER JOIN editora
	         ON editora.id = livro.editora_id
    LEFT JOIN genero_do_livro
           ON genero_do_livro.livro_id = livro.id
    LEFT JOIN genero
	         ON genero.id = genero_do_livro.genero_id
    LEFT JOIN autor_do_livro
	         ON autor_do_livro.livro_id = livro.id
    LEFT JOIN autor
	         ON autor.id = autor_do_livro.autor_id
     GROUP BY livro.id,
              editora.id,
              editora.nome,
              livro.nome,
              livro.edicao,
              livro.data_publicacao,
              livro.local_publicacao,
              livro.qtd_total,
              livro.qtd_disponivel;

--Consulta todos os usuários
       SELECT usuario.id AS cod_usuario,
              usuario.nome AS usuario,
              usuario.email,
              usuario.login,
              usuario.senha,
              usuario.ativo
         FROM usuario;

--Consulta somente as retiradas que tiveram suas devoluções atrasadas
       SELECT retirada.id AS cod_retirada,
              atendente.nome AS atendente,
              usuario.nome AS retirante,
              situacao_de_retirada.descricao AS situacao,
              retirada.data_retirada,
              retirada.data_devolucao_prevista,
              retirada.data_devolucao
         FROM retirada
   INNER JOIN usuario AS atendente
           ON atendente.id = retirada.atendente_id
   INNER JOIN usuario
           ON usuario.id = retirada.usuario_retirante_id
   INNER JOIN situacao_de_retirada
           ON situacao_de_retirada.id = retirada.situacao_id
        WHERE retirada.data_devolucao > retirada.data_devolucao_prevista;


--Consulta somente as multas que foram pagas em atraso
       SELECT multa_da_retirada.id AS cod_multa,
              retirada.id AS cod_retirada,
              atendente.nome AS atendente,
              usuario.nome AS retirante,
              situacao_de_retirada.descricao AS situacao,
              retirada.data_retirada,
              retirada.data_devolucao_prevista,
              retirada.data_devolucao,
              livro.id AS cod_livro,
              livro.nome AS livro,
              multa_da_retirada.valor,
              multa_da_retirada.data_emissao,
              multa_da_retirada.data_vencimento,
              multa_da_retirada.data_pagamento,
              multa_da_retirada.valor_pagamento,
              multa_da_retirada.saldo,
              multa_da_retirada.cancelada
         FROM multa_da_retirada
   INNER JOIN retirada
           ON retirada.id = multa_da_retirada.retirada_id
   INNER JOIN usuario AS atendente
           ON atendente.id = retirada.atendente_id
   INNER JOIN usuario
           ON usuario.id = retirada.usuario_retirante_id
   INNER JOIN situacao_de_retirada
           ON situacao_de_retirada.id = retirada.situacao_id
   INNER JOIN livro
           ON livro.id = multa_da_retirada.livro_id      
        WHERE multa_da_retirada.data_pagamento > multa_da_retirada.data_vencimento;

--Consulta somente os livros que nunca foram retirados, com EXISTS
       SELECT livro.id AS cod_livro,
              editora.id AS cod_editora,
              editora.nome AS editora,
              livro.nome AS livro,
              livro.edicao,
              livro.data_publicacao,
              livro.local_publicacao,
              livro.qtd_total,
              livro.qtd_disponivel,
              group_concat(genero.descricao SEPARATOR ', ') AS genero,
              group_concat(autor.nome SEPARATOR ', ') AS autor
         FROM livro
   INNER JOIN editora
           ON editora.id = livro.editora_id
    LEFT JOIN genero_do_livro
           ON genero_do_livro.livro_id = livro.id
    LEFT JOIN genero
           ON genero.id = genero_do_livro.genero_id
    LEFT JOIN autor_do_livro
           ON autor_do_livro.livro_id = livro.id
    LEFT JOIN autor
           ON autor.id = autor_do_livro.autor_id
        WHERE NOT EXISTS (SELECT retirada_id
                        FROM livro_da_retirada
                       WHERE livro_da_retirada.livro_id = livro.id
                       LIMIT 1)
     GROUP BY livro.id,
              editora.id,
              editora.nome,
              livro.nome,
              livro.edicao,
              livro.data_publicacao,
              livro.local_publicacao,
              livro.qtd_total,
              livro.qtd_disponivel;


--Consulta somente os livros que nunca foram retirados, com IN
       SELECT livro.id AS cod_livro,
              editora.id AS cod_editora,
              editora.nome AS editora,
              livro.nome AS livro,
              livro.edicao,
              livro.data_publicacao,
              livro.local_publicacao,
              livro.qtd_total,
              livro.qtd_disponivel,
              group_concat(genero.descricao SEPARATOR ', ') AS genero,
              group_concat(autor.nome SEPARATOR ', ') AS autor
         FROM livro
   INNER JOIN editora
           ON editora.id = livro.editora_id
    LEFT JOIN genero_do_livro
           ON genero_do_livro.livro_id = livro.id
    LEFT JOIN genero
           ON genero.id = genero_do_livro.genero_id
    LEFT JOIN autor_do_livro
           ON autor_do_livro.livro_id = livro.id
    LEFT JOIN autor
           ON autor.id = autor_do_livro.autor_id
        WHERE livro.id NOT IN (SELECT DISTINCT livro_id
                                      FROM livro_da_retirada)
     GROUP BY livro.id,
              editora.id,
              editora.nome,
              livro.nome,
              livro.edicao,
              livro.data_publicacao,
              livro.local_publicacao,
              livro.qtd_total,
              livro.qtd_disponivel;

--Consulta somente os livros cujo gênero seja AÇÃO ou TERROR com IN
       SELECT livro.id AS cod_livro,
              editora.id AS cod_editora,
              editora.nome AS editora,
              livro.nome AS livro,
              livro.edicao,
              livro.data_publicacao,
              livro.local_publicacao,
              livro.qtd_total,
              livro.qtd_disponivel,
              group_concat(genero.descricao SEPARATOR ', ') AS genero,
              group_concat(autor.nome SEPARATOR ', ') AS autor
         FROM livro
   INNER JOIN editora
           ON editora.id = livro.editora_id
    LEFT JOIN genero_do_livro
           ON genero_do_livro.livro_id = livro.id
    LEFT JOIN genero
           ON genero.id = genero_do_livro.genero_id
    LEFT JOIN autor_do_livro
           ON autor_do_livro.livro_id = livro.id
    LEFT JOIN autor
           ON autor.id = autor_do_livro.autor_id
        WHERE genero.descricao IN ('AÇÃO', 'TERROR')
     GROUP BY livro.id,
              editora.id,
              editora.nome,
              livro.nome,
              livro.edicao,
              livro.data_publicacao,
              livro.local_publicacao,
              livro.qtd_total,
              livro.qtd_disponivel;

--Consulta somente os livros cujo gênero seja AÇÃO ou TERROR com UNION
       SELECT livro.id AS cod_livro,
              editora.id AS cod_editora,
              editora.nome AS editora,
              livro.nome AS livro,
              livro.edicao,
              livro.data_publicacao,
              livro.local_publicacao,
              livro.qtd_total,
              livro.qtd_disponivel,
              group_concat(genero.descricao SEPARATOR ', ') AS genero,
              group_concat(autor.nome SEPARATOR ', ') AS autor
         FROM livro
   INNER JOIN editora
           ON editora.id = livro.editora_id
    LEFT JOIN genero_do_livro
           ON genero_do_livro.livro_id = livro.id
    LEFT JOIN genero
           ON genero.id = genero_do_livro.genero_id
    LEFT JOIN autor_do_livro
           ON autor_do_livro.livro_id = livro.id
    LEFT JOIN autor
           ON autor.id = autor_do_livro.autor_id
        WHERE genero.descricao = 'AÇÃO'
     GROUP BY livro.id,
              editora.id,
              editora.nome,
              livro.nome,
              livro.edicao,
              livro.data_publicacao,
              livro.local_publicacao,
              livro.qtd_total,
              livro.qtd_disponivel
        UNION 
       SELECT livro.id AS cod_livro,
              editora.id AS cod_editora,
              editora.nome AS editora,
              livro.nome AS livro,
              livro.edicao,
              livro.data_publicacao,
              livro.local_publicacao,
              livro.qtd_total,
              livro.qtd_disponivel,
              group_concat(genero.descricao SEPARATOR ', ') AS genero,
              group_concat(autor.nome SEPARATOR ', ') AS autor
         FROM livro
   INNER JOIN editora
           ON editora.id = livro.editora_id
    LEFT JOIN genero_do_livro
           ON genero_do_livro.livro_id = livro.id
    LEFT JOIN genero
           ON genero.id = genero_do_livro.genero_id
    LEFT JOIN autor_do_livro
           ON autor_do_livro.livro_id = livro.id
    LEFT JOIN autor
           ON autor.id = autor_do_livro.autor_id
        WHERE genero.descricao = 'TERROR'
     GROUP BY livro.id,
              editora.id,
              editora.nome,
              livro.nome,
              livro.edicao,
              livro.data_publicacao,
              livro.local_publicacao,
              livro.qtd_total,
              livro.qtd_disponivel;

--Consulta a quantidade de livros de TERROR já retirados até agora com IN
SELECT DISTINCT retirada.id AS cod_retirada,
              atendente.nome AS atendente,
              usuario.nome AS retirante,
              situacao_de_retirada.descricao AS situacao,
              retirada.data_retirada,
              retirada.data_devolucao_prevista,
              retirada.data_devolucao
         FROM retirada
   INNER JOIN usuario AS atendente
           ON atendente.id = retirada.atendente_id
   INNER JOIN usuario
           ON usuario.id = retirada.usuario_retirante_id
   INNER JOIN situacao_de_retirada
           ON situacao_de_retirada.id = retirada.situacao_id
   INNER JOIN livro_da_retirada
           ON livro_da_retirada.retirada_id = retirada.id
   INNER JOIN genero_do_livro
           ON genero_do_livro.livro_id = livro_da_retirada.livro_id
   INNER JOIN genero
           ON genero.id = genero_do_livro.genero_id
        WHERE genero.descricao IN ('AÇÃO', 'TERROR');


--Consulta a quantidade de livros de AÇÃO e TERROR já retirados até agora com UNION
SELECT DISTINCT retirada.id AS cod_retirada,
              atendente.nome AS atendente,
              usuario.nome AS retirante,
              situacao_de_retirada.descricao AS situacao,
              retirada.data_retirada,
              retirada.data_devolucao_prevista,
              retirada.data_devolucao
         FROM retirada
   INNER JOIN usuario AS atendente
           ON atendente.id = retirada.atendente_id
   INNER JOIN usuario
           ON usuario.id = retirada.usuario_retirante_id
   INNER JOIN situacao_de_retirada
           ON situacao_de_retirada.id = retirada.situacao_id
   INNER JOIN livro_da_retirada
           ON livro_da_retirada.retirada_id = retirada.id
   INNER JOIN genero_do_livro
           ON genero_do_livro.livro_id = livro_da_retirada.livro_id
   INNER JOIN genero
           ON genero.id = genero_do_livro.genero_id
        WHERE genero.descricao = 'AÇÃO'
        UNION
SELECT DISTINCT retirada.id AS cod_retirada,
              atendente.nome AS atendente,
              usuario.nome AS retirante,
              situacao_de_retirada.descricao AS situacao,
              retirada.data_retirada,
              retirada.data_devolucao_prevista,
              retirada.data_devolucao
         FROM retirada
   INNER JOIN usuario AS atendente
           ON atendente.id = retirada.atendente_id
   INNER JOIN usuario
           ON usuario.id = retirada.usuario_retirante_id
   INNER JOIN situacao_de_retirada
           ON situacao_de_retirada.id = retirada.situacao_id
   INNER JOIN livro_da_retirada
           ON livro_da_retirada.retirada_id = retirada.id
   INNER JOIN genero_do_livro
           ON genero_do_livro.livro_id = livro_da_retirada.livro_id
   INNER JOIN genero
           ON genero.id = genero_do_livro.genero_id
        WHERE genero.descricao = 'TERROR';
