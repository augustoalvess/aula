DELIMITER $$
DROP PROCEDURE IF EXISTS inserelivro$$
CREATE PROCEDURE inserelivro (pcodeini INT, pcodemax INT, editora_id INT, edicao INT, data_publicacao DATE, local_publicacao VARCHAR(255))
BEGIN
    DECLARE qtd_total INT;
    DECLARE qtd_disponivel INT;

    SET @x = pcodeini;
    SET qtd_total = FLOOR(RAND() * 100);
    SET qtd_disponivel = FLOOR(RAND() * 100);      
        
    REPEAT
	INSERT INTO livro VALUES(id, editora_id, concat('LIVRO DE TESTE ', @x), edicao, data_publicacao, local_publicacao, qtd_total, qtd_disponivel);
        SET @x = @x + 1;
    UNTIL @x > pcodemax
    END REPEAT;
 
    SELECT pcodeini;
END$$
DELIMITER;


DELIMITER $$
DROP PROCEDURE IF EXISTS insereusuario$$
CREATE PROCEDURE insereusuario (pcodeini INT, pcodemax INT)
BEGIN
    SET @x = pcodeini;
        
    REPEAT
    INSERT INTO usuario VALUES (id, concat('USUÁRIO TESTE ', @x), concat('teste', @x, '@teste.com'), concat('user', @x), concat('user', @x), 1);
        SET @x = @x + 1;
    UNTIL @x > pcodemax
    END REPEAT;
 
    SELECT pcodeini;
END$$
DELIMITER;


DELIMITER $$
DROP PROCEDURE IF EXISTS insereretirada$$
CREATE PROCEDURE insereretirada (pcodeini INT, pcodemax INT)
BEGIN
    DECLARE atendente_id INT;
    DECLARE usuario_retirante_id INT;
    DECLARE situacao_id INT;
    DECLARE config_de_multa_id INT;
    DECLARE data DATE;

    SET @x = pcodeini;
    SET atendente_id = FLOOR(1 + (RAND() * 40));
    SET usuario_retirante_id = FLOOR(1 + (RAND() * 500000));
    SET situacao_id = FLOOR(1 + (RAND() * 200));
    SET config_de_multa_id = FLOOR(1 + (RAND() * 200));
        
    REPEAT
    SET data = (CURDATE() - INTERVAL FLOOR(RAND() * 50000) DAY);
    INSERT INTO retirada VALUES(id, atendente_id, usuario_retirante_id, situacao_id, config_de_multa_id, data, data, data, FLOOR(RAND() * 10));
        SET @x = @x + 1;
    UNTIL @x > pcodemax
    END REPEAT;
 
    SELECT pcodeini;
END$$
DELIMITER;