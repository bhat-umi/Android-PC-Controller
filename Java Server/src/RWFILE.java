import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class RWFILE {
    String boundary;
    int size;
    String rawcontent;
    HashMap<String,String> fileinfo;
    RWFILE(String boundary,int size,String rawcontent)
    {
        this.boundary=boundary;
        this.size=size;
        this.rawcontent=rawcontent;
        fileinfo=new HashMap<String,String>();
    }
    public  boolean matchHeaer()
    {
        if(rawcontent.indexOf(boundary)>0)
            return true;
        
        return false;
    }
    String readLine(String temp)
    {
        //System.out.println("INput");
        //System.out.println(temp);
        return temp.substring(0, temp.indexOf("\r\n"));

    }
    String getFilename()
    {
        String token[];
        String temp="filename=\"";
        if(fileinfo.containsKey("Content-Disposition")){
            token=fileinfo.get("Content-Disposition").split("; ");
            for(String x : token)
                System.out.println(x);
            token[2]=token[2].trim();
            return token[2].substring(temp.length(), token[2].length()-1);

        };
        return "temp";
    }
    public void readFileheader()
    {
        String BOUNDARY=readLine(rawcontent);//ignore
        int l=BOUNDARY.length();
       // System.out.println(BOUNDARY);
        String temp=rawcontent.substring( l+2);
        int k=0;
        String h="";
        do{
            h=readLine(temp);
            k=h.length();
            temp=temp.substring(k+2);
            if(k>0){
                String tkn[]=h.split(": ");
                fileinfo.put(tkn[0], tkn[1]);
            }
           // System.out.println("output");
            //System.out.println(h+" end-->");
        }
        while(k>0);
       // getFilename();
       temp= temp.substring(0, temp.lastIndexOf("\r\n"));
       temp= temp.substring(0, temp.lastIndexOf("\r\n"));
       // System.out.println(temp);
        byte filedata[]=temp.getBytes();
        try  {
        

        File newfile=new File(getFilename());
        try{
            FileOutputStream os = new FileOutputStream(newfile);
            os.write(filedata);
            os.close();
            System.out.println(Constants.M+" "+newfile +" Received "+Constants.R);
          }
        catch ( FileNotFoundException E)
        {
            System.out.println(Constants.W+"Unabel to save "+newfile+" it may already exist"+Constants.R);
        }
            
        } 
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
