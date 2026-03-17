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
                    saveList(gpuList);
                    userHasNotExited = false;
                    break;
                case 1:
                    addMenu(gpuList, input);
                    break;
                case 2:
                    searchGPU(gpuList, input);
                    break;
                case 3:
                    editMenu(gpuList, input);
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

                gpuList.add(new GPURegister(name, brand, vram, watts, price));
            }
        } catch (IOException e) {
            System.out.println("Error filling list...");
        }

        return gpuList;
    }

    public static void saveList(List<GPURegister> gpuList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("midterms/Week1/GPU.txt"))) {
            for (GPURegister gpu : gpuList) {
                writer.write(gpu.modelName);
                writer.newLine();
                writer.write(gpu.brand);
                writer.newLine();
                writer.write(String.valueOf(gpu.vram));
                writer.newLine();
                writer.write(String.valueOf(gpu.watts));
                writer.newLine();
                writer.write(String.valueOf(gpu.price));
                writer.newLine();
            }
            System.out.println("Data saved to file!");
        } catch (IOException e) {
            System.out.println("Error saving data...");
        }
    }

    public static void addMenu(List<GPURegister> gpuList, Scanner input) {
        System.out.println("""

                 ______________________________________
                |                                      |
                | [1] Add to front                     |
                | [2] Add to middle                    |
                | [3] Add to end                       |
                |______________________________________|
                """);

        int choice = InputMethods.inputInt("Choice: ", input, 1, 3);

        String modelName = InputMethods.inputString("Model Name: ", input);
        String brand = InputMethods.inputString("Brand: ", input);
        int vram = InputMethods.inputInt("Vram: ", input, 0);
        int watts = InputMethods.inputInt("Watts: ", input, 0);
        double price = InputMethods.inputDouble("Price: ", input, 0);

        GPURegister gpu = new GPURegister(modelName, brand, vram, watts, price);

        System.out.println("\nAdding GPU...");

        switch (choice) {
            case 1:
                gpuList.add(0, gpu);
                break;
            case 2:
                gpuList.add(gpuList.size() / 2, gpu);
                break;
            case 3:
                gpuList.add(gpu);
                break;
        }

        System.out.println("\nGPU Added!!!");
    }

    public static void searchGPU(List<GPURegister> gpuList, Scanner input) {
        String searching = InputMethods.inputString("What are you searching for?: ", input);

        GPURegister searchedGPU = null;
        for (GPURegister gpu : gpuList) {
            if (searching.equalsIgnoreCase(gpu.modelName)) {
                searchedGPU = gpu;
                break;
            }
        }

        if (searchedGPU != null) {
            System.out.printf("""
                    GPU found!
                     ______________________________________
                    |                                      |
                    | Name:  %-30s|
                    | Brand: %-30s|
                    | Vram:  %-30d|
                    | Watts: %-30d|
                    | Price: %-30.2f|
                    |______________________________________|
                    """, searchedGPU.modelName, searchedGPU.brand, searchedGPU.vram, searchedGPU.watts,
                    searchedGPU.price);
        } else {
            System.out.println("GPU does not exist!");
        }
    }

    public static void editMenu(List<GPURegister> gpuList, Scanner input) {
        displayList(gpuList);

        int index = InputMethods.inputInt("Enter index to edit: ", input, 0, gpuList.size() - 1);

        GPURegister gpu = gpuList.get(index);

        System.out.printf("""
                     ______________________________________
                    |                                      |
                    | index: %-30d|
                    | Name:  %-30s|
                    | Brand: %-30s|
                    | Vram:  %-30d|
                    | Watts: %-30d|
                    | Price: %-30.2f|
                    |______________________________________|
                    """, index, gpu.modelName, gpu.brand, gpu.vram, gpu.watts,
                    gpu.price);

        if (InputMethods.yesOrNo("Edit this GPU? (Y/N): ", input)) {
            String newName = InputMethods.inputString("New Model Name: ", input);
            String newBrand = InputMethods.inputString("New Brand: ", input);
            int newVram = InputMethods.inputInt("New Vram: ", input, 0);
            int newWatts = InputMethods.inputInt("New Watts: ", input, 0);
            double newPrice = InputMethods.inputDouble("New Price: ", input, 0);

            gpu.modelName = newName;
            gpu.brand = newBrand;
            gpu.vram = newVram;
            gpu.watts = newWatts;
            gpu.price = newPrice;

            saveList(gpuList);
            System.out.println("GPU updated!");
        } else {
            System.out.println("Edit Cancelled...");
        }
    }

    public static void displayList(List<GPURegister> gpuList) {
        System.out.println("Displaying all GPUs...\n");

        for (int i = 0; i < gpuList.size(); i++) {
            GPURegister displayGPU = gpuList.get(i);

            System.out.printf("""
                     ______________________________________
                    |                                      |
                    | index: [%d]                          |
                    | Name:  %-30s|
                    | Brand: %-30s|
                    | Vram:  %-30d|
                    | Watts: %-30d|
                    | Price: %-30.2f|
                    |______________________________________|
                    """, i, displayGPU.modelName, displayGPU.brand, displayGPU.vram, displayGPU.watts,
                    displayGPU.price);

        }
    }
}