START TRANSACTION [WITH CONSISTENT SNAPSHOT]
BEGIN [WORK]
COMMIT [WORK] [AND [NO] CHAIN] [[NO] RELEASE]
ROLLBACK [WORK] [AND [NO] CHAIN] [[NO] RELEASE]
SET autocommit = {0 | 1}

START  TRANSACTION; 
SELECT  @ A : = SUM ( salário )  FROM  table1  ONDE  tipo = 1; 
UPDATE  table2  SET  resumo = @ A  ONDE  tipo = 1; 
COMMIT;

BEGIN; 
SELECT  @ A : = SUM ( salário )  FROM  table1  ONDE  tipo = 1; 
UPDATE  table2  SET  resumo = @ A  ONDE  tipo = 1; 
ROLLBACK;

SET autocommit = 0;


