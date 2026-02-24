package midterms.Week1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("""

                 ______________________________________
                |                                      |
                |     Welcome to Liam's Supermall!     |
                |    (Remember, keep it wholesome!)    |
                |______________________________________|
                        """);

        while (true) {
            System.out.print("""
                     ______________________________________
                    |                                      |
                    |  MAIN MENU:                          |
                    |                                      |
                    |  [1] Grocery Department Store        |
                    |  [2] Movie Rental Registration       |
                    |  [3] GPU Registration                |
                    |  [0] Exit                            |
                    |______________________________________|

                            """);
            int choice = InputMethods.inputInt(
                    "Choice: ", input, 0, 3);

            if (choice == 1) {
                System.out.println("\nLoading Grocery Department Store...");
                Grocery.start(input);
            } else if (choice == 2) {
                System.out.println("\nLoading Movie Rental Registration...");
                Movie.start(input);
            } else if (choice == 3) {
                System.out.println("\nLoading GPU Registration...");
                GPURegister.start(input);
            } else {
                System.out.println("\nThank you for coming to Liam's Supermall! GOODBYE!!!");
                break;
            }
        }

        input.close();
    }

    public static Scanner input = new Scanner(System.in);
}
