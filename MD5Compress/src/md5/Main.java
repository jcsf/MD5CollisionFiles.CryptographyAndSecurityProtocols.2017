package md5;

import java.io.*;
import java.nio.file.Files;

public class Main {

    public static void main(String[] args) {
        try {
            String f = readFile("C:\\Users\\JCSF\\Downloads\\longEgg-master\\longEgg-master\\demo");

            byte[] file = f.getBytes();

            System.out.println(MD5.md5Hash_NoPadding(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readFile(String fileName) throws IOException {
        try {
            File file = new File(fileName);
            return new String (Files.readAllBytes(file.toPath()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
