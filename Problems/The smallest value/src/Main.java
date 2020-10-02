import java.math.BigInteger;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner keyboard = new Scanner(System.in);

        BigInteger inputM = new BigInteger(keyboard.next());
        BigInteger nFactorial = BigInteger.ONE;
        long n = 1;

        while (nFactorial.max(inputM).equals(inputM)) {
            if (nFactorial.equals(inputM)) {
                break;
            } else {
                nFactorial = nFactorial.multiply(BigInteger.valueOf(n));
                n++;
            }

        }
        System.out.print(n - 1);
    }
}