#include "myheader.h"
  
void BFS(VERTEX* vArr, int numVs){
        clearVisited(vArr, numVs);

	printf("\nOrder of BFS starting with %c:\n", vArr[0].c);	

	QUEUE* queue = (QUEUE*)malloc(sizeof(QUEUE));
	queue->first = 0;
	queue->last = -1;
	queue->arr = (VERTEX*)malloc(numVs*sizeof(VERTEX));

	VERTEX* curr = &vArr[0];
        printf("%c ", curr->c);
        curr->isvisited = true;
        insert(queue, curr);
	
	int i = 0;

	while(queue->first<=queue->last){
		curr = &queue->arr[queue->first];
		EDGE* edge = curr->p;
		while(edge!=NULL){
			if(!edge->v->isvisited){
				printf("%c ", edge->v->c);
				edge->v->isvisited = true;
				insert(queue, edge->v);
			}
			edge = edge->q;
		}
		removeIt(queue);
	}
	printf("\n");
}
