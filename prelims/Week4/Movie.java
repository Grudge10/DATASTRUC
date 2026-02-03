package prelims.Week4;

import java.util.*;
import java.io.*;

public class Movie {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        File file = new File("prelims/Week4/Summary.txt");
        FileWriter writer = new FileWriter(file);
        int comedy = 0, horror = 0, scifi = 0, drama = 0, cartoon = 0, dvdTotal = 0, vcdTotal = 0, tapeTotal = 0,
                rent = 0, sales = 0;
        boolean choice = true;

        while (choice) {
            System.out.printf("""
                    Registration
                    1. DVD
                    2. VCD
                    3. Tape
                    Choice: """);
            writer.write("Registration\n" + "1. DVD\n" + "2. VCD\n" + "3. Tape\n" + "Choice: ");
            int code = input.nextInt();
            input.nextLine();
            writer.write(code + "\n");

            if (code == 1) {
                System.out.println("Type: DVD");
                writer.write("Type: DVD\n");
                dvdTotal += 1;
            } else if (code == 2) {
                System.out.println("Type: VCD");
                writer.write("Type: VCD\n");
                vcdTotal += 1;
            } else if (code == 3) {
                System.out.println("Type: Tape");
                writer.write("Type: Tape\n");
                tapeTotal += 1;
            }

            System.out.print("Title: ");
            writer.write("Title: ");
            String title = input.nextLine();
            writer.write(title + "\n");

            System.out.print("1. Horror\n2. Scifi\n3. Drama\n4. Comedy \n5. Cartoons\nCategory: ");
            writer.write("1. Horror\n2. Scifi\n3. Drama\n4. Comedy \n5. Cartoons\nCategory: ");
            int category = input.nextInt();
            input.nextLine();
            writer.write(category + "\n");

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
            writer.write("Minutes: ");
            int minutes = input.nextInt();
            input.nextLine();
            writer.write(minutes + "\n");

            System.out.print("Setting: ");
            writer.write("Setting: ");
            String genre = input.nextLine();
            writer.write(genre + "\n");

            System.out.println("1. Rental\n2. Sales");
            writer.write("1. Rental\n2. Sales\n");

            System.out.print("Transaction: ");
            writer.write("Transaction: ");
            int transactionType = input.nextInt();
            input.nextLine();
            writer.write(transactionType + "\n");

            if (transactionType == 1) {
                rent++;
            } else if (transactionType == 2) {
                sales++;
            }

            System.out.print("Price: ");
            writer.write("Price: ");
            double price = input.nextDouble();
            input.nextLine();
            writer.write(price + "\n");

            System.out.print("Register another? ");
            writer.write("Register another? ");
            String userChoice = input.nextLine().toLowerCase();
            char charUserChoice = userChoice.charAt(0);
            writer.write(userChoice + "\n");

            choice = charUserChoice == 'n' ? false : true;
        }

        System.out.printf(
                "Reports\nFor rent: %d\nFor sale: %d\nVCD Total: %d\nDVD Total: %d\nTape Total: %d\nHorror Movies: %d \nScifi Movies: %d\nDrama Movies: %d\nComedy Movies: %d\nCartoons: %d\n",
                rent, sales, vcdTotal, dvdTotal, tapeTotal, horror, scifi, drama, comedy, cartoon);
        writer.write("Reports\nFor rent: " + rent + "\nFor sale: " + sales + "\nVCD Total: " + vcdTotal
                + "\nDVD Total: " + dvdTotal + "\nTape Total: " + tapeTotal + "\nHorror Movies: " + horror
                + "\nScifi Movies: " + scifi + "\nDrama Movies: " + drama + "\nComedy Movies: " + comedy
                + "\nCartoons: " + cartoon + "\n");

        input.close();
        writer.close();
    }
}