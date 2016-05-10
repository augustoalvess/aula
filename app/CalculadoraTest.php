<?php
include 'Calculadora.php';
class CalculadoraTest extends PHPUnit_Framework_TestCase {
    public function testSoma() {
        $calculadora = new Calculadora();
	$this->assertEquals(3, $calculadora->soma(1, 2));
    }
}
