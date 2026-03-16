import java.util.Scanner;

public class HillCipher{
    private static int charToInt(char c) {
        return Character.toUpperCase(c) - 'A';
    }

    private static char intToChar(int i) {
        return (char) ((i % 26 + 26) % 26 + 'A');35
    }

    private static int[] multiplyMatrix(int[][] key, int[] vector) {
        int[] result = new int[2];
        result[0] = (key[0][0] * vector[0] + key[0][1] * vector[1]) % 26;
        result[1] = (key[1][0] * vector[0] + key[1][1] * vector[1]) % 26;
        return result;
    }

    private static int modInverse(int a, int m) {
        a = (a % m + m) % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) return x;
        }
        throw new ArithmeticException("No modular inverse exists!");
    }

    private static int[][] inverseMatrix(int[][] key) {
        int det = key[0][0] * key[1][1] - key[0][1] * key[1][0];
        det = (det % 26 + 26) % 26;
        int invDet = modInverse(det, 26);

        int[][] inv = new int[2][2];
        inv[0][0] = (key[1][1] * invDet) % 26;
        inv[0][1] = (-key[0][1] * invDet) % 26;
        inv[1][0] = (-key[1][0] * invDet) % 26;
        inv[1][1] = (key[0][0] * invDet) % 26;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                inv[i][j] = (inv[i][j] % 26 + 26) % 26;
            }
        }
        return inv;
    }

    public static String encrypt(String plaintext, int[][] key) {
        if (plaintext.length() % 2 != 0) plaintext += "X";
        StringBuilder cipher = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i += 2) {
            int[] vector = {charToInt(plaintext.charAt(i)), charToInt(plaintext.charAt(i + 1))};
            int[] result = multiplyMatrix(key, vector);
            cipher.append(intToChar(result[0])).append(intToChar(result[1]));
        }
        return cipher.toString();
    }

    public static String decrypt(String ciphertext, int[][] key) {
        int[][] invKey = inverseMatrix(key);
        StringBuilder plain = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i += 2) {
            int[] vector = {charToInt(ciphertext.charAt(i)), charToInt(ciphertext.charAt(i + 1))};
            int[] result = multiplyMatrix(invKey, vector);
            plain.append(intToChar(result[0])).append(intToChar(result[1]));
        }
        return plain.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input key matrix
        int[][] key = new int[2][2];
        System.out.println("Enter 2x2 key matrix (integers):");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                key[i][j] = sc.nextInt();
            }
        }

        sc.nextLine(); // consume newline
        System.out.print("Enter plaintext: ");
        String plaintext = sc.nextLine().replaceAll("[^A-Za-z]", "").toUpperCase();

        String encrypted = encrypt(plaintext, key);
        System.out.println("Encrypted text: " + encrypted);

        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted text: " + decrypted);
    }
}