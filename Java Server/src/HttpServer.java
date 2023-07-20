import java.io.IOException;
import java.net.*;

public class HttpServer extends Thread {

    ServerSocket httpServer;
    Socket Client;
    HttpServer()
   {    
        System.out.println(Constants.W+"Starting HTTP server"+Constants.R);
        try {
            httpServer=new ServerSocket(1828);
            System.out.println(Constants.M+"HTTP server started"+Constants.R);
            

        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("\033[90;101m Unable to start  Http server \033[0m");
        }

    }
    public void run()
    {
        while(true)
        {
            try {
                Client=httpServer.accept();
                HttpClientHandler chand=new HttpClientHandler(Client);
                chand.start();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("client connected");
        }
    }
}
