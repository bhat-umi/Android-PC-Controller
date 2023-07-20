// Java program to demonstrate working of Robot
// class. This program is for Windows. It opens
// notepad and types a message.
import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
 
public class robo
{
    private static PointerInfo pointerInfo;
    private static Point location;

    public static void main(String[] args) throws IOException,
                           AWTException, InterruptedException
    {
        try {
            Robot rr=new Robot();
       // rr.mouseMove(400, 500);
        
          pointerInfo = MouseInfo.getPointerInfo();
          location = pointerInfo.getLocation();
          System.out.println(location.getX());

       BufferedImage bb=rr.createScreenCapture(new Rectangle(0, 0, 500, 500));
       File imageFile = new File("single-screen.bmp");
    ImageIO.write(bb, "bmp", imageFile );
    //assertTrue(imageFile .exists());
            
        } catch (Exception e) {
         System.out.println(e);
        }
        
    }
}