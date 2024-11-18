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

    System.out.println("""
            Which image would you like to make into ascii?
            ----------------------------------------------
            """);
    while(true) {
      for (String i: directoryContents) {
        System.out.println(i);
      }

      try {
        fileChoice = scanner.nextLine();
        image = new File("media\\" + fileChoice);
        String extension = fileChoice.substring(fileChoice.lastIndexOf(".") + 1);

        if(!image.exists() && !image.isFile()) {
          System.out.println("""
                  File not found. Please enter the file name with the extension for example:
                  picture.png
                  --------------------------------------------------------------------------
                  """);
          continue;
        }
        if(extension.equals("png") || extension.equals("jpg") || extension.equals("jpeg")) {
          break;
        } else {
          System.out.println("""
                  Please enter an image file with one of these extensions: 'png','jpg' or,'jpeg'
                  ------------------------------------------------------------------------------
                  """);
        }
      } catch(Exception e) {
        System.out.println("""
                That is not a valid file. Please choose one listed below
                --------------------------------------------------------
                """);
      }
    }


    BufferedImage img = null;
    try{ img = ImageIO.read(image); }
    catch (IOException e) { e.printStackTrace(System.out); }

    System.out.println("""
            Specify the pixel dimension of each character square (e.g., 2 for a 2x2 square).
            --------------------------------------------------------------------------------
            """);
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
        if(characterPerPixel < 0) {
          throw new IllegalArgumentException("Please enter a positive integer");
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
