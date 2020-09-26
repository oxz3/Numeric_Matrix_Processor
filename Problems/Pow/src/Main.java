import java.lang.Math;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner keyboard = new Scanner(System.in);
        double a = keyboard.nextDouble();
        double b = keyboard.nextDouble();

        double result = Math.pow(a, b);
        System.out.println(result);
    }
}