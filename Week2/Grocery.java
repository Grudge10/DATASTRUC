import java.util.*;

public class Grocery {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String strProdName, strReceipt;
        char cCustomer, cAnotherP;
        double dQty, dBill, dPrice, dTotal, dPay, dChange;

        do {
            dBill = 0;
            strReceipt = String.format("\n%-15s %-10s %-10s %-10s\n", "ITEM", "QTY", "PRICE", "TOTAL");
            strReceipt += "------------------------------------------------------------\n";

            System.out.println("\n****************************************");
            System.out.println("         WELCOME TO THE STORE          ");
            System.out.println("****************************************");

            do {
                System.out.println("\n--- Enter Item Details ---");
                strProdName = getValidString(input, "Product Name: ");
                dPrice = getValidDouble(input, "Price:        ", 0);
                dQty = getValidDouble(input, "Quantity:     ", 1);

                dTotal = dQty * dPrice;
                dBill = dBill + dTotal;

                strReceipt += String.format("%-15s %-10.2f %-10.2f %-10.2f\n", strProdName, dQty, dPrice, dTotal);

                System.out.printf("Current Item Total: %.2f%n", dTotal);

                cAnotherP = getValidYN(input, "Another product (Y/N)? ");
            } while (cAnotherP == 'Y');

            System.out.println("\n======================= FINAL RECEIPT ======================");
            System.out.print(strReceipt);
            System.out.println("------------------------------------------------------------");
            System.out.printf("%-37s %.2f\n", "GRAND TOTAL:", dBill);
            System.out.println("============================================================\n");

            dPay = getValidDouble(input, "Payment Amount: ", dBill);

            dChange = dPay - dBill;

            System.out.println("\n-----------------------------------");
            System.out.printf("CHANGE: %.2f%n", dChange);
            System.out.println("Thank you for shopping!");
            System.out.println("-----------------------------------");

            cCustomer = getValidYN(input, "\nAnother customer (Y/N)? ");
        } while (cCustomer == 'Y');

        System.out.println("\nGrocery program terminating...");
        input.close();
    }

    public static double getValidDouble(Scanner input, String prompt, double minValue) {
        double value;
        while (true) {
            System.out.print(prompt);
            if (input.hasNextDouble()) {
                value = input.nextDouble();
                input.nextLine();
                if (value >= minValue) {
                    return value;
                } else {
                    System.out.printf("Invalid! Must be at least %.2f: \n", minValue);
                }
            } else {
                System.out.println("Invalid input! Please enter a number: ");
                input.nextLine();
            }
        }
    }

    public static String getValidString(Scanner input, String prompt) {
        String s;
        while (true) {
            System.out.print(prompt);
            s = input.nextLine();
            if (s.length() > 0) {
                return s;
            }
            System.out.println("Input cannot be empty.");
        }
    }

    public static char getValidYN(Scanner input, String prompt) {
        String s;
        char c;
        while (true) {
            System.out.print(prompt);
            s = input.nextLine().toUpperCase();

            if (s.length() > 0) {
                c = s.charAt(0);
                if (c == 'Y' || c == 'N') {
                    return c;
                }
            }
            System.out.println("Error! Invalid input. Please enter 'Y' for Yes or 'N' for No.");
        }
    }
}