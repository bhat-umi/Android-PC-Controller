import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class GetIp {
   static  boolean isConnected=false;
    public static String Net()
    {
        String ips="(";
        try {
            Enumeration<NetworkInterface> e;
            e = NetworkInterface.getNetworkInterfaces();
        
            while(e.hasMoreElements())
            {
                NetworkInterface n = (NetworkInterface) e.nextElement();
                Enumeration<InetAddress> ee = n.getInetAddresses();
                while (ee.hasMoreElements())
                {
                    InetAddress i = ee.nextElement();
                    String ip=i.getHostAddress();
                   // System.out.println(ip);
                    if(ip.startsWith("192."))
                    {
                        isConnected=true;
                      ips+=ip+", ";
                    }
                    //System.out.println(i.getHostAddress());
                }
            }
            if (isConnected)
                return ips+"\b\b)";
            else    
                return "Network Error";
            }
             catch (SocketException x) {
    // TODO Auto-generated catch block
    x.printStackTrace();
    return "Network Error(2)";
    }
   
    }
}
