package hw.mishanya;

import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

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
