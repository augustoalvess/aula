<?php

require_once 'population.php';

class GA {

	private $banks = array();
	const MAX_GENERATIONS = 50000;
	const FITNESS_THRESHOLD = 0;

	public function __construct($banks = NULL) {
		try {
			if (is_null($banks)) {
				throw new Exception('Erro, não foi especificado # de bancos');
			}
			$this->init($banks);
		} catch(Exception $e) {
			die($e->getMessage());
		}
	}
	
	private function init($banks) {
		for ($x=0; $x<$banks; $x++) {
			$this->banks[$x] = new Population();
		}
	}
	
	public function getBank($bank = NULL) {
		try {
			if (is_null($bank)) {
				throw new Exception('Erro, falta especificar banco');
			}
			return $this->banks[$bank];
		} catch(Exception $e) {
			die($e->getMessage());
		}
	}
	
	public function getBestCandidates($bank = NULL) {
		try {
			if (is_null($bank)) {
				throw new Exception('Erro, falta especificar banco');
			}
			return $this->banks[$bank]->getBestCitizens();
		} catch(Exception $e) {
			die($e->getMessage());
		}
	}
	
	public function setObjective($bank = NULL, $objective = NULL) {
		try {
			if (is_null($bank) || is_null($objective)) {
				throw new Exception('Erro, falta especificar banco ou objetivo');
			}
			$this->banks[$bank]->setObjective($objective);
		} catch(Exception $e) {
			die($e->getMessage());
		}
	}
	
	public function startUp($bank = NULL) {
		try {
			if (is_null($bank)) {
				throw new Exception('Erro, falta especificar banco');
			}
			$this->banks[$bank]->startUp();
			$this->banks[$bank]->calculateFitness();
			$this->banks[$bank]->assignBestCitizens();
		} catch(Exception $e) {
			die($e->getMessage());
		}
	}
	
	public function isFinished($bank = NULL, $fitness_threshold = 0){
		try {
			if (is_null($bank)) {
				throw new Exception('Error, falta especificar banco');
			}
			$bests = $this->getBestCandidates($bank);
			$can0 = $bests[0]->getFitness();
			return ($can0 == $fitness_threshold);
		} catch(Exception $e) {
			die($e->getMessage());
		}
	}
	
	function microtime_float() {
	    list($useg, $seg) = explode(" ", microtime());
	    return ((float)$useg + (float)$seg);
  	}
	
	public function start($bank = NULL) {
		try {
			if (is_null($bank)) {
				throw new Exception('Erro, falta especificar banco');
			}
			//BUCLE HASTA ENCONTRAR EL MEJOR CANDIDATO
			$end = FALSE;
			$generation = 0;
			$stime = $this->microtime_float();
			
			echo "<table class='table table-striped table-bordered table-hover'>";
			echo "<thead><tr>";
			echo "<th class = 'success'>GERAÇÃO</th>";
			echo "<th class = 'success'>MELHOR CANDIDATO</th>";
			echo "<th class = 'success'>FITNESS</th>";
			echo "</tr></thead><tbody>";
			
			while ($end == FALSE && $generation < self::MAX_GENERATIONS) {
				// Criamos candidatos dos dois melhores candidatos da geração anterior
				$this->banks[$bank]->reproduce();

				// Nós mutamos candidatos da geração atual
				$this->banks[$bank]->mutate();

				// Avaliamos os candidatos e estabelecemos os dois melhores desta geração
				$this->banks[$bank]->calculateFitness();
				$this->banks[$bank]->assignBestCitizens();

				// Imprimimos na tela os dois melhores candidatos desta geração
				$bests = $this->banks[$bank]->getBestCitizens();
				
				$i = 0;
				foreach ($bests as $b) {
				  ++$i;
				  echo "<tr><td>$generation</td><td>" . $b->getData() . "</td><td>" . $b->getFitness() . "</td>";
				}

				$generation++;
				$end = $this->isFinished(0, self::FITNESS_THRESHOLD);
			}
			echo "</tbody></table><br /><br />";
			$ftime = $this->microtime_float();
			echo "Tempo de evolução : " . ($ftime - $stime) . ' segundos' . PHP_EOL . PHP_EOL;
			return $this->banks[$bank]->getBestCitizens();
		} catch(Exception $e) {
			die($e->getMessage());
		}
	}
}