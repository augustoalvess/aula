import java.io.*;

public class Exercicio3 {
	public static void main(String args[]) {
		try {
			FileReader fileReader = new FileReader("packages.txt");
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	        MyDictionary d = new MyDictionary();

	        while (bufferedReader.ready())
            {
                String linha = bufferedReader.readLine();
                String[] result = linha.split(" ");
                d.put(result[0], result[1]);
            }

            System.out.println(d);
            System.out.println(d.size() + " destinos totais");
        } catch (Exception err) {}
	}
}