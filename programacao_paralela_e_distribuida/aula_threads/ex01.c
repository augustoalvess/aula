#include <stdio.h>

void funcao(int *n) {
	*n = *n * 2;
	printf("valor recebido em n = %d\n", *n);
}

void main() {
	int a = 3;
	int b = 10;

	char *s = "univates";

	printf("variável a = %d, variável b = %d\n", a, b);
	printf("variável s = %s\n", s);

	funcao(&a);

	printf("variável a = %d, variável b = %d\n", a, b);
}