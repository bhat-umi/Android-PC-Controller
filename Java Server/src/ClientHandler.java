import java.io.*;
import java.net.Socket;

import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;


public class ClientHandler extends Thread {
    Socket client=null;
    BufferedReader in=null;
    boolean isConnected=true;
    CommandHandler commandHandler;
    ClientHandler(Socket client)
    {
        this.client=client;
        System.out.println(client.getInetAddress() +"assigned to "+" ClientHandler " +this.getId());
       
       
    }

   public void run()
    {
        try {
            commandHandler=new CommandHandler(client);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
    
            while(!client.isClosed())
            {
                while(!in.ready())
                {
                   // System.out.println(Constants.M+" Going to sleep "+Constants.R);
                    sleep(2000);
                }
                String Command=in.readLine();
                //check if is client wants to send file
                
                commandHandler.Command(Command);
                
                System.out.print(Command);
            }
            System.out.println(Constants.W+"Disconnected.."+Constants.R);
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
}
