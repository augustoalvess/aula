import java.util.ArrayList;
import java.util.Arrays;

public class Exercicio2 {
	public static void main(String args[]) {
		ArrayList<String> s = new ArrayList<String>(Arrays.asList("me", "ball", "is", "home", "you", "test", "no"));
		Exercicio2.armazenaPrimeiraStringDeMesmoTamanho(s);
	}

	public static void armazenaPrimeiraStringDeMesmoTamanho(ArrayList<String> s) {
		MyDictionary d = new MyDictionary();

		for (int x = 0; x < s.size(); x++) {
			Integer key = s.get(x).length();
			
			if (d.get(key.toString()) == "0") {
				d.put(key.toString(), s.get(x));
			}
		}

		System.out.println(d);
	}
}