#include "myheader.h"

int findVertex(VERTEX* vArr, int size, char ch){

        int i;

        for(i=0;i<size;i++){
                if(vArr[i].c == ch)
                        return i;
        }

        return i;
}
