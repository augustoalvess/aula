import java.util.ArrayList;
import java.io.*;
import java.util.*;

public class Plataforma {
	public static void main(String[] args) {
		try
        {
            FileReader fileReader = new FileReader("ign-reviews.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine(); // Lê a primeira para ignorar o cabeçalho.

            HashMap<String, HashMap> plataformas = new HashMap<String, HashMap>();





            while (bufferedReader.ready())
            {
                String linha = bufferedReader.readLine();
                String[] linhaSplit = linha.split(";");

                if (plataformas.containsKey(linhaSplit[4])) {
                	plataformas.get(linhaSplit[4]).put(
                		"número de reviews", 
                		(Integer.parseInt(plataformas.get(linhaSplit[4]).get("número de reviews").toString()) + 1)
                	);

                	if (linhaSplit[7].toString().equals("Y")) {
                		plataformas.get(linhaSplit[4]).put(
	                		"número de Editor’s Choice", 
	                		(Integer.parseInt(plataformas.get(linhaSplit[4]).get("número de Editor’s Choice").toString()) + 1)
	                	);                		
                	}

                	continue;
                }

				HashMap<String, Integer> valores = new HashMap<String, Integer>();
				valores.put("número de reviews", 1);
				valores.put("número de Editor’s Choice", ((linhaSplit[7] == "Y") ? 1 : 0));
				valores.put("percentual de ‘Masterpiece’ ou ‘Amazing’ reviews", 0);
				valores.put("média de todos os scores", 0);
				valores.put("desvio padrão (para variável aleatória discreta) de todos os scores", 0);
				valores.put("melhor jogo (um entre os de maior score)", 0);
				valores.put("pior jogo (um entre os de menor score)", 0);
                
                plataformas.put(linhaSplit[4], valores);
            }






            //Percorre as plataformas e exibe os resultados obtidos.
            for (String plataforma: plataformas.keySet()) {
            	System.out.println(plataforma);
            	for (Object valor: plataformas.get(plataforma).keySet()) {
            		System.out.println("    " + valor + " = " + plataformas.get(plataforma).get(valor));
            	}
            }

        }
        catch (Exception err)
        {

        }
	}
}