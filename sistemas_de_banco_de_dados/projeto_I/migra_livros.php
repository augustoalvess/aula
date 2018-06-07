<?php
error_reporting(E_ERROR | E_PARSE);

require_once __DIR__ . '/php-voldemort/vendor/autoload.php';
require_once __DIR__ . '/php-voldemort/src/voldemort-client.php';

$conn = new PDO('mysql:host=localhost;dbname=biblioteca_sbd', 'root', 'root');

$result = $conn->query("
	 SELECT livro.id AS cod_livro,
            editora.id AS cod_editora,
            editora.nome AS editora,
            livro.nome AS livro,
            livro.edicao,
            livro.data_publicacao,
            livro.local_publicacao,
            livro.qtd_total,
            livro.qtd_disponivel,
            group_concat(genero.descricao SEPARATOR ', ') AS genero,
            group_concat(autor.nome SEPARATOR ', ') AS autor
       FROM livro
 INNER JOIN editora
         ON editora.id = livro.editora_id
  LEFT JOIN genero_do_livro
         ON genero_do_livro.livro_id = livro.id
  LEFT JOIN genero
         ON genero.id = genero_do_livro.genero_id
  LEFT JOIN autor_do_livro
         ON autor_do_livro.livro_id = livro.id
  LEFT JOIN autor
         ON autor.id = autor_do_livro.autor_id
   GROUP BY livro.id,
            editora.id,
            editora.nome,
            livro.nome,
            livro.edicao,
            livro.data_publicacao,
            livro.local_publicacao,
            livro.qtd_total,
            livro.qtd_disponivel
");

$voldemort = \Voldemort::create(array(array("host" => "localhost", "port" => "6666")), 'test');

$dados = "[";
foreach ($result as $row) {
	$dados .= <<<EOQ
{
    \"cod_livro\": \"{$row['cod_livro']}\",
    \"cod_editora\": \"{$row['cod_editora']}\",
    \"editora\": \"{$row['editora']}\",
    \"livro\": \"{$row['livro']}\",
    \"edicao\": \"{$row['edicao']}\",
    \"data_publicacao\": \"{$row['data_publicacao']}\",
    \"local_publicacao\": \"{$row['local_publicacao']}\",
    \"qtd_total\": \"{$row['qtd_total']}\",
    \"qtd_disponivel\": \"{$row['qtd_disponivel']}\",
    \"genero\": \"{$row['genero']}\",
    \"autor\": \"{$row['autor']}\"
},
EOQ;
}
$dados = rtrim($dados, ",") . "]";

echo $dados;
$response = $voldemort->put("livros", $dados);
if ($response->hasError()) {
	echo $response->getError()->getErrorMessage();
}

echo "\r\n";













{
    'cod_livro': 1, 
    'cod_editora': 1, 
    'editora': 'Editora teste',
    'livro': 'Teste',
    'edicao': '2',
    'data_publicacao': '10/01/1992',
    'local_publicacao': 'São Paulo',
    'qtd_total': '5',
    'qtd_disponivel': '5',
    'genero': 'Ação',
    'autor': 'João'
}