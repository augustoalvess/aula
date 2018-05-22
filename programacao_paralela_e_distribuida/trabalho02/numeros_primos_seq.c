#include <stdio.h>
#include <string.h>

void main() {
	int tamanhoVetor = 2500;
	int resultado = 0;

	for (int x = 1; x <= tamanhoVetor; x=x+2) {
		int cont = 0;

		// verificar se i é um número primo.
		for (int i = 1; i <= x; i++) {
    		if (x % i == 0) {
     			cont++;
    		}
    	}
    
    	// se for primo, soma resultado + 1
  		if (cont == 2) {
  			resultado++;
  		} 		
	}

	printf("A quantidade total de números primos é %d.\n", resultado);
}
