#include <stdio.h>
#include <string.h>

void main() {
	int tamanhoVetor = 2500000;
	int resultado = 0;

	for (int x = 0; x <= tamanhoVetor; x++) {
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
