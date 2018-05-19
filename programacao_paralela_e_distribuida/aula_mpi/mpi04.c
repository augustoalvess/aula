#include <stdio.h>
#include <string.h>
#include "mpi.h"

/**
 * ./mpicc-copyrun mpi04 3
 */
void main(int argc, char** argv) {
	int nroNodo, nroNodos;
	char hostName[MPI_MAX_PROCESSOR_NAME];
	int tamanhoHostName;

	MPI_Init(&argc, &argv);
	MPI_Comm_rank(MPI_COMM_WORLD, &nroNodo);
	MPI_Comm_size(MPI_COMM_WORLD, &nroNodos);
	MPI_Get_processor_name(hostName, &tamanhoHostName);

	int tamanhoVetor = 100000;
	int qtdePorNodo = (int)tamanhoVetor / nroNodos;
	int resultado = 0;
	int resultadoParcial = 0;

	int inicio = nroNodo * qtdePorNodo;
	int fim = inicio + qtdePorNodo - 1;

	printf("[nodo: %d@%s] - %d .. %d\n", nroNodo, hostName, inicio, fim);

	int a = 0;
	for (int i = inicio; i < fim; i++) {
		resultado = resultado + 1;

		// For adicional para gastar um tempo
		for (int j = 0; j < 100000; j++) {
			a = (a + i) * j;
		}
	}

	if (nroNodo != 0) { // Executado pelo nodo 0
		MPI_Send(&resultado, 1, MPI_INT, 0, 0, MPI_COMM_WORLD);
		
	} else { // Executado pelos demais nodos, 1, 2, 3, 4...
		for (int i = 1; i < nroNodos; i++) {
			MPI_Recv(&resultadoParcial, 1, MPI_INT, i, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
			resultado = resultado + resultadoParcial;
		}
		printf("Resultado final: %d\n", resultado);
	}

	MPI_Finalize();
}
