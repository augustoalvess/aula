import java.util.ArrayList;
import java.util.*;

public class Exercicio03 {
    public static void main(String args[]) {
        String palavra = "paralelepipedo";
        String[] result = palavra.split("");

        HashMap<String, Integer> d = new HashMap<String, Integer>();
        for (int x = 0; x < result.length; x++) {
            String letra = result[x];
            if (letra.length() > 0) {
                d.put(letra, (((d.get(letra) != null) ? d.get(letra) : 0) + 1));
            }
        }

        ArrayList<OcorrenciaLetra> ocorrencias = new ArrayList<OcorrenciaLetra>();
        for (String letra: d.keySet()) {
            ocorrencias.add(new OcorrenciaLetra(letra, d.get(letra)));
        }

        Collections.sort(ocorrencias);
        Collections.reverse(ocorrencias);
        System.out.println(ocorrencias.toString());
    }
}