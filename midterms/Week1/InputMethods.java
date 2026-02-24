package midterms.Week1;

import java.util.Scanner;

public class InputMethods {
    public static String inputString(String message, Scanner input) {
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

    public static String inputString(String message, Scanner input, int length) {
        String s;
        while (true) {
            System.out.print(message);
            if (input.hasNextLine()) {
                s = input.nextLine().trim();
                if (!s.isEmpty())
                    return s.length() > length ? s.substring(0, length) : s;
            }
            System.out.println("\nInvalid Input! Retry\n");
        }
    }

    public static int inputInt(String message, Scanner input) {
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

    public static int inputInt(String message, Scanner input, int min) {
        int n;
        while (true) {
            System.out.print(message);
            if (input.hasNextInt()) {
                n = input.nextInt();
                input.nextLine();
                if (n >= min)
                    return n;
                else
                    System.out.println("\nInvalid Input! Input must be at least " + min + "!\n");
            } else {
                System.out.println("\nInvalid Input! You must input a numerical value\n");
                input.nextLine();
            }
        }
    }

    public static int inputInt(String message, Scanner input, int min, int max) {
        int n;
        while (true) {
            System.out.print(message);
            if (input.hasNextInt()) {
                n = input.nextInt();
                input.nextLine();
                if (n >= min && n <= max)
                    return n;
                else
                    System.out.println(
                            "\nInvalid Input! Input must be between " + min + " and " + max + " (inclusive)!\n");
            } else {
                System.out.println("\nInvalid Input! You must input a numerical value\n");
                input.nextLine();
            }
        }
    }

    public static double inputDouble(String message, Scanner input) {
        double d;
        while (true) {
            System.out.print(message);
            if (input.hasNextDouble()) {
                d = input.nextDouble();
                input.nextLine();
                if (d >= 0)
                    return d;
                else
                    System.out.println("\nInvalid Input! Input must be at least 0!\n");
            } else {
                System.out.println("\nInvalid Input! You must input a numerical value\n");
                input.nextLine();
            }
        }
    }

    public static double inputDouble(String message, Scanner input, double min) {
        double d;
        while (true) {
            System.out.print(message);
            if (input.hasNextDouble()) {
                d = input.nextDouble();
                input.nextLine();
                if (d >= min)
                    return d;
                else
                    System.out.println("\nInvalid Input! Input must be at least " + min + "!\n");
            } else {
                System.out.println("\nInvalid Input! You must input a numerical value\n");
                input.nextLine();
            }
        }
    }

    public static double inputDouble(String message, Scanner input, double min, double max) {
        double d;
        while (true) {
            System.out.print(message);
            if (input.hasNextDouble()) {
                d = input.nextDouble();
                input.nextLine();
                if (d >= min && d <= max)
                    return d;
                else
                    System.out.println(
                            "\nInvalid Input! Input must be between " + min + " and " + max + " (inclusive)!\n");
            } else {
                System.out.println("\nInvalid Input! You must input a numerical value\n");
                input.nextLine();
            }
        }
    }

    public static boolean yesOrNo(String message, Scanner input) {
        while (true) {
            System.out.print(message);
            if (input.hasNextLine()) {
                String choice = input.nextLine().trim().toUpperCase();
                if (!choice.isEmpty()) {
                    char yesOrNo = choice.charAt(0);
                    if (yesOrNo == 'Y')
                        return true;
                    else if (yesOrNo == 'N')
                        return false;
                }
            }
            System.out.println("\nInvalid Input! You must input either Y or N\n");
        }
    }
}
