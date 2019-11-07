package server;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EmWar
 */
public class Server {

   public static void main(String[] args) throws IOException,InterruptedException {
       try {
            greetingserver gserver=new greetingserver(5000);
             gserver.run();
            

        } 
        catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
   
 
   
}