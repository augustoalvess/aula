import java.util.*;

public class Pessoa implements Comparable<Pessoa> {
	String nome;
	String sobrenome;
	int idade;

    public Pessoa(String nome, String sobrenome, int idade) {
    	this.nome = nome;
    	this.sobrenome = sobrenome;
    	this.idade = idade;
    }

    public String toString() {
    	return this.nome + " " + this.sobrenome + "  " + this.idade;
    }

    public int compareTo(Pessoa p) {
    	String nomePessoa1 = this.nome + " " + this.sobrenome;
    	String nomePessoa2 = p.nome + " " + p.sobrenome;
    	int compare = nomePessoa1.compareTo(nomePessoa2);
    	if (compare == 0) {
    		return (this.idade - p.idade);
		} 
		return compare;
    }
}