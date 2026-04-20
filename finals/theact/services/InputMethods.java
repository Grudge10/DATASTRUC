package finals.theact.services;

import java.util.Scanner;

public class InputMethods {
    private static Scanner input = new Scanner(System.in);

    public static String inputString(String message) {
        String s;
        while (true) {
            System.out.print(message);
            if (input.hasNextLine()) {
                s = input.nextLine().trim();
                if (!s.isEmpty())
                    return s;
            }
            System.out.println("\nInvalid Input! Retry\n");
        }
    }

    public static int inputInt(String message) {
        int n;
        while (true) {
            System.out.print(message);
            if (input.hasNextInt()) {
                n = input.nextInt();
                input.nextLine();
                if (n >= 0)
                    return n;
                else
                    System.out.println("\nInvalid Input! Input must be at least 0!\n");
            } else {
                System.out.println("\nInvalid Input! You must input a numerical value\n");
                input.nextLine();
            }
        }
    }

    public static int inputInt(String message, int min, int max) {
        int n;
        while (true) {
            System.out.print(message);
            if (input.hasNextInt()) {
                n = input.nextInt();
                input.nextLine();
                if (n >= min && n <= max)
                    return n;
                else
                    System.out.println("\nInvalid Input! Input must be between " + min + " and " + max + " (inclusive)!\n");
            } else {
                System.out.println("\nInvalid Input! You must input a numerical value\n");
                input.nextLine();
            }
        }
    }
}
