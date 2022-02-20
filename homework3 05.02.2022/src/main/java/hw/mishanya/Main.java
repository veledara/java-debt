package hw.mishanya;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<Person> group = new ArrayList<Person>();
        System.out.println("Enter the number of persons: ");
        int n = IsInt();
        while (n < 2 || n > 50) {
            System.out.println("The number must be in brackets [2; 50]. Try again.");
            n = IsInt();
        }
        for (int i = 0; i < n; i++) {
            System.out.println("Enter the " + (i + 1) + " person lastname:");
            String lastname = in.nextLine();
            System.out.println("Enter the " + (i + 1) + " person name:");
            String name = in.nextLine();
            System.out.println("Enter the " + (i + 1) + " person age:");
            Byte age = IsByte();
            group.add(new Person(lastname, name, age));
        }
        group.sort(Comparator.comparing(Person::getLastname)
                .thenComparing(Person::getFirstname)
                .thenComparing(Person::getAge, Comparator.reverseOrder()));
        for (Person person :
                group) {
            System.out.println(person);
        }
    }

    public static boolean IsNumeric(String str) {
        try {
            Integer.parseInt(str);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    public static int IsInt() {
        String str = in.nextLine();
        while (IsNumeric(str)) {
            System.out.println("Not a number entered. Try again:");
            str = in.nextLine();
        }
        return Integer.parseInt(str);
    }

    public static Byte IsByte() {
        String str = in.nextLine();
        while (IsNumeric(str)) {
            System.out.println("Not a number entered. Try again:");
            str = in.nextLine();
        }
        return Byte.parseByte(str);
    }
}
