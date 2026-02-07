package prelims.Week4.activity7;

import java.util.*;

public class Movie {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int comedy = 0, horror = 0, scifi = 0, drama = 0, cartoon = 0, dvdTotal = 0, vcdTotal = 0, tapeTotal = 0,
                rent = 0, sales = 0;
        boolean choice = true;
        while (choice) {
            System.out.println("Registration");
            System.out.println("1. DVD \n2. VCD \n3. Tape");
            System.out.print("Choice: ");
            int code = input.nextInt();
            input.nextLine();

            if (code == 1) {
                System.out.println("Type: DVD");
                dvdTotal += 1;
            } else if (code == 2) {
                System.out.println("Type: VCD");
                vcdTotal += 1;
            } else if (code == 3) {
                System.out.println("Type: Tape");
                tapeTotal += 1;
            }
            System.out.print("Title: ");
            String title = input.nextLine();
            System.out.println("1. Horror\n2. Scifi\n3. Drama\n4. Comedy \n5. Cartoons");
            System.out.print("Category: ");
            int category = input.nextInt();
            input.nextLine();
            if (category == 1) {
                horror++;
            } else if (category == 2) {
                scifi++;
            } else if (category == 3) {
                drama++;
            } else if (category == 4) {
                comedy++;
            } else if (category == 5) {
                cartoon++;
            }

            System.out.print("Minutes: ");
            int minutes = input.nextInt();
            input.nextLine();

            System.out.print("Setting: ");
            String genre = input.nextLine();

            System.out.println("1. Rental");
            System.out.println("2. Sales");

            System.out.print("Transaction: ");
            int transactionType = input.nextInt();
            input.nextLine();
            if (transactionType == 1) {
                rent++;
            } else if (transactionType == 2) {
                sales++;
            }

            System.out.print("Price: ");
            double price = input.nextDouble();
            input.nextLine();

            System.out.print("Register another? ");
            String userChoice = input.nextLine().toLowerCase();
            char charUserChoice = userChoice.charAt(0);

            choice = charUserChoice == 'n' ? false : true;
        }

        System.out.printf(
                "Reports\nFor rent: %d\nFor sale: %d\nVCD Total: %d\nDVD Total: %d\nTape Total: %d\nHorror Movies: %d \nScifi Movies: %d\nDrama Movies: %d\nComedy Movies: %d\nCartoons: %d\n",
                rent, sales, vcdTotal, dvdTotal, tapeTotal, horror, scifi, drama, comedy, cartoon);
    }
}
