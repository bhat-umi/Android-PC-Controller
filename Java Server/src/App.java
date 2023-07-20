public class App {
    public static void main(String[] args) throws Exception {
        
       Server Main= new Server();
       Main.start();
       HttpServer httpserver=new HttpServer();
       httpserver.start();

    
    }
}