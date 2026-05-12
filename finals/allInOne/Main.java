package finals.allInOne;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InputMethods.openTranscript("finals/allInOne/output.txt");

        InputMethods.print("""

                 ______________________________________
                |                                      |
                |     Welcome to Liam's Supermall!     |
                |    (Remember, keep it wholesome!)    |
                |______________________________________|
                        """);

        while (true) {
            InputMethods.print("""
                     ______________________________________
                    |                                      |
                    |  MAIN MENU:                          |
                    |                                      |
                    |  [1] Grocery Department Store        |
                    |  [2] Movie Rental Registration       |
                    |  [3] GPU Registration                |
                    |  [4] Quizzer                         |
                    |  [0] Exit                            |
                    |______________________________________|

                            """);
            int choice = InputMethods.inputInt(
                    "Choice: ", input, 0, 4);

            if (choice == 1) {
                InputMethods.println("\nLoading Grocery Department Store...");
                Grocery.start(input);
            } else if (choice == 2) {
                InputMethods.println("\nLoading Movie Rental Registration...");
                Movie.start(input);
            } else if (choice == 3) {
                InputMethods.println("\nLoading GPU Registration...");
                GPURegister.start(input);
            } else if (choice == 4) {
                InputMethods.println("\nLoading Quizzer...");
                Quizzer.start(input);
            }else {
                InputMethods.println("\nThank you for coming to Liam's Supermall! GOODBYE!!!");
                break;
            }
        }

        input.close();
        InputMethods.closeTranscript();
    }

    public static Scanner input = new Scanner(System.in);
}
