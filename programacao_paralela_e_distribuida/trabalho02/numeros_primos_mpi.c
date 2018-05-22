#include <stdio.h>
#include <string.h>
#include "mpi.h"

void main(int argc, char** argv) {
	int nroNodo, nroNodos;
	char hostName[MPI_MAX_PROCESSOR_NAME];
	int tamanhoHostName;

	MPI_Init(&argc, &argv);
	MPI_Comm_rank(MPI_COMM_WORLD, &nroNodo);
	MPI_Comm_size(MPI_COMM_WORLD, &nroNodos);
	MPI_Get_processor_name(hostName, &tamanhoHostName);

	int tamanhoVetor = 500000;
	int qtdePorNodo = (int)tamanhoVetor / nroNodos;
	int resultado = 0;
	int resultadoParcial = 0;

	int inicio = nroNodo * qtdePorNodo;
	if (inicio % 2 == 0) {
		inicio++;
	}

	int fim = inicio + qtdePorNodo - 1;

	printf("[nodo: %d@%s] - %d .. %d\n", nroNodo, hostName, inicio, fim);

	for (int x = inicio; x <= fim; x=x+2) {
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

	if (nroNodo != 0) {
		MPI_Send(&resultado, 1, MPI_INT, 0, 0, MPI_COMM_WORLD);
	} else {
		for (int i = 1; i < nroNodos; i++) {
			MPI_Recv(&resultadoParcial, 1, MPI_INT, i, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
			resultado = resultado + resultadoParcial;
		}
		printf("A quantidade total de números primos é %d.\n", resultado);
	}

	MPI_Finalize();
}
