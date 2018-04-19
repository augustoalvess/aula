<?php
error_reporting(E_ERROR | E_PARSE);

require_once __DIR__ . '/php-voldemort/vendor/autoload.php';
require_once __DIR__ . '/php-voldemort/src/voldemort-client.php';

$voldemort = \Voldemort::create(array(array("host" => "localhost", "port" => "6666")), 'test');

$start = microtime(true);
$livros = json_decode(str_replace(array(" ", "\\"), "", $voldemort->get('livros')));

$livrosAcaoTerror = array_filter($livros, function($livro) {
    return in_array($livro->genero, array("AÇÃO", "TERROR"));
});
$end = microtime(true);

echo var_export($livrosAcaoTerror);
echo "\r\n";
echo $end - $start;
echo "\r\n";