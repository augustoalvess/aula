import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

public class Plataforma {
	public static void main(String[] args) {
		try
        {
            FileReader fileReader = new FileReader("ign-reviews.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine(); // Lê a primeira para ignorar o cabeçalho.
            DecimalFormat df = new DecimalFormat("#.##"); 

            HashMap<String, HashMap> plataformas = new HashMap<String, HashMap>();
            ArrayList<Double> scores = new ArrayList<Double>();

            // Inicialização e somatórios iniciais dos registros.
            while (bufferedReader.ready())
            {
                String linha = bufferedReader.readLine();
                String[] linhaSplit = linha.split(";");

                if (plataformas.containsKey(linhaSplit[4])) {

                    // Soma a quantidade de reviews por plataforma.
                	plataformas.get(linhaSplit[4]).put(
                		"número de reviews", 
                		(Integer.parseInt(plataformas.get(linhaSplit[4]).get("número de reviews").toString()) + 1)
                	);

                    // Soma a quantidade de editor's choice por plataforma.
                	if (linhaSplit[7].toString().equals("Y")) {
                		plataformas.get(linhaSplit[4]).put(
	                		"número de Editor’s Choice", 
	                		(Integer.parseInt(plataformas.get(linhaSplit[4]).get("número de Editor’s Choice").toString()) + 1)
	                	);
                	}

                    // Soma a quantidade de avaliações Amazing por plataforma.
                    if (linhaSplit[1].toString().equals("Amazing")) {
                        plataformas.get(linhaSplit[4]).put(
                            "percentual de Amazing reviews",
                            (Integer.parseInt(plataformas.get(linhaSplit[4]).get("percentual de Amazing reviews").toString()) + 1)
                        );
                    }

                    // Soma os scores de todas as plataformas
                    plataformas.get(linhaSplit[4]).put(
                        "média de todos os scores",
                        (Float.parseFloat(plataformas.get(linhaSplit[4]).get("média de todos os scores").toString()) + Float.parseFloat(linhaSplit[5]))
                    );

                    // Obtem o melhor jogo da plataforma
                    for (String plataforma: plataformas.keySet()) {
                        if (plataforma.equals(linhaSplit[4]) && Float.parseFloat(linhaSplit[5]) > Float.parseFloat(plataformas.get(plataforma).get("melhor score").toString())) {
                            plataformas.get(linhaSplit[4]).put(
                                "melhor jogo (um entre os de maior score)",
                                linhaSplit[2]
                            );
                            plataformas.get(linhaSplit[4]).put(
                                "melhor score",
                                linhaSplit[5]
                            );
                        }
                    }

                    // Obtem o pior jogo da plataforma
                    for (String plataforma: plataformas.keySet()) {
                        if (plataforma.equals(linhaSplit[4]) && Float.parseFloat(linhaSplit[5]) > Float.parseFloat(plataformas.get(plataforma).get("melhor score").toString())) {
                            plataformas.get(linhaSplit[4]).put(
                                "pior jogo (um entre os de menor score)",
                                linhaSplit[2]
                            );
                            plataformas.get(linhaSplit[4]).put(
                                "pior score",
                                linhaSplit[5]
                            );
                        }
                    }


                    scores.add(Double.parseDouble(linhaSplit[5]));

                	continue;
                }

                // Inicializa a hashMap de valores da plataforma.
				HashMap<String, String> valores = new HashMap<String, String>();
				valores.put("número de reviews", "1");
				valores.put("número de Editor’s Choice", ((linhaSplit[7] == "Y") ? "1" : "0"));
				valores.put("percentual de Amazing reviews", ((linhaSplit[1] == "Amazing") ? "1" : "0"));
				valores.put("média de todos os scores", Float.parseFloat(linhaSplit[5]) + "");
				valores.put("desvio padrão (para variável aleatória discreta) de todos os scores", "0");
				valores.put("melhor jogo (um entre os de maior score)", linhaSplit[2]);
                valores.put("melhor score", Float.parseFloat(linhaSplit[5]) + "");
				valores.put("pior jogo (um entre os de menor score)", linhaSplit[2]);
                valores.put("pior score", Float.parseFloat(linhaSplit[5]) + "");
                scores.add(Double.parseDouble(linhaSplit[5]));
                
                plataformas.put(linhaSplit[4], valores);
            }

            // Finaliza os cálculos para a exibição dos resultados
            for (String plataforma: plataformas.keySet()) {

                // Calcula o percentual de amazing reviews
                plataformas.get(plataforma).put(
                    "percentual de Amazing reviews",
                    df.format(((Double.parseDouble(plataformas.get(plataforma).get("percentual de Amazing reviews").toString()) * 100) / Double.parseDouble(plataformas.get(plataforma).get("número de reviews").toString()))) + "%"
                );

                // Calcula a média de todos os scores
                plataformas.get(plataforma).put(
                    "média de todos os scores",
                    df.format((Double.parseDouble(plataformas.get(plataforma).get("média de todos os scores").toString()) / Double.parseDouble(plataformas.get(plataforma).get("número de reviews").toString())))
                );

                // Calcula o desvio padrão de todos os scores
                plataformas.get(plataforma).put(
                    "desvio padrão (para variável aleatória discreta) de todos os scores",
                    df.format(desvioPadrao(scores))
                );                

                // Remove valores desnecessários
                plataformas.get(plataforma).remove("melhor score");
                plataformas.get(plataforma).remove("pior score");
                
            }

            //Percorre as plataformas e exibe os resultados obtidos.
            for (String plataforma: plataformas.keySet()) {
            	System.out.println(plataforma);
            	for (Object valor: plataformas.get(plataforma).keySet()) {
            		System.out.println("    " + valor + " = " + plataformas.get(plataforma).get(valor));
            	}
            }
        }
        catch (Exception err) {}
	}

    public static double desvioPadrao(ArrayList<Double> scores) {
        
        return 0.00;
    }
}