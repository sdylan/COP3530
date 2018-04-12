#include "myheader.h"

void topsort(VERTEX* vArr, int numVs){
	int i=numVs, j;
	bool acyclic = false;

	printf("\nList after a topological sort:\n");
	
	while( i > 0) {
		for(j =0;j<numVs;j++){
			if(vArr[j].indegree == -1){
				i--;
				vArr[j].indegree--;
				EDGE* edge = vArr[j].p;
				while(edge!=NULL){
					edge->v->indegree--;
					edge = edge->q;
				}
				printf("%c ",vArr[j].c);
			}
			if(vArr[j].indegree == 0){ 
				vArr[j].indegree--;
				acyclic = true;	
			}	
		}
		if(!acyclic) {
			printf("Graph is not acyclic, so can't top sort.");
			break;
		}
	}
	printf("\n\n");
}
