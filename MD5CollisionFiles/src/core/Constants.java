package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Constants {
    public static String mainPath = "";
    public static String templatesPath = "";
    public static String outputPath = "";

    public static void loadProperties() {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("config.properties");

            // load a properties file
            prop.load(input);

            if(prop.containsKey("PATH")) {
                mainPath = prop.getProperty("PATH");
                templatesPath = mainPath + "/templates/";
                outputPath = mainPath + "/outputs/";
            } else {
                System.err.println("[CRITICAL ERROR]: VERIFY PROPERTIES FILE (\"config.properties\")");
                System.err.println("---|DEFAULT FILE|---");
                System.err.println("# PROPERTIES\n" +
                        "PATH=C:/Users/JCSF/Desktop/Project CPS/MD5CollisionFiles/");
                System.err.println("--------------------");
                System.exit(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
