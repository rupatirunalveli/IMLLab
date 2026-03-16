import java.util.Scanner;

public class DiffieHellman {
    static int modPow(int b, int e, int m) {
        int r = 1;
        for (int i = 0; i < e; i++) r = (r * b) % m;
        return r;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter prime number p: ");
        int p = sc.nextInt();
        System.out.print("Enter primitive root g: ");
        int g = sc.nextInt();
        System.out.print("Enter private key a: ");
        int a = sc.nextInt();
        System.out.print("Enter private key b: ");
        int b = sc.nextInt();

        int A = modPow(g, a, p);
        int B = modPow(g, b, p);
        int keyA = modPow(B, a, p);
        int keyB = modPow(A, b, p);

        System.out.println("Shared key at A: " + keyA);
        System.out.println("Shared key at B: " + keyB);

        sc.close();
    }
}
