import java.util.*;

public class Destino implements Comparable<Destino> {
	String cep;
	String valor;

    public Destino(String cep, String valor) {
    	this.cep = cep;
        this.valor = valor;
    }

    public String toString() {
    	return this.cep + "=" + this.valor;
    }

    public int compareTo(Destino d) {
        String[] result1 = this.cep.split("-");
        String[] result2 = d.cep.split("-");
        if (result1[0].equals(result2[0])) {
            return (Integer.parseInt(result1[1]) - Integer.parseInt(result2[1]));
        }
    	return (Integer.parseInt(result1[0]) - Integer.parseInt(result2[0]));
    }
}