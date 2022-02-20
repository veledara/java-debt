package hw.mishanya;

import java.util.Scanner;

public class Main {
    public static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter the number of persons: ");
        int n = IsCorrect();
        while (n < 2 || n > 50) {
            System.out.println("The number must be in brackets [2; 50]. Try again.");
            n = IsCorrect();
        }
    }

    public static boolean IsNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int IsCorrect() {
        String str = in.nextLine();
        while (!IsNumeric(str)) {
            System.out.println("Not a number entered. Try again:");
            str = in.nextLine();
        }
        return Integer.parseInt(str);
    }
}
