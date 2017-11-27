import java.util.ArrayList;
import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

public class Exercicio05 {
    public static void main(String args[]) {
        try {
            FileReader fileReader = new FileReader("packages.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            HashMap<String, String> d = new HashMap<String, String>();
            DecimalFormat df = new DecimalFormat("#.##"); 

            while (bufferedReader.ready())
            {
                String linha = bufferedReader.readLine();
                String[] result = linha.split(" ");

                Double value = Double.parseDouble(result[1]);
                Double currentValue = (d.get(result[0]) != null) ? Double.parseDouble(d.get(result[0])) : 0.0;
                d.put(result[0], df.format((currentValue + value)).replace(',', '.'));
            }
            
            ArrayList<DestinoValor> destinos = new ArrayList<DestinoValor>();
            for (String destino: d.keySet()) {
                destinos.add(new DestinoValor(destino, d.get(destino)));
            }

            Collections.sort(destinos);
            Collections.reverse(destinos);
            System.out.println(destinos.toString());
        } catch (Exception err) {}
    }
}