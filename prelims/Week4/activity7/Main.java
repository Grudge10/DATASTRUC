package prelims.Week4.activity7;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to Liam's Supermall!");
        System.out.println("Remember, Keep it wholesome!");

        while (true) {
            System.out.print("""

                        MAIN MENU:
                        [1] Grocery
                        [2] Movie Rental
                        [3] GPU Registration
                        [0] Exit

                    """);
            int choice = InputMethods.inputInt("Choice: ", input, 0, 3);

            if (choice == 1) {
                Grocery.start(input);
            } else if (choice == 2) {
                Movie.start(input);
            } else if (choice == 3) {
                GPURegister.start(input);
            } else {
                System.out.println("Thank you for coming to Liam's Supermall! GOODBYE!!!");
                break;
            }
        }

        input.close();
    }

    public static Scanner input = new Scanner(System.in);
}
