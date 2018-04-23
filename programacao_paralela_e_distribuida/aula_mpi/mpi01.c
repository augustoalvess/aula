#include <stdio.h>
#include <string.h>
#include "mpi.h"

/**
 * Para executar em outras máquinas: 
 * -Para compilar um programa mpi: mpicc mpi01.c -o mpi01
 * -Criar as chaves ssh e enviar para as máquinas desejadas para os clustes (para não precisar toda hora autenticar);
 * -Copiar este programa nas máquinas escolhidas, para o diretório identico em que se encontra na máquina atual;
 * -Executar o comando: mpirun -np 3 -host 10.3.1.11,10.3.1.12,10.3.1.13 mpi01
 */
void main(int argc, char** argv) {
	int rank, nroRanks;
	char hostName[MPI_MAX_PROCESSOR_NAME];
	int tamanhoHostName;

	MPI_Init(&argc, &argv);
	MPI_Comm_rank(MPI_COMM_WORLD, &rank); // Este será o id do nodo.
	MPI_Comm_size(MPI_COMM_WORLD, &nroRanks);
	MPI_Get_processor_name(hostName, &tamanhoHostName);

	printf("Meu rank %d de %d executando no host %s\n", rank, nroRanks, hostName);

	MPI_Finalize();
}
