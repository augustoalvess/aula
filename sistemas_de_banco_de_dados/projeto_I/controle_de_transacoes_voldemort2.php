<?php
error_reporting(E_ERROR | E_PARSE);

require_once __DIR__ . '/php-voldemort/vendor/autoload.php';
require_once __DIR__ . '/php-voldemort/src/voldemort-client.php';

$voldemort = \Voldemort::create(array(array("host" => "localhost", "port" => "6666")), 'test');

$livro = <<<EOQ
{
    'cod_livro': 1,
    'cod_editora': 1,
    'editora': 'Editora teste',
    'livro': 'Bla bla',
    'edicao': '2',
    'data_publicacao': '10/01/1992',
    'local_publicacao': 'São Paulo',
    'qtd_total': 5,
    'qtd_disponivel': 5,
    'genero': 'Ação',
    'autor': 'João'
}
EOQ;
echo $livro;

$response = $voldemort->put("1", $livro);
if ($response->hasError()) {
	echo $response->getError()->getErrorMessage();
}

echo "\r\n";