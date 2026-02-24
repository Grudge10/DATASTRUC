package midterms.Week1;

import java.util.Scanner;

public class Grocery {
    public static void start(Scanner input) {
        double dQty, dBill, dPrice, dTotal, dPay, dChange;
        String strProdName;
        boolean anotherProduct, anotherCustomer;

        do {
            dBill = 0;
            StringBuilder receipt = new StringBuilder();
            receipt.append(String.format("\n%-15s %-10s %-10s %-10s\n", "ITEM", "QTY", "PRICE", "TOTAL"));
            receipt.append("------------------------------------------------------------\n");

            System.out.print("""
                
                 ______________________________________
                |                                      |
                |     LIAM'S SUPERMALL GROCERY         |
                |         DEPARTMENT STORE             |
                |______________________________________|
                
                """);

            do {
                System.out.println("\n--- Enter Item Details ---");
                strProdName = InputMethods.inputString("Product Name: ", input, 15);
                dPrice = InputMethods.inputDouble("Price: ", input);
                dQty = InputMethods.inputDouble("Quantity: ", input, 1);

                dTotal = dQty * dPrice;
                dBill = dBill + dTotal;

                receipt.append(String.format("%-15s %-10.2f %-10.2f %-10.2f\n", strProdName, dQty, dPrice, dTotal));

                System.out.printf("Current Item Total: %.2f%n", dTotal);

                anotherProduct = InputMethods.yesOrNo("Another product? (Y/N): ", input);
            } while (anotherProduct);

            System.out.println("\n======================= FINAL RECEIPT ======================");
            System.out.print(receipt.toString());
            System.out.println("------------------------------------------------------------");
            System.out.printf("%-37s %.2f\n", "GRAND TOTAL:", dBill);
            System.out.println("============================================================\n");

            dPay = InputMethods.inputDouble("Payment Amount: ", input, dBill);

            dChange = dPay - dBill;

            System.out.println("\n-----------------------------------");
            System.out.printf("CHANGE: %.2f%n", dChange);
            System.out.println("Thank you for shopping!");
            System.out.println("-----------------------------------");

            anotherCustomer = InputMethods.yesOrNo("\nAnother customer (Y/N)? ", input);
        } while (anotherCustomer);

        System.out.println("\nReturning to main menu...\n");
    }
}