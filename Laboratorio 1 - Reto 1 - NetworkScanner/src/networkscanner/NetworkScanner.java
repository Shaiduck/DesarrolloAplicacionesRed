/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkscanner;

import java.net.InetAddress;

/**
 *
 * @author Shaiduck
 */
public class NetworkScanner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        new NetworkScanner().say();
    }
    
    public void say()
    {
        try{
            java.net.InetAddress i = java.net.InetAddress.getLocalHost();
            System.out.println(i);
            System.out.println(i.getHostName());
            System.out.println(i.getHostAddress());
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
}
