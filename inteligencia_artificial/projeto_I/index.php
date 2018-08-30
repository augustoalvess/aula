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

// Legenda

// PRESENTEADOR
// Homem 1
// Mulher 0





// MELHOR PRESENTE PARA DAR PARA A NAMORADA NOS DIAS DOS NAMORADOS.

// PONTUAÇÃO DOS PRESENTES
// Flor                    9	0001
// Chocolate              10	0010
// Anel                   15	0011
// Meia                    3	0100
// Vestido                 7	0101
// Lingerie                5	0110
// Sapato 				  20	0111
// Bolsa 				  15	1000
// Viagem 				  25	1001
// Cinema 				   5	1010
// Pote 				  16	1011
// Anjinho 				   0	1100
// Roupa 				  18	1101
// Pijama 				   3	1110

// PONTUAÇÃO DO LOCAL
// Em casa                10	01
// Em um jantar           15	10
// No trabalho             5	11

// PONTUAÇÃO DO HORÁRIO
// Pela manhã			  10    001
// Meio do dia            5     010
// A tarde                15	011
// A noite                20	100
// De madrugada           3		101

// PONTUAÇÃO DO STATUS
// De bom humor           20	001
// De mal humor           5     010
// TPM                    7     011
// Concentrada            10    100
// Precionada             5     101
// Séria                  9     110
// Reconciliação          15	111


$ga->setObjective(0, '000000000000');

// Os cromossomos devem ser gerados aleatóriamentes baseados nos critérios.
// Baseado em presente já dados, verificar quais foram os melhores presentes.
// Obter o melhor presente do mundo.



// Adaptar o métdodo de cálculo de fitnes e não atribuir uma solução melhor, mas sim executar até x gerações.





// Inicializamos o objetivo
$ga->startUp(0);

// Iniciamos a evolução
$bests = $ga->start(0);


