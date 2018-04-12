#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

struct EDGETAG;

typedef struct {
	char c;
	int indegree;
	bool isvisited;
	struct EDGETAG* p;
} VERTEX;

typedef struct EDGETAG {
	VERTEX* v;
	struct EDGETAG* q;
} EDGE;

typedef struct{
        int top;
        VERTEX* arr;
} STACK;

typedef struct{
	int first;
	int last;
	VERTEX* arr;
} QUEUE;

int makeTable(VERTEX* vArr, char input[], int size);
void addEdge(char from, char to, int size, VERTEX* vArr);
void printAdjList(int numVs, VERTEX* vArr);
void DFS(VERTEX* vArr, int numVs);
void BFS(VERTEX* vArr, int numVs);
void push(STACK* stack, VERTEX* vertex);
void pop(STACK* stack);
void insert(QUEUE* queue, VERTEX* vertex);
void removeIt(QUEUE* queue);
void clearVisited(VERTEX* vArr, int numVs);
int nextUnvisited (VERTEX* vArr, VERTEX* curr, int numVs);
int findVertex(VERTEX* vArr, int size, char ch);
int readFile (char* infile, char input[]);
void topsort(VERTEX* vArr, int numVs);
