package prelims.Week4.activity7;

import java.util.Scanner;

public class GPURegister {
    public static void start(Scanner input) {
        String modelName, brand;
        int vram, watts, registeredGPU = 0;
        double price;

        do {
            modelName = InputMethods.inputString("Model name: ", input, 28);
            brand = InputMethods.inputString("Brand name: ", input, 28);
            vram = InputMethods.inputInt("Amount of VRAM: ", input);
            watts = InputMethods.inputInt("Watt consumption: ", input);
            price = InputMethods.inputDouble("GPU price: ", input);

            registeredGPU++;

            printSummary(modelName, brand, vram, watts, price);

            if (!InputMethods.yesOrNo("Would you like to register another GPU? (Y/N): ", input))
                break;
        } while (true);

        System.out.printf("You have registered %d GPU%s!\n", registeredGPU, registeredGPU > 1 ? "'s" : "");

        System.out.println("\nReturning to main menu...\n");
    }

    public static void printSummary(String modelName, String brand, int vram, int watts, double price) {
        System.out.printf("""


                You have registered a GPU!
                *********************************************
                *                  Summary                  *
                *********************************************
                *   Model name: %-28s*
                *   Brand:      %-28s*
                *   VRAM:       %-28d*
                *   Watts:      %-28d*
                *   Price:      %-28.2f*
                *********************************************


                """, modelName, brand, vram, watts, price);
    }
}
