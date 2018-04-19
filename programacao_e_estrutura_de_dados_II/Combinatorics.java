/**
 * Generic Search
 * - Exploitation of the state space
 * - Tuple - Sequência de valores: n = length (comprimento), k = symbols (valores possíveis de cada valor da tupla)
 */

public class Combinatorics {

	public static int states, solutions;

	public static void binary(int n, String s) {
		if (n == 0) { // base case
			states++;
			if (s.replace("0", "").length() == 5) {
				solutions++;
				System.out.println(s);

			}
		} else { //general case
			binary(n - 1, s + "0");
			binary(n - 1, s + "1");
		}
	}

	public static void main(String[] args) {
		binary(8, "");
		System.out.println("states:" + states);
		System.out.println("solutions:" + solutions);
	}
}