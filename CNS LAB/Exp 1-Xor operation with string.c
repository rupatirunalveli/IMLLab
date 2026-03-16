#include <stdio.h>
#include <string.h>

int main(void)
{
    char str[] = "Helloworld";
    char str1[20];
    int i, len;
    
    len = strlen(str);
    for (i = 0; i < len; i++) 
    {
        str1[i] = str[i] ^ 0;
        printf("%c", str1[i]);
    }
    str1[len] = '\0'; 
    printf("\n");
    return 0;
}

