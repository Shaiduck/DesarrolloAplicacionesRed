/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpserver;

import java.io.IOException;
import java.net.InetAddress;

/**
 *
 * @author Shaiduck
 */
public class ScannerIP {
    public static void escanear() throws IOException
    {
        InetAddress hostlocal = InetAddress.getLocalHost();
       byte[] ip = hostlocal.getAddress();
        
       for (int i = 1; i <= 10; i++)
       {
           ip[3] = (byte)i;
           InetAddress address = InetAddress.getByAddress(ip);
           if(address.isReachable(1000))
           {
               Server.direcciones.add(address.getHostAddress());
               System.out.println(address.getHostAddress());
               //System.out.println(address.getHostName());
               //System.out.println(address.getCanonicalHostName());
           }
    }
    }
}
