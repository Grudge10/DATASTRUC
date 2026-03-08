package midterms.Week1;

import java.io.*;
import java.util.*;

public class GPURegister {
    public String modelName, brand;
    public int vram, watts;
    public double price;

    public GPURegister() {
    }

    public GPURegister(String modelName, String brand, int vram, int watts, double price) {
        this.modelName = modelName;
        this.brand = brand;
        this.vram = vram;
        this.watts = watts;
        this.price = price;
    }

    public static void start(Scanner input) {
        List<GPURegister> gpuList = loadList();

        boolean userHasNotExited = true;
        while (userHasNotExited) {
            System.out.print("""

                     ______________________________________
                    |                                      |
                    |   LIAM'S SUPERMALL GPU REGISTRATION  |
                    |______________________________________|
                     ______________________________________
                    |                                      |
                    | OPTIONS:                             |
                    | [1] Add GPU                          |
                    | [2] Search for GPU                   |
                    | [3] Edit GPU Info                    |
                    | [4] Delete GPU                       |
                    | [5] Sort GPU                         |
                    | [6] List GPUs                        |
                    | [0] Exit                             |
                    |______________________________________|

                    """);

            int choice = InputMethods.inputInt("Choice: ", input, 0, 6);
            System.out.println();

            switch (choice) {
                case 0:
                    userHasNotExited = false;
                    break;
                case 1:
                    System.out.println("Adding GPU...\n");
                    System.out.println("Done!");
                    break;
                case 2:
                    System.out.println("Searching for GPU...\n");
                    System.out.println("Done!");
                    break;
                case 3:
                    System.out.println("Editing GPU Info...\n");
                    System.out.println("Done!");
                    break;
                case 4:
                    System.out.println("Deleting GPU...\n");
                    System.out.println("Done!");
                    break;
                case 5:
                    System.out.println("Sorting GPU");
                    System.out.println("Done!");
                    break;
                case 6:
                    displayList(gpuList);
                    break;
            }
        }

        System.out.println("\nReturning to main menu...\n");
    }

    public static List<GPURegister> loadList() {
        System.out.println("\nLoading GPU Array...");

        List<GPURegister> gpuList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("midterms/Week1/GPU.txt"))) {
            String name;
            while ((name = reader.readLine()) != null) {
                String brand = reader.readLine();
                int vram = Integer.parseInt(reader.readLine());
                int watts = Integer.parseInt(reader.readLine());
                double price = Double.parseDouble(reader.readLine());

                GPURegister loadGPU = new GPURegister(name, brand, vram, watts, price);
                gpuList.add(loadGPU);
            }
        } catch (IOException e) {
            System.out.println("Error filling array...");
        }

        return gpuList;
    }

    public static void displayList(List<GPURegister> gpu) {
        System.out.println("Displaying all GPUs...\n");

        for (GPURegister displayGpu : gpu) {
            System.out.printf("""
                     ______________________________________
                    |                                      |
                    | Name:  %-30s|
                    | Brand: %-30s|
                    | Vram:  %-30d|
                    | Watts: %-30d|
                    | Price: %-30.2f|
                    |______________________________________|
                    """, displayGpu.modelName, displayGpu.brand, displayGpu.vram, displayGpu.watts, displayGpu.price);
        }
    }
}
