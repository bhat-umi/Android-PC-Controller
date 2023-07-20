import java.awt.AWTException;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.net.Socket;
import java.util.StringTokenizer;

import javax.lang.model.util.ElementScanner14;

import java.awt.*;
 
public class CommandHandler {
    private Robot robot;

    private boolean isCntrl=false;
    private boolean isAlt=false;
    private boolean isShift=false;
    Socket Client;
    
   

    CommandHandler(Socket Client) 
    {
        this.Client=Client;
        try {
            robot=new Robot();
          
        } catch (AWTException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void sendKeys(int x)
    {

        robot.waitForIdle();
        if(isCntrl)
            robot.keyPress(KeyEvent.VK_CONTROL);
        if(isAlt)
            robot.keyPress(KeyEvent.VK_ALT);    
        if(isShift)
            robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(x);

        
        robot.keyRelease(x);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(KeyEvent.VK_SHIFT);
    }
 
    private void pressKey(String key)
    {
        key=key.toLowerCase();
        System.out.println("key--->"+key);
        String keycode=" ";
        switch (key)
        {

            case "ctrl":
                if(isCntrl)
                    isCntrl=false;
                else
                    isCntrl =true;
                return;
            case "shift":
                if(isShift)
                    isShift=false;
                else
                    isShift = true;
                return;
          
            case "alt":
                if(isAlt)
                    isAlt=false;
                else
                    isAlt= true;
                return;

            case "esc":
                sendKeys(KeyEvent.VK_ESCAPE);
                return;
            
                
            case "start":
                sendKeys(KeyEvent.VK_WINDOWS);
                return;
            case "caps":
             sendKeys(KeyEvent.VK_CAPS_LOCK);
                return;
            case "logout":
           // System.out.println("hello logout");
                robot.keyPress(KeyEvent.VK_WINDOWS);
                robot.keyPress(KeyEvent.VK_L);
                robot.keyRelease(KeyEvent.VK_WINDOWS);
                robot.keyRelease(KeyEvent.VK_L);
                
                return;
                
            case "tab":
           
               keycode="\t";
                break;
            case "space":
                keycode=" ";
                break;
            case "enter":
                keycode ="\n";
                break;
            case "bksp":
                keycode ="\b";
                break;
            default:
                keycode =key+"";
            
            
        }

       
          
      
        System.out.println("key "+keycode);
        //System.out.println(KeyEvent.getExtendedKeyCodeForChar(keycode.charAt(0)));
        sendKeys(KeyEvent.getExtendedKeyCodeForChar(keycode.charAt(0)));

    }
    void moveMouse(int x, int y)
    {
        robot.mouseMove(x, y);
    }
    public void Command(String cmd)
    {
       cmd=cmd.trim();
      
        //format for keyboard
        //key character
        
        String Tokens[]=cmd.split(" ");

        if(Tokens[0].equalsIgnoreCase("key"))
        {
            System.out.println("keyboard event");
            pressKey(Tokens[1]);

        }
        else if(Tokens[0].equalsIgnoreCase("mouse")) 
        {
            //Tokens[2]=ST.nextToken();
            System.out.println("mouse event");
            //right left click
            String action=Tokens[1];
            switch(action)
            {
                case "lclick":
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.delay(100);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    return;
                case "rclick":
                      robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
                    robot.delay(100);
                        robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
                return;
            }

           PointerInfo mos= MouseInfo.getPointerInfo();
           Point pt=mos.getLocation();
           int cx=(int)pt.getX();
          int cy =(int)pt.getY();
            int x=0;
            int y=0;
            try{
                x=Integer.parseInt(Tokens[1]);
                y=Integer.parseInt(Tokens[2]);
                moveMouse(cx+x/6,cy+y/6);
            }
            catch(Exception e)
            {
                System.out.println("Token error");
            }
            

        }
        else if(Tokens[0].equalsIgnoreCase("receiveFile"))
        {
            //this is not currently being used in app
              
                System.out.println("Client wants to send file "+ Tokens[2] +"of size "+Tokens[1]);
             //  FileHandler F1= new FileHandler(Tokens[2], Integer.parseInt(Tokens[1]),Client);
               //F1.start();
            
        }
        else
        {
            System.out.println(Constants.W+"Unrecognised command"+Constants.R);
        }


       
        
        
      
        

    }
    public static void main(String[] args) throws AWTException {
        //new CommandHandler();
        new Robot();
    }

}
