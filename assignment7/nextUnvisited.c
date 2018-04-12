#include "myheader.h"

int nextUnvisited (VERTEX* vArr, VERTEX* curr, int numVs){
        EDGE* edge = curr->p;

        while (edge!=NULL){
                if (edge->q == NULL && edge->v->isvisited) return -1;
                else if (edge->v->isvisited) edge = edge->q;
                else return findVertex(vArr, numVs, edge->v->c);
        }
        return -1;      
}
