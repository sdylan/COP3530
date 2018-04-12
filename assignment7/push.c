#include "myheader.h"

void push(STACK* stack, VERTEX* vertex){
        stack->arr[++stack->top] = *vertex;
}
