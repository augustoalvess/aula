#include <stdio.h>
#include <pthread.h>

void *OiMundo(void *in) {
	char *str = (char *)in;
	printf("%s\n", str);
	pthread_exit(NULL);
}

void main() {
	pthread_t t1;
	printf("inicio do main()\n");

	char *str = "Oi mundo";
	pthread_create(&t1, NULL, &OiMundo, str);

	printf("fim do main()\n");
	pthread_exit(NULL);
}