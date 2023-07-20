import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.text.Position;

public class HttpClientHandler extends Thread {
 Socket client;
 String Content;
 long size;
HashMap<String,String> header;
 HttpClientHandler(Socket client)
 {
    this.client=client;
    header=new HashMap<String,String>();
}

    public void  loadFile(String p) throws IOException
    {
        Path path = Paths.get(p);
        size=Files.size(path);
        byte[] byte_=Files.readAllBytes(path);
       Content =new String(byte_);
      

    } 
    public String FetchLine(InputStreamReader in)
    {
        int byte_;
        String line="";
        try {
            while((byte_=in.read())>0)
            {
                if((char)byte_=='\r')
                {
                    if((char)(byte_=in.read())=='\n')
                    {
                        return line;

                        
                    }

                }
                line+=(char)byte_+"";
                    
                //System.out.print((char)byte_);
            }
            return "";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "Error";
        }
    }
    // public String FetchHeader(InputStreamReader in) throws IOException
    // {
    //     int a;
    //     int b; 
    //     int c;
    //     int d;
    //     String Headers="";
    //     while(true)
    //     {
    //         a=b=c=d=' ';
    //         a=in.read();
    //         if((char)a=='\r')
    //         {
    //             a=' ';
    //             b=in.read();
    //             if((char)b=='\n')
    //             {
    //                 b=';';
    //                 c=in.read();
    //             if((char)c=='\r')
    //             {
    //                 c=' ';
    //                 d=in.read();
    //                 if((char)d=='\n')
    //             {
    //                 d=';';
    //                 return Headers;
                    

    //             }
                    

    //             }
    //             }
                    
                    
    //         }
    //         else
    //             Headers+=(char)a +(char)b+ (char)c+ (char)d;

            

    //     }

    // }
    public String FetchHeaders(InputStreamReader in)
    {
        String headers="";
        while(true)
        {
            String line=FetchLine(in);
            if(line.length()==0)
                return headers;
            else    
                headers+=line;
            String token[]=line.split(": ");
           header.put(token[0], token[1]);
            
        }

    }
    public String ReqType(String req)
    {
        String temp[]=req.split(" ");
        temp[0]=temp[0].trim();
        return temp[0];
    }

    public void run()
    {
        try  {

            
            InputStreamReader in=new InputStreamReader(client.getInputStream(),StandardCharsets.US_ASCII);
            String req=FetchLine(in);
           // System.out.println(req);
            String RType=ReqType(req);
           // System.out.println(RType);
            String hedaers=FetchHeaders(in);
          // System.out.println(hedaers);
            int k=0;
            int fsize=0;
            String boundary="";
            if(header.containsKey("Content-Length"))
            {
                fsize=Integer.parseInt(header.get("Content-Length"));
                //System.out.println("length= "+fsize);
            }
            if(header.containsKey("Content-Type"))
            {
                String temp=header.get("Content-Type");
                String tempToken[]=temp.split("; ");
                for(String x: tempToken)
                {
                    if(x.startsWith("boundary="))
                    {
                        boundary=x.substring("boundary=".length());
                        boundary=boundary.replaceAll("-", "");
                        break;
                    }
                }
            }
           // System.out.println("Boundary =" +boundary);
            int i=0;
            StringBuilder sb=new StringBuilder();
            
            while(i<fsize-k && RType.equalsIgnoreCase("post"))
            {
                int byte_=in.read();
               // System.out.print((char)byte_);
                sb.append((char)byte_);
                i++;
            }
            //System.out.println(sb);
            RWFILE newFile=new RWFILE(boundary, fsize, sb.toString());
           // System.out.println("is boundary match"+newFile.matchHeaer());
            newFile.readFileheader();
            
        } catch (Exception e) {
            // TODO: handle exception
        }

        try (DataOutputStream out = new DataOutputStream(client.getOutputStream())) {
            loadFile("./src/index.html");
            //building response
            String html="HTTP/1.1 200 OK\r\ncontent-length: ";
            html+=(size+"");
            html+="\r\n\r\n";
            html+=Content;

           
            out.writeBytes(html);


        } catch (Exception e) {

            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
}
