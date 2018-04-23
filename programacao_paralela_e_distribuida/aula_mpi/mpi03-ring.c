#include <stdio.h>
#include <string.h>
#include "mpi.h"

/**
 * Copiar este código para todos os hosts do cluster, para o mesmo diretório de execução, e executar.
 * ./mpicc-copyrun mpi03-ring 2
 */
void main(int argc, char** argv) {
	int rank, nroRanks;
	char hostName[MPI_MAX_PROCESSOR_NAME];
	int tamanhoHostName;
	char msg[1000];

	MPI_Init(&argc, &argv);
	MPI_Comm_rank(MPI_COMM_WORLD, &rank);
	MPI_Comm_size(MPI_COMM_WORLD, &nroRanks);
	MPI_Get_processor_name(hostName, &tamanhoHostName);
	
	if (rank == 0) { // Executado pelo nodo 0
		sprintf(msg, "[%d@%s]", rank, hostName);
		MPI_Send(msg, strlen(msg)+1, MPI_CHAR, 1, 0, MPI_COMM_WORLD);
		MPI_Recv(msg, 1000, MPI_CHAR, nroRanks-1, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
		printf("Processo %d recebeu: %s\n", rank, msg);
	} else { // Executado pelos demais nodos, 1, 2, 3, 4...
		MPI_Recv(msg, 1000, MPI_CHAR, rank-1, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
		printf("%s->[%d@%s]", msg, rank, hostName);
		MPI_Send(msg, strlen(msg)+1, MPI_CHAR, (rank+1)%nroRanks, 0, MPI_COMM_WORLD);
	}

	MPI_Finalize();
}
