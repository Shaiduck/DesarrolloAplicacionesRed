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
import java.net.NetworkInterface;

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
               System.out.println("La maquina "+address.getHostName()+" esta encendida y se puede hacer ping en la direccion" + address.getHostAddress());
           }
           else if(!address.getHostAddress().equals(address.getHostName()))
           {
               System.out.println("La maquina "+address.getHostName()+" es reconocida en una busqueda de DNS");
               System.out.println("La direccion IP de "+address.getHostName()+" es "+address.getHostAddress());
               conseguirMAC(InetAddress.getByName(address.getHostAddress()));
           }
           else
           {
               System.out.println("La direccion del host " + address + " y el nombre del host son iguales, significa que el nombre del host no se pudo resolver");
           }
       }
    }
    
    public static void conseguirMAC(InetAddress address)
    {
        try
        {
            NetworkInterface getMac = NetworkInterface.getByInetAddress(address);
            byte[] mac = getMac.getHardwareAddress();
            for (int i = 0; i < mac.length ; i++)
            {
                String macAdd = String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" :"");
            }
        }
        catch(IOException ex)
        {
            
        }
    }
}
