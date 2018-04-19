<?php
error_reporting(E_ERROR | E_PARSE);

require_once __DIR__ . '/php-voldemort/vendor/autoload.php';
require_once __DIR__ . '/php-voldemort/src/voldemort-client.php';

$conn = new PDO('mysql:host=localhost;dbname=biblioteca_sbd', 'root', 'root');

$result = $conn->query("
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
");

$voldemort = \Voldemort::create(array(array("host" => "localhost", "port" => "6666")), 'test');

$dados = "[";
foreach ($result as $row) {
	$dados .= <<<EOQ
{
    \"cod_multa\": \"{$row['cod_multa']}\",
    \"cod_retirada\": \"{$row['cod_retirada']}\",
    \"atendente\": \"{$row['atendente']}\",
    \"retirante\": \"{$row['retirante']}\",
    \"situacao\": \"{$row['situacao']}\",
    \"data_retirada\": \"{$row['data_retirada']}\",
    \"data_devolucao_prevista\": \"{$row['data_devolucao_prevista']}\",
    \"data_devolucao\": \"{$row['data_devolucao']}\",
    \"cod_livro\": \"{$row['cod_livro']}\",
    \"livro\": \"{$row['livro']}\",
    \"valor\": \"{$row['valor']}\",
    \"data_emissao\": \"{$row['data_emissao']}\",
    \"data_vencimento\": \"{$row['data_vencimento']}\",
    \"data_pagamento\": \"{$row['data_pagamento']}\",
    \"valor_pagamento\": \"{$row['valor_pagamento']}\",
    \"saldo\": \"{$row['saldo']}\",
    \"cancelada\": \"{$row['cancelada']}\",
},
EOQ;
}
$dados = rtrim($dados, ",") . "]";

echo $dados;
$response = $voldemort->put("multas", $dados);
if ($response->hasError()) {
	echo $response->getError()->getErrorMessage();
}

echo "\r\n";