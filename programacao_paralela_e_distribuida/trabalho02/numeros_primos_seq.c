#include <stdio.h>
#include <string.h>

void main() {
	int tamanhoVetor = 500000;
	int resultado = 0;

	for (int x = 1; x <= tamanhoVetor; x=x+2) {
		int cont = 0;

		// verificar se i é um número primo.
		for (int i = 3; i <= x; i++) {
    		if (x % i == 0) {
     			cont++;
     			break;
    		}
    	}
    
    	// se for primo, soma resultado + 1
  		if (cont == 1) {
  			resultado++;
  		} 		
	}

	printf("A quantidade total de números primos é %d.\n", resultado);
}
