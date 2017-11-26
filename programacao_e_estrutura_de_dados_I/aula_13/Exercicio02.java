import java.util.ArrayList;
import java.util.*;

public class Exercicio02 {
    public static void main(String[] args) {
        ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
        pessoas.add(new Pessoa("joão", "silva", 24));
        pessoas.add(new Pessoa("joão", "silva", 16));
        pessoas.add(new Pessoa("joão", "pedro", 31));
        pessoas.add(new Pessoa("andré", "roma", 68));
        pessoas.add(new Pessoa("marco", "costa", 34));
        pessoas.add(new Pessoa("daniel", "souza", 27));

        Collections.sort(pessoas);
        System.out.println("Pessoas ordenadas: " + pessoas.toString());

    }
}