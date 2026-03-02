package midterms.Week1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
        GPURegister[] gpuArray = loadArray();

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

    public static GPURegister[] loadArray() {
        System.out.println("\nLoading GPU Array...");

        int totalGpus = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("midterms/Week1/GPU.txt"))) {
            while (reader.readLine() != null) {
                totalGpus++;
            }
        } catch (IOException e) {
            System.out.println("Error counting file...");
        }

        int size = totalGpus / 5;

        GPURegister[] gpuArray = new GPURegister[size];

        try (BufferedReader reader = new BufferedReader(new FileReader("midterms/Week1/GPU.txt"))) {
            for (int i = 0; i < size; i++) {
                String name = reader.readLine();
                String brand = reader.readLine();
                int vram = Integer.parseInt(reader.readLine());
                int watts = Integer.parseInt(reader.readLine());
                double price = Double.parseDouble(reader.readLine());

                gpuArray[i] = new GPURegister(name, brand, vram, watts, price);
            }
        } catch (IOException e) {
            System.out.println("Error filling array...");
        }

        return gpuArray;
    }

    public static void displayArray(GPURegister[] gpu) {
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
