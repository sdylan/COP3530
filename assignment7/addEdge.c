#include "myheader.h"

void addEdge(char from, char to, int size, VERTEX* vArr){

        int i = findVertex(vArr, size, from);
        int j = findVertex(vArr, size, to);

        EDGE* newEdge = (EDGE*)malloc(sizeof(EDGE));
        newEdge->q = NULL;
        EDGE* tempEdge;
        newEdge->v = &vArr[j];
	newEdge->v->indegree++;

        if (vArr[i].p == NULL) vArr[i].p = newEdge;
        else {
                tempEdge = vArr[i].p;
                while (tempEdge->q != NULL){
                        tempEdge = tempEdge->q;
                }
                tempEdge->q = newEdge;
        }
}
