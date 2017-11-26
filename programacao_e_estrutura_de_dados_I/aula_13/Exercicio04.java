import java.util.ArrayList;
import java.util.*;
import java.io.*;

public class Exercicio04 {
    public static void main(String args[]) {
        try {
            FileReader fileReader = new FileReader("packages.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            HashMap<String, String> d = new HashMap<String, String>();

            while (bufferedReader.ready())
            {
                String linha = bufferedReader.readLine();
                String[] result = linha.split(" ");
                d.put(result[0], result[1]);
            }

            ArrayList<Destino> destinos = new ArrayList<Destino>();
            for (String destino: d.keySet()) {
                destinos.add(new Destino(destino, d.get(destino)));
            }

            Collections.sort(destinos);
            System.out.println(destinos.toString());
        } catch (Exception err) {}
    }
}