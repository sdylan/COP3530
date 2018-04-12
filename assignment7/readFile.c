# include "myheader.h"

int readFile (char* infile, char input[]) {
        FILE* fr;

        fr = fopen(infile, "r");

        int i = 0;

        while(fscanf(fr, " %c", &input[i++])!=EOF);

        return i-1;
}
