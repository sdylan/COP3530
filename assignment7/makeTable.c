#include "myheader.h"

int makeTable(VERTEX* vArr, char input[], int size) {

        int i, k, l;
        int j = 0;

        for(i = 0; i< size; i++){

                bool inList = false;
                char temp = input[i];
                for (k = 0; k<j; k++){
                        if(vArr[k].c == temp) inList = true;
                }

                if(!inList) {
                        vArr[j].c = temp;
			vArr[j].indegree = 0;
                        vArr[j].p = NULL;
                        j++;
                }
        }

        for( i = 0; i<size; i+=2){
                addEdge(input[i], input[i+1], j, vArr);
        }

        return j;

}
