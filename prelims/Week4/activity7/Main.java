package prelims.Week4.activity7;

import java.util.InputMismatchException;
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
            int choice = 0;
            while (true) {
                try {
                    choice = input.nextInt();
                    input.nextLine();

                    if (choice > 3 || choice < 0) {
                        System.out.println("[Error] Invalid Input! You must choose the following!");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("[Error] Invalid Input! You must enter a numerical value!");
                    input.nextLine();
                }
            }

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
