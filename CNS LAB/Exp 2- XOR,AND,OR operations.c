#include <stdio.h>
int main() {
    char str[] = "Hello world";
    char andResult[50];
    char orResult[50];
    char xorResult[50];
    int i;

    for (i = 0; str[i] != '\0'; i++) {
        andResult[i] = str[i] & 127;
        orResult[i]  = str[i] | 127;
        xorResult[i] = str[i] ^ 127;
    }

    andResult[i] = '\0';
    orResult[i] = '\0';
    xorResult[i] = '\0';

    printf("Original string : %s\n", str);
    printf("AND with 127     : %s\n", andResult);
    printf("OR with 127      : %s\n", orResult);
    printf("XOR with 127     : %s\n", xorResult);

    return 0;
}