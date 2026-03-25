package midterms.Week1;

import java.util.Scanner;

public class Movie {
    public static void start(Scanner input) {
        int comedy = 0, horror = 0, scifi = 0, drama = 0, cartoon = 0, dvdTotal = 0, vcdTotal = 0, tapeTotal = 0,
                rent = 0, sales = 0;

        InputMethods.print("""
            
             ______________________________________
            |                                      |
            |   LIAM'S SUPERMALL MOVIE RENTAL      |
            |          REGISTRATION                |
            |______________________________________|
            
            """);

        boolean choice = true;
        while (choice) {
            InputMethods.println("Registration");
            InputMethods.println("1. DVD \n2. VCD \n3. Tape");
            int code = InputMethods.inputInt("Choice: ", input, 1, 3);

            if (code == 1) {
                InputMethods.println("Type: DVD");
                dvdTotal += 1;
            } else if (code == 2) {
                InputMethods.println("Type: VCD");
                vcdTotal += 1;
            } else if (code == 3) {
                InputMethods.println("Type: Tape");
                tapeTotal += 1;
            }

            String title = InputMethods.inputString("Title: ", input);

            InputMethods.println("1. Horror\n2. Scifi\n3. Drama\n4. Comedy \n5. Cartoons");
            int category = InputMethods.inputInt("Category: ", input, 1, 5);

            if (category == 1)
                horror++;
            else if (category == 2)
                scifi++;
            else if (category == 3)
                drama++;
            else if (category == 4)
                comedy++;
            else if (category == 5)
                cartoon++;

            int minutes = InputMethods.inputInt("Minutes: ", input, 1);

            String genre = InputMethods.inputString("Setting: ", input);

            InputMethods.println("1. Rental");
            InputMethods.println("2. Sales");

            int transactionType = InputMethods.inputInt("Transaction: ", input, 1, 2);

            if (transactionType == 1)
                rent++;
            else if (transactionType == 2)
                sales++;

            double price = InputMethods.inputDouble("Price: ", input);

            if (!InputMethods.yesOrNo("Register Another? (Y/N): ", input))
                break;
        }

        InputMethods.print(String.format(
                "Reports\nFor rent: %d\nFor sale: %d\nVCD Total: %d\nDVD Total: %d\nTape Total: %d\nHorror Movies: %d \nScifi Movies: %d\nDrama Movies: %d\nComedy Movies: %d\nCartoons: %d\n",
                rent, sales, vcdTotal, dvdTotal, tapeTotal, horror, scifi, drama, comedy, cartoon));

        InputMethods.println("\nReturning to main menu...\n");
    }
}
