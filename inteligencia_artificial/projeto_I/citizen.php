<?php

class Citizen {
	
	private $data;
	private $fitness;
	
	public function __construct() {
		$this->data = '';
		$this->fitness = -1;
	}
	
	public function getData() {
		return $this->data;
	}
	
	public function setData($newData = NULL) {
		try {
			if (!is_null($newData)) {
				$this->data = $newData;
			}
		} catch(Exception $e) {
			die($e->getMessage());
		}
	}
	
	public function getFitness() {
		return $this->fitness;
	}
	
	public function setFitness($newFitness = NULL) {
		try {
			if (!is_null($newFitness)) {
				$this->fitness = $newFitness;
			}
		} catch(Exception $e) {
			die($e->getMessage());
		}
	}
	
	public function calculateFitness($objective = NULL) {
		try {
			if (is_null($objective)) {
				throw new Exception('Erro, não foi especificado um objetivo');
			}
			
			$fitness = 0;
			// Basicamente, passamos por todos os elementos da matriz e obtemos o valor
			// ascii do caractere atual mutado ... e do caractere objetivo ... e então os subtraímos.
			// Obviamente, quanto mais nos aproximamos da cadeia alvo ... quanto menor
			// valor de fitness porque toda vez estaremos mais próximos da cadeia alvo e
			// haverá menos diferença entre a subtração do ascii() atual do objetivo
			for ($x=0;$x<strlen($objective);$x++) {
				//echo $objective[$x] . "   -   " . $this->data[$x] . "   -   " . abs(ord($objective[$x]) - ord($this->data[$x])) . "   -   " . $fitness . "<br />";
				$fitness += abs(ord($objective[$x]) - ord($this->data[$x]));
			}
			
			$this->setFitness($fitness);
		} catch(Exception $e) {
			die($e->getMessage());
		}
	}
}