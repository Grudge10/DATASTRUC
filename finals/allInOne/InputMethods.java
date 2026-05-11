package finals.allInOne;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class InputMethods {
    private static BufferedWriter transcriptWriter;

    public static String inputString(String message, Scanner input) {
        String s;
        while (true) {
            print(message);
            if (input.hasNextLine()) {
                s = input.nextLine().trim();
                logInput(s);
                if (!s.isEmpty())
                    return s;
            }
            println("\nInvalid Input! Retry\n");
        }
    }

    public static String inputString(String message, Scanner input, int length) {
        String s;
        while (true) {
            print(message);
            if (input.hasNextLine()) {
                s = input.nextLine().trim();
                logInput(s);
                if (!s.isEmpty())
                    return s.length() > length ? s.substring(0, length) : s;
            }
            println("\nInvalid Input! Retry\n");
        }
    }

    public static int inputInt(String message, Scanner input) {
        int n;
        while (true) {
            print(message);
            if (input.hasNextInt()) {
                n = input.nextInt();
                input.nextLine();

                logInput(String.valueOf(n));
                if (n >= 0)
                    return n;
                else
                    println("\nInvalid Input! Input must be at least 0!\n");
            } else {
                println("\nInvalid Input! You must input a numerical value\n");
                input.nextLine();
            }
        }
    }

    public static int inputInt(String message, Scanner input, int min) {
        int n;
        while (true) {
            print(message);
            if (input.hasNextInt()) {
                n = input.nextInt();
                input.nextLine();

                logInput(String.valueOf(n));
                if (n >= min)
                    return n;
                else
                    println("\nInvalid Input! Input must be at least " + min + "!\n");
            } else {
                println("\nInvalid Input! You must input a numerical value\n");
                input.nextLine();
            }
        }
    }

    public static int inputInt(String message, Scanner input, int min, int max) {
        int n;
        while (true) {
            print(message);
            if (input.hasNextInt()) {
                n = input.nextInt();
                input.nextLine();

                logInput(String.valueOf(n));
                if (n >= min && n <= max)
                    return n;
                else
                    println(
                            "\nInvalid Input! Input must be between " + min + " and " + max + " (inclusive)!\n");
            } else {
                println("\nInvalid Input! You must input a numerical value\n");
                input.nextLine();
            }
        }
    }

    public static double inputDouble(String message, Scanner input) {
        double d;
        while (true) {
            print(message);
            if (input.hasNextDouble()) {
                d = input.nextDouble();
                input.nextLine();

                logInput(String.valueOf(d));
                if (d >= 0)
                    return d;
                else
                    println("\nInvalid Input! Input must be at least 0!\n");
            } else {
                println("\nInvalid Input! You must input a numerical value\n");
                input.nextLine();
            }
        }
    }

    public static double inputDouble(String message, Scanner input, double min) {
        double d;
        while (true) {
            print(message);
            if (input.hasNextDouble()) {
                d = input.nextDouble();
                input.nextLine();

                logInput(String.valueOf(d));
                if (d >= min)
                    return d;
                else
                    println("\nInvalid Input! Input must be at least " + min + "!\n");
            } else {
                println("\nInvalid Input! You must input a numerical value\n");
                input.nextLine();
            }
        }
    }

    public static double inputDouble(String message, Scanner input, double min, double max) {
        double d;
        while (true) {
            print(message);
            if (input.hasNextDouble()) {
                d = input.nextDouble();
                input.nextLine();

                logInput(String.valueOf(d));
                if (d >= min && d <= max)
                    return d;
                else
                    println(
                            "\nInvalid Input! Input must be between " + min + " and " + max + " (inclusive)!\n");
            } else {
                println("\nInvalid Input! You must input a numerical value\n");
                input.nextLine();
            }
        }
    }

    public static boolean yesOrNo(String message, Scanner input) {
        while (true) {
            print(message);
            if (input.hasNextLine()) {
                String choice = input.nextLine().trim().toUpperCase();

                logInput(choice);
                if (!choice.isEmpty()) {
                    char yesOrNo = choice.charAt(0);
                    if (yesOrNo == 'Y')
                        return true;
                    else if (yesOrNo == 'N')
                        return false;
                }
            }
            println("\nInvalid Input! You must input either Y or N\n");
        }
    }

    public static void openTranscript(String fileName) {
        try {
            transcriptWriter = new BufferedWriter(new FileWriter(fileName));
        } catch (IOException e) {
            System.out.println("Warning: Could not open transcript file.");
        }
    }

    public static void closeTranscript() {
        try {
            if (transcriptWriter != null) {
                transcriptWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void print(String text) {
        System.out.print(text);
        try {
            if (transcriptWriter != null) {
                transcriptWriter.write(text);
                transcriptWriter.flush();
            }
        } catch (IOException e) {}
    }

    public static void println(String text) {
        System.out.println(text);
        try {
            if (transcriptWriter != null) {
                transcriptWriter.write(text);
                transcriptWriter.newLine();
                transcriptWriter.flush();
            }
        } catch (IOException e) {}
    }

    public static void logInput(String text) {
        try {
            if (transcriptWriter != null) {
                transcriptWriter.write(text);
                transcriptWriter.newLine();
                transcriptWriter.flush();
            }
        } catch (IOException e) {}
    }
}
