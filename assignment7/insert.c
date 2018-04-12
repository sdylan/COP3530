#include "myheader.h"

void insert(QUEUE* queue, VERTEX* vertex) {
	queue->arr[++queue->last] = *vertex;
}
