package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EmWar
 */
public class greetingclient implements Runnable {
   private final Socket client;
   private final OutputStream outtoserver;
   private final DataOutputStream out;
   private final InputStream infromserver;
   private final DataInputStream in;
   private final Scanner sc;
   private boolean isfirst;
   private String message;
   
   greetingclient(String id,int port) throws IOException{
       client = new Socket(id,port);
       outtoserver=client.getOutputStream();
       out=new DataOutputStream(outtoserver);
       infromserver=client.getInputStream();
       in=new DataInputStream(infromserver);
       sc=new Scanner(System.in);
       isfirst=true;
   }
   
   private void print(Object obj){
       System.out.println(obj);
   }

   private void getmessage() throws IOException{
        print("wait for getting message: ");
       print(in.readUTF());
       
   }
   
   private void sendmessage() throws IOException{
       print("type sth: ");
       message=sc.nextLine();
       out.writeUTF(message);
       
   }
   
   private void firststatement(){
   print("Just connected to " + client.getRemoteSocketAddress());
   }
   
    @Override
    public void run() {
        while(true){
       try {
           if(isfirst)
            firststatement();
            

           getmessage();
              sendmessage();
           
          
           isfirst=false;
       }
       catch (IOException ex) {
           Logger.getLogger(greetingclient.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    }
}