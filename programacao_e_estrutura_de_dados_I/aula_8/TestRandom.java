/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula;

import java.util.Random;

/**
 *
 * @author augusto.silva
 */
public class TestRandom {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Random r = new Random(2); // Semente para gerar uma sequencia aleatória, porém sempre a mesma
        for (int i = 0; i < 5; i ++) {
            System.out.println(r.nextInt(101));
            
            //System.out.println((int) (Math.random() * 16) + 2); // Para aumentaro intervalo, basta somar o valor desjado.
        }
    }
    
}
