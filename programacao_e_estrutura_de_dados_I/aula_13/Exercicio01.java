import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;

public class Exercicio01 {
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
        System.out.print("Informe um número inteiro: ");
        int n = entrada.nextInt();

        ArrayList<Integer> lista = new ArrayList<Integer>();
        for (int x = 1; x <= n; x++) {
        	lista.add(x);
        }

        Collections.shuffle(lista);
        System.out.println("Shuffle: " + lista.toString());
        Collections.sort(lista);
        System.out.println("Sort: " + lista.toString());
        Collections.reverse(lista);
        System.out.println("Reverse: " + lista.toString());
	}
}