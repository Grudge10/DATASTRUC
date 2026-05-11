package finals.allInOne;

import java.io.*;
import java.util.*;

public class GPURegister {
    private String modelName;
    private String brand;
    private int vram;
    private int watts;
    private double price;

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
            InputMethods.print("""

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
            InputMethods.println("");

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
                    deleteMenu(gpuList, input);
                    break;
                case 5:
                    sortMenu(gpuList, input);
                    break;
                case 6:
                    displayList(gpuList);
                    break;
            }
        }

        InputMethods.println("\nReturning to main menu...\n");
    }

    public static List<GPURegister> loadList() {
        InputMethods.println("\nLoading GPU Array...");

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
        } catch (IOException | NumberFormatException e) {
            InputMethods.println("Error filling list...");
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
            InputMethods.println("Data saved to file!");
        } catch (IOException e) {
            InputMethods.println("Error saving data...");
        }
    }

    public static void addMenu(List<GPURegister> gpuList, Scanner input) {
        InputMethods.println("""

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

        InputMethods.println("\nAdding GPU...");

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

        InputMethods.println("\nGPU Added!!!");
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
            InputMethods.print(String.format("""
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
                    searchedGPU.price));
        } else {
            InputMethods.println("GPU does not exist!");
        }
    }

    public static void editMenu(List<GPURegister> gpuList, Scanner input) {
        if (gpuList.isEmpty()) {
            InputMethods.println("The registry is empty! Nothing to edit...");
            return;
        }

        displayList(gpuList);

        int index = InputMethods.inputInt("Enter index to edit: ", input, 0, gpuList.size() - 1);

        GPURegister gpu = gpuList.get(index);

        InputMethods.print(String.format("""
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
                gpu.price));

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
            InputMethods.println("GPU updated!");
        } else {
            InputMethods.println("Edit Cancelled...");
        }
    }

    public static void deleteMenu(List<GPURegister> gpuList, Scanner input) {
        if (gpuList.isEmpty()) {
            InputMethods.println("The registry is empty! Nothing to delete...");
            return;
        }

        displayList(gpuList);

        int index = InputMethods.inputInt("Enter index to delete: ", input, 0, gpuList.size() - 1);

        GPURegister gpu = gpuList.get(index);
        InputMethods.println("You are about to delete: " + gpu.modelName);

        if (InputMethods.yesOrNo("Are you sure you want to delete this GPU? (Y/N): ", input)) {
            gpuList.remove(index);

            saveList(gpuList);
            InputMethods.println("GPU Deleted!");
        } else {
            InputMethods.println("Deletion Cancelled...");
        }
    }

    public static void sortMenu(List<GPURegister> gpuList, Scanner input) {
        if (gpuList.isEmpty()) {
            InputMethods.println("Nothing to sort!");
            return;
        }

        InputMethods.print("""
                 ______________________________________
                |                                      |
                | [1] Ascending Order                  |
                | [2] Descending Order                 |
                |______________________________________|

                """);

        int choice = InputMethods.inputInt("Choice: ", input, 1, 2);

        for (int i = 0; i < gpuList.size() - 1; i++) {
            for (int j = 0; j < gpuList.size() - i - 1; j++) {

                GPURegister left = gpuList.get(j);
                GPURegister right = gpuList.get(j + 1);

                boolean shouldSwap = false;

                if (choice == 1) {
                    shouldSwap = left.price > right.price;
                } else {
                    shouldSwap = left.price < right.price;
                }

                if (shouldSwap) {
                    gpuList.set(j, right);
                    gpuList.set(j + 1, left);
                }
            }
        }

        InputMethods.println("GPU list sorted!");
        saveList(gpuList);
    }

    public static void displayList(List<GPURegister> gpuList) {
        InputMethods.println("Displaying all GPUs...\n");

        for (int i = 0; i < gpuList.size(); i++) {
            GPURegister displayGPU = gpuList.get(i);

            InputMethods.print(String.format("""
                     ______________________________________
                    |                                      |
                    | index: %-30d|
                    | Name:  %-30s|
                    | Brand: %-30s|
                    | Vram:  %-30d|
                    | Watts: %-30d|
                    | Price: %-30.2f|
                    |______________________________________|
                    """, i, displayGPU.modelName, displayGPU.brand, displayGPU.vram, displayGPU.watts,
                    displayGPU.price));

        }
    }
}
