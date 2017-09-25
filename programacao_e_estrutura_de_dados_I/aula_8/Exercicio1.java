public class Exercicio1 {
	public static void main(String args[]) {
		Exercicio1.contaNumeroDeOcorrenciasDeCadaLetra("paralelepipedo");
	}

	public static void contaNumeroDeOcorrenciasDeCadaLetra(String s) {
		String[] result = s.split("");
		MyDictionary d = new MyDictionary();

		for (int x = 0; x < result.length; x++) {
			String letra = result[x];

			if (letra.length() > 0) {
				Integer value = Integer.parseInt(d.get(result[x])) + 1;
				d.put(result[x], value.toString());
			}
		}
		
		System.out.println(d);
	}
}