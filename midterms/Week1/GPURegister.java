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

    public void loadList(List<GPURegister> gpu) {
        try (BufferedReader reader = new BufferedReader(new FileReader("midterms/Week1/GPU.txt"))) {
            String line;
            int counter = 1;
            while ((line = reader.readLine()) != null) {
                GPURegister loadGpu = new GPURegister();

                switch (counter) {
                    case 1:
                        loadGpu.modelName = line;
                        ++counter;
                        break;
                    case 2:
                        loadGpu.
                        ++counter;
                        break;
                    case 3:
                        ++counter;
                        break;
                    case 4:
                        ++counter;
                        break;
                    case 5:
                        counter = 1;
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Starting from scratch!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
