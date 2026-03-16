import java.util.Scanner;
public class Ceasercipher{
    static String process(String text, int shift) {
        StringBuilder sb = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isUpperCase(ch))
                sb.append((char) ((ch - 'A' + shift + 26) % 26 + 'A'));
            else if (Character.isLowerCase(ch))
                sb.append((char) ((ch - 'a' + shift + 26) % 26 + 'a'));
            else sb.append(ch);
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine(); int shift = sc.nextInt();
        String enc = process(text, shift), dec = process(enc, -shift);
        System.out.println("Encrypted: " + enc + "\nDecrypted: " + dec);
    }
}