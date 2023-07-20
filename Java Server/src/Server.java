import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
    private final int PORT =1827;
    ServerSocket Server=null;
    Socket client=null;
    InetAddress ip=null;
    DataInputStream inputStream;
    

     Server()
     {
        System.out.println("Starting Sever");
       
        try {
            Server= new ServerSocket(PORT);
            
            System.out.println(Constants.M+"Main Server Strated "+Constants.R);
            System.out.println("Ip: "+Constants.E+ GetIp.Net()+Constants.R);
            System.out.println("Port: "+Constants.E+PORT+Constants.R);
          
           
       

        } catch (IOException e) {
            System.out.println("\033[90;101m Unable to start  server \n Other instance might be still Running \033[0m");
           // e.printStackTrace();
        }


     }
    public void run()
     {
        while(true && Server!=null)
        {
            System.out.println("Waiting for new client");
            try {
                client=Server.accept();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("Client connected");
           ClientHandler clientHandler= new ClientHandler(client);
           clientHandler.start();
          

        }
     }
    
}
