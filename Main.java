import java.io.IOException;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;


public class Main {
  public static void main(String[] args) {
    File image = new File("media\\test.jpg");
    BufferedImage img = null;

    try{ img = ImageIO.read(image); }
    catch (IOException e) { e.printStackTrace(System.out); }

    if (img != null) {
      img = ParseImage.grayScale(img);
      System.out.println(img.getWidth() + " " + img.getHeight());
      ParseImage.display(img);

      char[][] ascii =  ParseImage.makeASCII(img, 3);

      StringBuilder canvas = new StringBuilder();
      for (int y = 0; y < ascii.length; y++) {
        for (int x = 0; x < ascii[0].length; x++) {
          canvas.append(ascii[y][x]);
        }
        canvas.append("\n");
      }

      CreateFile.makeFile("canvas.txt");
      CreateFile.writeToFile("canvas.txt", canvas.toString());
    }
  }

}
