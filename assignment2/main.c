#include "my.h"

int main (int argc, char *argv[]){
	
	FILE *fr;
	int i, j, nElems;
	int *a;

	fr = fopen(argv[1], "r");
	fscanf(fr, "%d", &nElems);

	a = (int*)calloc(nElems, sizeof(int));
	
	for (i=0; i<nElems; i++){
		fscanf(fr, "%d", &a[i]);
	}

	oesort(a, nElems-1);
	
	j = 0;
	while (j!=-1){
		
		printf("\nEnter an index from 0 to %d (or -1 to quit): ", nElems-1);
		scanf("%d", &j);
		if(j<nElems && j>=0){
			printf("\nThe value at index %d is %d.\n", j, a[j]);
		}
		else if(j!=-1){
			printf("\nThe index you have entered is out of bounds, try again.\n");
			j=0;
		}	
	}

	free(a);
	
	fclose(fr);

	printf("\nProgram is closing, thank you.\n\n");

		

	return;
} 	 
