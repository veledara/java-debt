package org.homework.methods;

import java.util.Scanner;

public class Methods {
    public static Scanner in = new Scanner(System.in);
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int isPortCorrect() {
        String str = in.nextLine();
        while (!isNumeric(str)) {
            System.out.println("Введено не число. Попробуйте еще раз.");
            str = in.nextLine();
        }
        return Integer.parseInt(str);
    }
}
