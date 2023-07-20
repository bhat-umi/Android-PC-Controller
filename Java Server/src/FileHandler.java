// import java.io.DataInputStream;
// import java.io.DataOutputStream;
// import java.io.File;
// import java.io.FileOutputStream;
// import java.io.IOException;
// import java.io.OutputStream;
// import java.net.Socket;

// public class FileHandler extends Thread{
// //to save  a file 
// String Fname;
// int fsize;
// Socket Client;
//     FileHandler(String Fname,int size,Socket Client )
//     {
//         fsize=size;
//         this.Fname=Fname;
//         this.Client=Client;

//     }
//     public void run()
//     {
//         try {
//             DataOutputStream out = new DataOutputStream(Client.getOutputStream()) ;
//             DataInputStream in = new DataInputStream(Client.getInputStream()) ;
//             out.writeUTF("ok send");
//            sleep(1000);
//            int i=0;
//            byte bytes_[]=new byte[fsize];
//            while(i<fsize)
//            {
//             bytes_[i++]=in.readByte();
//            }
//            System.out.println("file received sucessfuly");
//            File newfile=new File(Fname);
//            FileOutputStream os=new FileOutputStream(newfile) ;
            
//            os.write(bytes_);
//            os.close();
//            System.out.println("File saved");




//         } catch (Exception e) {
//             // TODO Auto-generated catch block
//             e.printStackTrace();
//         }


//     }


    
// }
