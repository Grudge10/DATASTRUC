import java.util.Scanner;

public class GPURegister {
    public static void main(String[] args) {
        String modelName, brand;
        int vram, watts, registeredGPU = 0;
        double price;

        do {
            modelName = inputString("Model name: ");
            brand = inputString("Brand name: ");
            vram = inputInt("Amount of VRAM: ");
            watts = inputInt("Watt consumption: ");
            price = inputDouble("GPU price: ");

            registeredGPU++;

            printSummary(modelName, brand, vram, watts, price);

            if (!yesOrNo("Would you like to register another GPU? (Y/N): "))
                break;
        } while (true);

        System.out.printf("You have registered %d GPU%s!\n", registeredGPU, registeredGPU > 1 ? "'s" : "");
    }

    public static Scanner input = new Scanner(System.in);

    public static String inputString(String message) {
        String s;
        while (true) {
            System.out.print(message);
            if (input.hasNextLine()) {
                s = input.nextLine().trim();
                if (!s.isEmpty()) {
                    break;
                }
            }
            System.out.println("\nInvalid Input! Retry\n");
        }
        return s;
    }

    public static int inputInt(String message) {
        int n;
        while (true) {
            System.out.print(message);
            if (input.hasNextInt()) {
                n = input.nextInt();
                input.nextLine();
                if (n >= 0)
                    break;
                else {
                    System.out.println("\nInvalid Input! Input must be positive\n");
                }
            } else {
                System.out.println("\nInvalid Input! You must input a numerical value\n");
                input.nextLine();
            }
        }
        return n;
    }

    public static double inputDouble(String message) {
        double d;
        while (true) {
            System.out.print(message);
            if (input.hasNextDouble()) {
                d = input.nextDouble();
                input.nextLine();
                if (d >= 0)
                    break;
                else {
                    System.out.println("\nInvalid Input! Input must be positive\n");
                }
            } else {
                System.out.println("\nInvalid Input! You must input a numerical value\n");
                input.nextLine();
            }
        }
        return d;
    }

    public static boolean yesOrNo(String message) {
        while (true) {
            System.out.print(message);
            if (input.hasNextLine()) {
                String choice = input.nextLine().trim().toUpperCase();
                if (!choice.isEmpty()) {
                    char yesOrNo = choice.charAt(0);
                    if (yesOrNo == 'Y') {
                        return true;
                    } else if (yesOrNo == 'N') {
                        return false;
                    }
                }
            }
            System.out.println("\nInvalid Input! You must input either Y or N\n");
        }
    }

    public static void printSummary(String modelName, String brand, int vram, int watts, double price) {
        System.out.printf("""

                You have registered a GPU!
                *********************************************
                *                  Summary                  *
                *********************************************
                *   Model name: %-28s*
                *   Brand: %-33s*
                *   VRAM: %-34d*
                *   Watts: %-33d*
                *   Price: %-33.2f*
                *********************************************

                """, modelName, brand, vram, watts, price);
    }
}