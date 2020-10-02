import java.math.BigInteger;

class DoubleFactorial {
    public static BigInteger calcDoubleFactorial(int n) {
        // type your java code here

        int input = n;
        BigInteger dubFactorial = BigInteger.ONE;
        while (input > 0) {
            dubFactorial = dubFactorial.multiply(BigInteger.valueOf(input));
            input -= 2;
        }

        return dubFactorial;
    }
}