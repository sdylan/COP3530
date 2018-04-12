#include "myheader.h"

void printAdjList(int numVs, VERTEX* vArr){

        int i;

        printf("\nVertex   List of Adjacent Vertices\n");

        for(i = 0; i<numVs; i++){
        printf("%c        ", vArr[i].c);
        EDGE* curr = vArr[i].p;
        while (curr != NULL){
                printf("%c ",curr->v->c);
                curr = curr->q;
		if (curr!=NULL) printf("--> ");
        }
        printf("\n");
        }
        printf("\n");
}
