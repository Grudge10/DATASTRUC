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

    public GPURegister(String modelName, String brand, int vram, int watts, double price) {
        this.modelName = modelName;
        this.brand = brand;
        this.vram = vram;
        this.watts = watts;
        this.price = price;
    }

    public static void start(Scanner input) {
        List<GPURegister> gpu = new ArrayList<>();

        System.out.print("""

                 ______________________________________
                |                                      |
                |   LIAM'S SUPERMALL GPU REGISTRATION  |
                |           (Currently WIP!)           |
                |______________________________________|

                """);

        System.out.println("\nReturning to main menu...\n");
    }

    public List<GPURegister> loadList() {
        List<GPURegister> loadList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("midterms/Week1/GPU.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String brand = reader.readLine();
                int vram = Integer.parseInt(reader.readLine());
                int watts = Integer.parseInt(reader.readLine());
                double price = Double.parseDouble(reader.readLine());
                
                GPURegister loadGpu = new GPURegister(line, brand, vram, watts, price);
                loadList.add(loadGpu);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Starting from scratch!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return loadList;
    }
}
