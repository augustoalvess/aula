import java.io.*;

public class Exercicio4 {
	public static void main(String args[]) {
		try {
			FileReader fileReader = new FileReader("packages.txt");
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	        MyDictionary d = new MyDictionary();

	        while (bufferedReader.ready())
            {
                String linha = bufferedReader.readLine();
                String[] result = linha.split(" ");

                Double value = Double.parseDouble(d.get(result[0])) + 1;
                d.put(result[0], value.toString());
            }
            
            d.sort();
            System.out.println(d);
        } catch (Exception err) {}
	}
}