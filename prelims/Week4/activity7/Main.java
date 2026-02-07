package prelims.Week4.activity7;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to Liam's Supermall!");
        System.out.println("Remember, Keep it wholesome!");

        while (true) {
            System.out.print("""

                    MAIN MENU:
                    [1] Grocery
                    [2] Movie Rental
                    [3] GPU Registration
                    [0] Exit
                    Choice:  """);
            int choice = 0;
            try {
                choice = input.nextInt();
                input.nextLine();

                if (choice > 3 || choice < 0) {
                    System.out.println("[Error] Invalid Input! You must choose the following!");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("[Error] Invalid Input! You must enter a numerical value!");
            }

            if (choice == 1) {
                Grocery.start();
            } else if (choice == 2) {
                Movie.start();
            } else if (choice == 3) {
                GPURegister.start();
            } else {
                System.out.println("Thank you for coming to Liam's Supermall! GOODBYE!!!");
                break;
            }
        }

        input.close();
    }
}
