package prelims.Week4;

import java.io.*;

public class FileWritePractice {
    public static void main(String[] args) throws IOException {
        File file = new File("prelims/Week4/test.txt");
        FileWriter writer = new FileWriter(file);
        writer.write("Hello, World!");
        writer.close();
    }
}
