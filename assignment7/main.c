#include "myheader.h"

int main (int argc, char* argv[]) {
	
	char input[200];

	int size = readFile(argv[1], input);	
	
	VERTEX* vArr = (VERTEX*)malloc(size*sizeof(VERTEX));
	
	int numVs = makeTable(vArr, input, size);

	printAdjList(numVs, vArr);

	DFS(vArr, numVs);

	BFS(vArr, numVs);

	topsort(vArr, numVs);

	return 0;
}
