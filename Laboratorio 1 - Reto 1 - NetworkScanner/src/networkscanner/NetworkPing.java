/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkscanner;

/**
 *
 * @author Shaiduck
 */

import java.io.IOException;
import java.net.InetAddress;

public class NetworkPing {
    
    public static void main(String[] args) throws IOException
    {
       InetAddress hostlocal = InetAddress.getLocalHost();
       byte[] ip = hostlocal.getAddress();
        
       for (int i = 1; i <= 254; i++)
       {
           ip[3] = (byte)i;
           InetAddress address = InetAddress.getByAddress(ip);
           if(address.isReachable(1000))
           {
               //System.out.println("La maquina en "+address.getHostName()+" esta encendida y se puede hacer ping en " + address.getHostAddress());
               System.out.println(address.getHostAddress());
               System.out.println(address.getHostName());
               System.out.println(address.getCanonicalHostName());
           }
           else if(address.getHostAddress().equals(address.getHostName()))
           {
               System.out.println("La maquina en "+address+" es reconocida en una busqueda de DNS");
           }
           else
           {
               System.out.println("La direccion del host" + address + "   y el nombre del host son iguales, significa que el nombre del host no se pudo resolver");
           }
       }
    }
}
