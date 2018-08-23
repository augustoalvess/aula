<?php

ini_set('max_execution_time', 300); // 300 segundos = 5 minutos

require_once './ga.php';

echo '
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
';

// Criamos um novo objeto GA
$ga = new GA(1);

// Estabelecemos o objetivo
// O objetivo é atingir uma cadeia de 30 números 1
$ga->setObjective(0, '111111111111111111111111111111');

// Inicializamos o objetivo
$ga->startUp(0);

// Iniciamos a evolução
$bests = $ga->start(0);