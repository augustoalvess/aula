import java.util.*;

public class OcorrenciaLetra implements Comparable<OcorrenciaLetra> {
	String letra;
	int ocorrencia;

    public OcorrenciaLetra(String letra, int ocorrencia) {
    	this.letra = letra;
        this.ocorrencia = ocorrencia;
    }

    public String toString() {
    	return this.letra + "=" + this.ocorrencia;
    }

    public int compareTo(OcorrenciaLetra l) {
    	return (this.ocorrencia - l.ocorrencia);
    }
}