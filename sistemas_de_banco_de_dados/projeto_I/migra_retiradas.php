<?php
error_reporting(E_ERROR | E_PARSE);

require_once __DIR__ . '/php-voldemort/vendor/autoload.php';
require_once __DIR__ . '/php-voldemort/src/voldemort-client.php';

$conn = new PDO('mysql:host=localhost;dbname=biblioteca_sbd', 'root', 'root');

$result = $conn->query("
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
");

$voldemort = \Voldemort::create(array(array("host" => "localhost", "port" => "6666")), 'test');
//echo $voldemort->get('aham');

$dados = "[";
foreach ($result as $row) {
	$dados .= <<<EOQ
{
    \"cod_retirada\": \"{$row['cod_retirada']}\",
    \"atendente\": \"{$row['atendente']}\",
    \"retirante\": \"{$row['retirante']}\",
    \"situacao\": \"{$row['situacao']}\",
    \"data_retirada\": \"{$row['data_retirada']}\",
    \"data_devolucao_prevista\": \"{$row['data_devolucao_prevista']}\",
    \"data_devolucao\": \"{$row['data_devolucao']}\"
},
EOQ;
}
$dados = rtrim($dados, ",") . "]";

echo $dados;
$response = $voldemort->put("retiradas", $dados);
if ($response->hasError()) {
	echo $response->getError()->getErrorMessage();
}

echo "\r\n";