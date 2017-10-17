import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.io.*;

/**
 * Augusto Alves da Silva
 * Anderson Sprandel
 */
public class Tarefa0 {
	public static void main(String[] args) {
		try
        {
            FileReader fileReader = new FileReader("ign-reviews.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            ArrayList<String> retorno = new ArrayList<String>();

            while (bufferedReader.ready())
            {
                String linha = bufferedReader.readLine();

                MyDictionary reviews = new MyDictionary();
                reviews.put();



            }

            
        }
        catch (Exception err) {}
	}
}