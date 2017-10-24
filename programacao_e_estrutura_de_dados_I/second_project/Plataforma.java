import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

/**
 * Augusto Alves da Silva
 * Anderson Sprandel
 */
public class Plataforma {
	public static HashMap<String, HashMap> plataformas = new HashMap<String, HashMap>();

	public static void main(String[] args) {
		try
        {
            FileReader fileReader = new FileReader("ign-reviews.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine(); // Lê a primeira para ignorar o cabeçalho.
            DecimalFormat df = new DecimalFormat("#.##"); 

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

                    // Obtém a soma das notas dos gêneros Actions por plataforma
                    if (linhaSplit[6].equals("Action")) {
                        // Soma os scores de todas as plataformas
                        plataformas.get(linhaSplit[4]).put(
                            "média de todos os scores actions",
                            (Float.parseFloat(plataformas.get(linhaSplit[4]).get("média de todos os scores actions").toString()) + Float.parseFloat(linhaSplit[5]))
                        );

                        // Soma a quantidade de reviews Actions por plataforma.
                        plataformas.get(linhaSplit[4]).put(
                            "número de reviews Actions", 
                            (Integer.parseInt(plataformas.get(linhaSplit[4]).get("número de reviews Actions").toString()) + 1)
                        );
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
                valores.put("média de todos os scores actions", (linhaSplit[6].equals("Action") ? Float.parseFloat(linhaSplit[5]) : 0) + "");
                valores.put("número de reviews Actions", (linhaSplit[6].equals("Action") ? "1" : "0"));
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

                // Calcula o desvio padrão de todos os scores
                plataformas.get(plataforma).put(
                    "desvio padrão (para variável aleatória discreta) de todos os scores",
                    df.format(desvioPadrao(plataforma, scores, (Double.parseDouble(plataformas.get(plataforma).get("média de todos os scores").toString()) / Double.parseDouble(plataformas.get(plataforma).get("número de reviews").toString()))))
                );   

                // Calcula a média de todos os scores
                plataformas.get(plataforma).put(
                    "média de todos os scores",
                    df.format((Double.parseDouble(plataformas.get(plataforma).get("média de todos os scores").toString()) / Double.parseDouble(plataformas.get(plataforma).get("número de reviews").toString())))
                );             

                // Calcula a média de todos os scores Actions
                plataformas.get(plataforma).put(
                    "média de todos os scores actions",
                    (Float.parseFloat(plataformas.get(plataforma).get("média de todos os scores actions").toString()) / Float.parseFloat(plataformas.get(plataforma).get("número de reviews Actions").toString()))
                );

                // Remove valores desnecessários
                plataformas.get(plataforma).remove("melhor score");
                plataformas.get(plataforma).remove("pior score");                
            }

            //Percorre as plataformas e exibe os resultados obtidos.
            String plataformaActions = "";
            String scoreActions = "0";
            for (String plataforma: plataformas.keySet()) {
            	System.out.println(plataforma);
            	for (Object valor: plataformas.get(plataforma).keySet()) {
                    System.out.println("    " + valor + " = " + plataformas.get(plataforma).get(valor));
                    if (valor.equals("média de todos os scores actions") && (Float.parseFloat(plataformas.get(plataforma).get(valor).toString()) > Float.parseFloat(scoreActions))) {
                        plataformaActions = plataforma;
                        scoreActions = plataformas.get(plataforma).get(valor).toString();
                    }
            	}
            }

            System.out.println();
            System.out.println(plataformaActions + " é a plataforma com os jogos do gênero 'Action' mais bem avaliados");
        }
        catch (Exception err) {}
	}

    public static double desvioPadrao(String plataforma, ArrayList<Double> scores, double mediaAritimetica) {
    	if (scores.size() == 1) {
            return 0;
        } else {
            float somatorio = 01;
            for (int i = 0; i < scores.size(); i++) {
                float result = Float.parseFloat(String.valueOf(scores.get(i) - mediaAritimetica));
                somatorio = somatorio + result * result;
            }
            return Math.sqrt(((float) 1 / (scores.size() - 1)) * somatorio);
        }       
    }
}