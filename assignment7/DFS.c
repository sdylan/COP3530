#include "myheader.h"

void DFS(VERTEX* vArr, int numVs){

        clearVisited(vArr, numVs);
	
	printf("Order of DFS starting with %c:\n", vArr[0].c);

        STACK* stack = (STACK*)malloc(sizeof(STACK));
        stack->top=-1;
        stack->arr = (VERTEX*)malloc(numVs*sizeof(VERTEX));

        VERTEX* curr = &vArr[0];
        printf("%c ", curr->c);
        curr->isvisited = true;
        push(stack, curr);

        while(stack->top!=-1){

                int i = nextUnvisited(vArr, curr, numVs);

                if(i!=-1){
                        curr = &vArr[i];
                        curr->isvisited = true;
                        printf("%c ", curr->c);
                        push(stack, curr);
                }
                else{
                        pop(stack);
                        if(stack->top!=-1) curr = &stack->arr[stack->top];
                }
        }
        printf("\n");
}
