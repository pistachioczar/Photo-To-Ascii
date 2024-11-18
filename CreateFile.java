import java.io.File;
import java.io.FileWriter;  // Import the File class
import java.io.IOException;
import java.awt.Desktop;

public class CreateFile {
    public static void makeFile(String fileName) {
        try {
            File file = new File(fileName);
            if(!file.exists()) {
                System.out.println("File does not exist");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writeToFile(String fileName, String content) {
        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(content);
            myWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            File file = new File(fileName);
            if(Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                desktop.open(file);
            }
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void drawAscii(char[][] asciiArray){
        StringBuilder canvas = new StringBuilder();
        for (int y = 0; y < asciiArray.length; y++) {
            for (int x = 0; x < asciiArray[0].length; x++) {
                canvas.append(asciiArray[y][x]);
            }
            canvas.append("\n");
        }

        CreateFile.makeFile("canvas.txt");
        CreateFile.writeToFile("canvas.txt", canvas.toString());
    }
}
