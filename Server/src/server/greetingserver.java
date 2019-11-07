package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EmWar
 */

public class greetingserver implements Runnable {
   private final  ServerSocket serverSocket;
   private final Socket server;
   private final OutputStream outtoserver;
   private final DataOutputStream out;
   private final InputStream infromserver;
   private final DataInputStream in;
   private final Scanner sc;
   private boolean isfirst;
   private String message;
     
     greetingserver(int port) throws SocketException, IOException{
         serverSocket = new ServerSocket(port);
      serverSocket.setSoTimeout(100000);
         server = serverSocket.accept();
      server.setSoTimeout(10000);
      outtoserver=server.getOutputStream();
       out=new DataOutputStream(outtoserver);
       infromserver=server.getInputStream();
       in=new DataInputStream(infromserver);
       sc=new Scanner(System.in);
       isfirst=true;

     }

  
     
     private void print(Object obj){
       System.out.println(obj);
   }
     
   
     
     public void firststatement(){
     print("Waiting for client on port " +
                       serverSocket.getLocalPort() + "..."+ "\n" +
               "Just connected to " + server.getRemoteSocketAddress());
   }

     
     public  void getmessage() throws IOException{
         print("wait for getting message: ");
       print(in.readUTF());
       
   }
   
   private void sendmessage() throws IOException{
       print("type sth: ");
       message=sc.nextLine();
       out.writeUTF(message);
       
   }
     
   
   @Override
    public void run() {
       while(true){
           try {
           if(isfirst){
             firststatement(); }
               

           sendmessage();
             getmessage();
            
               
            isfirst=false;
           } 
            catch (SocketTimeoutException s) {
              s.printStackTrace();
         }
           catch (IOException ex) {
               Logger.getLogger(greetingserver.class.getName()).log(Level.SEVERE, null, ex);
               break;
           }
       
       }
    }
      
}