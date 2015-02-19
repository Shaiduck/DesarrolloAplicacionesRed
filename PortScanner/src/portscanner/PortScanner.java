/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portscanner;

/**
 *
 * @author CristianShaid
 */

import java.net.*;

public class PortScanner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //int startPortRange = 0;
        //int stopPortRange=0;
        int startPortRange = 1;
        int stopPortRange=2000;
        
        //startPortRange = Integer.parseInt(args[0]);
        //stopPortRange = Integer.parseInt(args[1]);
        
        
        for (int i = startPortRange; i <=stopPortRange; i++)
        {
            try
            {
                Socket serverSok = new Socket("172.72.56.154", i);
                System.out.println("El puerto "+i+" esta en uso");
                serverSok.close();
                
            }
            catch(Exception e)
            {
             //System.out.println("El puerto "+ i+ " no esta en uso");   
            }
        }
        
    }
    
}
