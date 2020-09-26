import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner keyboard = new Scanner(System.in);

        double a = keyboard.nextDouble();
        double b = keyboard.nextDouble();
        double c = keyboard.nextDouble();

        double pos = (-b + Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
        double neg = (-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);

        double low = Math.min(pos, neg);
        double high = Math.max(pos, neg);

        System.out.print(low + " " + high);
    }
}