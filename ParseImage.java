import java.awt.*;
import java.awt.image.*;
import javax.swing.*;


public class ParseImage {

    public static void display (BufferedImage img){
        JFrame frame = new JFrame();
        JLabel label = new JLabel();
        frame.setSize(img.getWidth(), img.getHeight());
        label.setIcon(new ImageIcon(img));
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static BufferedImage grayScale(BufferedImage img) {
        BufferedImage grayImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics g = grayImage.getGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
        return grayImage;
    }

    public static char[][] makeASCII(BufferedImage img, int pixelWidth) {
        char[][] ascii = new char[img.getHeight()/ pixelWidth][img.getWidth()/ pixelWidth];


        for (int y = 0; y < img.getHeight(); y += pixelWidth) {
            for (int x = 0; x < img.getWidth(); x += pixelWidth) {
                int totalPixelValue = 0;
                int pixelCount = 0;

                for (int i = 0; i < pixelWidth && y + i < img.getHeight(); i++) {
                    for (int j = 0; j < pixelWidth && x + j < img.getWidth(); j++) {
                        totalPixelValue += (img.getRGB(x + j, y + i) & 0xff);
                        pixelCount++;
                    }
                }

                int avgPixelValue = totalPixelValue / pixelCount;

                if (avgPixelValue == 255) {
                    ascii[y / pixelWidth][x / pixelWidth] = ' ';
                } else if (avgPixelValue > 240) {
                    ascii[y / pixelWidth][x / pixelWidth] = '.';
                } else if(avgPixelValue > 220) {
                    ascii[y / pixelWidth][x / pixelWidth] = ':';
                }else if(avgPixelValue > 200) {
                    ascii[y / pixelWidth][x / pixelWidth] = '-';
                }else if(avgPixelValue > 180) {
                    ascii[y / pixelWidth][x / pixelWidth] = '=';
                }else if(avgPixelValue > 160) {
                    ascii[y / pixelWidth][x / pixelWidth] = '+';
                }else if(avgPixelValue > 140) {
                    ascii[y / pixelWidth][x / pixelWidth] = '*';
                }else if(avgPixelValue > 120) {
                    ascii[y / pixelWidth][x / pixelWidth] = '#';
                }else if(avgPixelValue > 100) {
                    ascii[y / pixelWidth][x / pixelWidth] = '%';
                }else{
                    ascii[y / pixelWidth][x / pixelWidth] = '@';
                }
            }
        }

        return ascii;
    }

}
