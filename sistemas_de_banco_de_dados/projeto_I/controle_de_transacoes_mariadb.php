<?php
error_reporting(E_ERROR | E_PARSE);

$conn = new PDO('mysql:host=localhost;dbname=biblioteca_sbd', 'root');

$conn->beginTransaction();
try {
	$ok = $conn->exec("UPDATE livro SET nome = 'TESTE' WHERE id = 1");
	//$ok = $conn->exec("UPDATE livro SET id = 'TESTE' WHERE id = 1");
	if (!$ok) {
		throw new Exception("Erro\n");
	}
	$conn->commit();
} catch (Exception $err) {
	echo $err->getMessage();
	$conn->rollBack();
}