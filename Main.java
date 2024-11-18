import java.io.IOException;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {

    ///Initiate Vars
    String fileChoice;
    Scanner scanner = new Scanner(System.in);
    File directory = new File(".");
    String[] directoryContents;
    int characterPerPixel;

    ///Makes sure media file is present
    try {
      directory = new File("media");
    }catch(Exception e){
      System.out.println("directory 'media' not found");
    }
    directoryContents = directory.list();
    File image;

    if (directoryContents == null) {
      System.out.println("You have no files to convert. Please insert an image in the 'media' folder");
      System.exit(0);
    }

    System.out.println("Which image would you like to make into ascii?");
    while(true) {
      for (String i: directoryContents) {
        System.out.println(i);
      }

      try {
        fileChoice = scanner.nextLine();
        image = new File("media\\" + fileChoice);
        if(image.exists() && image.isFile()) {
          break;
        } else{
          System.out.println("File not found. Please enter the file name with the extension for example:\npicture.png");
        }
      } catch(Exception e) {
        System.out.println("That is not a valid file. Please choose one listed below");
      }
    }


    BufferedImage img = null;
    try{ img = ImageIO.read(image); }
    catch (IOException e) { e.printStackTrace(System.out); }

    System.out.println("How many pixels do you want each character to take up?");
    while(true) {
      try{
        characterPerPixel = Integer.parseInt(scanner.nextLine());
          assert img != null;
          if(img.getWidth() <= characterPerPixel || img.getHeight() <= characterPerPixel) {
          if(img.getWidth() >= img.getHeight()){
            throw new IllegalArgumentException("Please enter a number smaller than " + img.getHeight());
          }else{
            throw new IllegalArgumentException("Please enter a number smaller than " + img.getWidth());
          }
        }
        break;
      }catch(Exception e){
        System.out.println(e.getMessage());
      }
    }


    img = ParseImage.grayScale(img);
    char[][] ascii =  ParseImage.makeASCII(img, characterPerPixel);

    CreateFile.drawAscii(ascii);
  }

}
