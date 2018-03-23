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
	INSERT INTO livro VALUES(id, editora_id, 'LIVRO DE TESTE ' + @x, edicao, data_publicacao, local_publicacao, qtd_total, qtd_disponivel);
        SET @x = @x + 1;
    UNTIL @x > pcodemax
    END REPEAT;
 
    SELECT pcodeini;
END$$
DELIMITER ;
