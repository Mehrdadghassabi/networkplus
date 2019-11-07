package client;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EmWar
 */
public class Client {
    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        print("input your ip: ");
        String ip=sc.nextLine();
        
     
        try {
            greetingclient gclient=new greetingclient(ip,5000);
          gclient.run();
            
          
        }
        catch (IOException ex) 
        {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       
      
    }
    
    public static void print(Object obj){
    System.out.print(obj);
    }
}
