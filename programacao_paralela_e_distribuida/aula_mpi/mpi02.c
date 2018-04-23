#include <stdio.h>
#include <string.h>
#include "mpi.h"

void main(int argc, char** argv) {
	int rank, nroRanks;
	char hostName[MPI_MAX_PROCESSOR_NAME];
	int tamanhoHostName;

	MPI_Init(&argc, &argv);
	MPI_Comm_rank(MPI_COMM_WORLD, &rank); // Este será o id do nodo.
	MPI_Comm_size(MPI_COMM_WORLD, &nroRanks);
	MPI_Get_processor_name(hostName, &tamanhoHostName);

	printf("Meu rank %d de %d executando no host %s\n", rank, nroRanks, hostName);

	char msg[100];
	MPI_Status status;

	if (rank != 0) {
		sprintf(msg, "Oi! Eu sou o processo %d do host %s", rank, hostName);
		MPI_Send(msg, strlen(msg) + 1, MPI_CHAR, 0, 0, MPI_COMM_WORLD);
	} else {
		for (int i = 1; i < nroRanks; i++) {
			MPI_Recv(msg, 100, MPI_CHAR, i, 0, MPI_COMM_WORLD, &status);
			printf("%s\n", msg);
		}
	}

	MPI_Finalize();
}
