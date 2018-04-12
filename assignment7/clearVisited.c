#include "myheader.h"

void clearVisited(VERTEX* vArr, int numVs){
        int i;
        for (i=0;i<numVs;i++)
                vArr[i].isvisited = false;
}
