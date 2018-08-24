<?php

class Citizen {
	
	private $data;
	private $fitness;
	private $presentes = array(
		"0001" => 9,
		"0010" => 10,
		"0011" => 15,
		"0100" => 3,
		"0101" => 7,
		"0110" => 5,
		"0111" => 20,
		"1000" => 15,
		"1001" => 25,
		"1010" => 5,
		"1011" => 16,
		"1100" => 0,
		"1101" => 18,
		"1110" => 3
	);
	private $locais = array(
		"01" => 10,
		"10" => 15,
		"11" => 5
	);
	
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

			$presente = substr($this->data, 0, 4);
			$local = substr($this->data, -2);

			$this->setFitness($this->presentes[$presente] + $this->locais[$local]);
		} catch(Exception $e) {
			die($e->getMessage());
		}
	}
}