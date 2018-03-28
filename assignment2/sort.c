#include "my.h"

void oesort(int array[], int size){
	
	int i=0;
	bool done = false;
	int temp;

	while (!done){
		done = true;
		for (i=1; i<size; i+=2){
			if (array[i]>array[i+1]){
				temp = array[i];
				array[i] = array[i+1];
				array[i+1] = temp;
				done = false;
			}
		}
		for (i=0; i<size; i+=2){
			if (array[i]>array[i+1]){
				temp = array[i];
				array[i] = array[i+1];
				array[i+1] = temp;
				done = false;
			}
		}	
	}
	
	return;
}
	 
