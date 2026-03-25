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

            InputMethods.print("""
                
                 ______________________________________
                |                                      |
                |     LIAM'S SUPERMALL GROCERY         |
                |         DEPARTMENT STORE             |
                |______________________________________|
                
                """);

            do {
                InputMethods.println("\n--- Enter Item Details ---");
                strProdName = InputMethods.inputString("Product Name: ", input, 15);
                dPrice = InputMethods.inputDouble("Price: ", input);
                dQty = InputMethods.inputDouble("Quantity: ", input, 1);

                dTotal = dQty * dPrice;
                dBill = dBill + dTotal;

                receipt.append(String.format("%-15s %-10.2f %-10.2f %-10.2f\n", strProdName, dQty, dPrice, dTotal));

                InputMethods.print(String.format("Current Item Total: %.2f%n", dTotal));

                anotherProduct = InputMethods.yesOrNo("Another product? (Y/N): ", input);
            } while (anotherProduct);

            InputMethods.println("\n======================= FINAL RECEIPT ======================");
            InputMethods.print(receipt.toString());
            InputMethods.println("------------------------------------------------------------");
            InputMethods.print(String.format("%-37s %.2f\n", "GRAND TOTAL:", dBill));
            InputMethods.println("============================================================\n");

            dPay = InputMethods.inputDouble("Payment Amount: ", input, dBill);

            dChange = dPay - dBill;

            InputMethods.println("\n-----------------------------------");
            InputMethods.print(String.format("CHANGE: %.2f%n", dChange));
            InputMethods.println("Thank you for shopping!");
            InputMethods.println("-----------------------------------");

            anotherCustomer = InputMethods.yesOrNo("\nAnother customer (Y/N)? ", input);
        } while (anotherCustomer);

        InputMethods.println("\nReturning to main menu...\n");
    }
}