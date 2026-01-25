import java.util.*;

public class Grocery {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String strProdName, strAnotherP, strCustomer;
        char cCustomer, cAnotherP;
        double dQty, dBill, dPrice, dTotal, dPay, dChange;

        do {
            dBill = 0;
            System.out.println("\nWelcome to the store!");

            do {
                System.out.print("Input product name: ");
                strProdName = input.nextLine();
                while (strProdName.length() == 0) {
                    System.out.print("Name cannot be empty: ");
                    strProdName = input.nextLine();
                }

                System.out.print("Input product price: ");
                while (true) {
                    if (input.hasNextDouble()) {
                        dPrice = input.nextDouble();
                        if (dPrice >= 0) {
                            break;
                        } else {
                            System.out.print("Price cannot be negative. Enter valid price: ");
                        }
                    } else {
                        System.out.print("Invalid input! Please enter a numerical price: ");
                        input.next();
                    }
                }

                System.out.print("Quantity: ");
                while (true) {
                    if (input.hasNextDouble()) {
                        dQty = input.nextDouble();
                        if (dQty >= 1) {
                            break;
                        } else {
                            System.out.print("Quantity must be at least 1. Enter valid quantity: ");
                        }
                    } else {
                        System.out.print("Invalid input! Please enter a numerical quantity: ");
                        input.next();
                    }
                }
                input.nextLine();

                dTotal = dQty * dPrice;

                System.out.println("Total: " + dTotal);

                dBill = dBill + dTotal;

                System.out.print("Another product Y/N? ");
                strAnotherP = input.nextLine();
                if (strAnotherP.length() > 0) {
                    cAnotherP = strAnotherP.charAt(0);
                } else {
                    cAnotherP = 'N';
                }
            } while ((cAnotherP == 'Y' || cAnotherP == 'y'));

            System.out.println("Bill: " + dBill);

            System.out.print("Payment: ");
            while (true) {
                if (input.hasNextDouble()) {
                    dPay = input.nextDouble();
                    if (dPay >= dBill) {
                        break;
                    } else {
                        System.out.print("Money is not enough! Enter at least " + dBill + ": ");
                    }
                } else {
                    System.out.print("Invalid input! Enter a numerical amount: ");
                    input.next();
                }
            }
            input.nextLine();

            dChange = dPay - dBill;
            System.out.println("Change: " + dChange);
            System.out.println("Thank you for shopping!");

            System.out.print("Another customer Y/N? ");
            strCustomer = input.nextLine();
            if (strCustomer.length() > 0) {
                cCustomer = strCustomer.charAt(0);
            } else {
                cCustomer = 'N';
            }
        } while (cCustomer == 'Y' || cCustomer == 'y');
        System.out.println("Grocery program terminating...");
        input.close();
    }
}