<?php
error_reporting(E_ERROR | E_PARSE);

require_once __DIR__ . '/php-voldemort/vendor/autoload.php';
require_once __DIR__ . '/php-voldemort/src/voldemort-client.php';

$conn = new PDO('mysql:host=localhost;dbname=biblioteca_sbd', 'root', 'root');

$result = $conn->query("
	 SELECT usuario.id AS cod_usuario,
          usuario.nome AS usuario,
          usuario.email,
          usuario.login,
          usuario.senha,
          usuario.ativo
     FROM usuario
");

$voldemort = \Voldemort::create(array(array("host" => "localhost", "port" => "6666")), 'test');

$dados = "[";
foreach ($result as $row) {
	$dados .= <<<EOQ
{
    \"cod_usuario\": \"{$row['cod_usuario']}\",
    \"usuario\": \"{$row['usuario']}\",
    \"email\": \"{$row['email']}\",
    \"login\": \"{$row['login']}\",
    \"senha\": \"{$row['senha']}\",
    \"ativo\": \"{$row['ativo']}\"
},
EOQ;
}
$dados = rtrim($dados, ",") . "]";

echo $dados;
$response = $voldemort->put("usuarios", $dados);
if ($response->hasError()) {
	echo $response->getError()->getErrorMessage();
}

echo "\r\n";