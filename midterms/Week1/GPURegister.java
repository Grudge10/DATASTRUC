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
        List<GPURegister> gpuArray = loadArray();

        System.out.print("""

                 ______________________________________
                |                                      |
                |   LIAM'S SUPERMALL GPU REGISTRATION  |
                |           (Currently WIP!)           |
                |______________________________________|

                """);

        displayArray(gpuArray);

        System.out.println("\nReturning to main menu...\n");
    }

    public static List<GPURegister> loadArray() {
        System.out.println("\nLoading GPU Array...");

        List<GPURegister> gpuArray = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("midterms/Week1/GPU.txt"))) {
            String name;
            while ((name = reader.readLine()) != null) {
                String brand = reader.readLine();
                int vram = Integer.parseInt(reader.readLine());
                int watts = Integer.parseInt(reader.readLine());
                double price = Double.parseDouble(reader.readLine());

                gpuArray.add(new GPURegister(name, brand, vram, watts, price));
            }
        } catch (IOException e) {
            System.out.println("Error filling array...");
        }

        return gpuArray;
    }

    public static void displayArray(List<GPURegister> gpu) {
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