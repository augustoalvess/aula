public class TestRecursion {

	// Permutação
	public static int fatorial(int n) {
		if (n == 0) {
			return 1;
		} else {
			return n * fatorial(n - 1);
		}
	}

	// Tupla, têm repetição
	public static void binary(int n, String s) {
		if (n == 0) {
			System.out.println(s);
		} else {
			binary(n - 1, s + '0');
			binary(n - 1, s + '1');
		}
	}

	// Tupla, têm repetição
	public static void abc(int n, String s) {
		if (n == 0) {
			System.out.println(s);
		} else {
			abc(n - 1, s + 'a');
			abc(n - 1, s + 'b');
			abc(n - 1, s + 'c');
		}
	}

	//Permutação, trocar coisas de lugar, mas sem repetição
	public static void permutation(int n, String s) {
		if (n == 0) {
			System.out.println(s);
		} else {
			if (!s.contains("a")) { permutation(n - 1, s + "a"); }
			if (!s.contains("b")) { permutation(n - 1, s + "b"); }
			if (!s.contains("c")) { permutation(n - 1, s + "c"); }
		}
	}

	public static void main(String[] args) {
		//System.out.println(fatorial(5));
		//binary(3, "");
		//abc(4, "");
		permutation(3, "");
	}

	/**
     * Merge sort, vai quebrando o vetor pela metade de forma recursiva, e vai ordenando atravéz de merge (fusão, imagem do drive).
     * Quick sort, seleciona uma pivo e separa o vetor em dois conjuntos, um com os menores que o pivo e outro com os menores que o pivo.
     */

}