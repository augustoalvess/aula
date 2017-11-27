import java.util.*;

public class DestinoValor implements Comparable<DestinoValor> {
	String cep;
	String valor;

    public DestinoValor(String cep, String valor) {
    	this.cep = cep;
        this.valor = valor;
    }

    public String toString() {
    	return this.cep + "=" + this.valor;
    }

    public int compareTo(DestinoValor d) {
        Double compare = (Double.parseDouble(this.valor) - Double.parseDouble(d.valor));
        if (compare < 0) {
            return -1;
        } else if (compare == 0) {
            return 0;
        } else {
            return 1;
        }
    }
}