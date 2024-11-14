import java.io.File;
import java.io.FileWriter;  // Import the File class
import java.io.IOException;
import java.awt.Desktop;
import java.io.File;

public class CreateFile {
    public static void makeFile(String fileName) {
        try {
            File file = new File(fileName);
            if(!file.exists()) {
                System.out.println("File does not exist");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void writeToFile(String fileName, String content) {
        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(content);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println(e);
        }

        try {
            File file = new File(fileName);
            if(Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                desktop.open(file);
            }
        } catch(IOException e) {
            System.out.println(e);
        }

    }
}
