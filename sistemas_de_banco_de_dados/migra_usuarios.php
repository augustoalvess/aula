<?php

$conn = new PDO('mysql:host=localhost;dbname=biblioteca_sbd', 'root', 'root');

$result = $conn->query("
	 SELECT id, 
	        nome, 
	        email, 
	        login, 
	        senha, 
	        ativo
       FROM usuario
      LIMIT 1
");

$fp = fsockopen("tcp://localhost", 6666, $errno, $errstr, 30);

if (!$fp) {
	echo "$errno, $errstr\n";
} else {
	foreach ($result as $row) {
		/**
		$command = "\"usuario_{$row['id']}\" \"{\\\"nome\\\":\\\"{$row['nome']}\\\",\\\"email\\\":\\\"{$row['email']}\\\",\\\"login\\\":\\\"{$row['login']}\\\",\\\"senha\\\":\\\"{$row['senha']}\\\",\\\"ativo\\\":\\\"{$row['ativo']}\\\"}\"";
		*/

		fputs($fp, "put \"aham\" \"1234\"");
		while (!feof($fp)) {
			echo fgets($fp, 2048);
		}
	}
}
fclose($fp);