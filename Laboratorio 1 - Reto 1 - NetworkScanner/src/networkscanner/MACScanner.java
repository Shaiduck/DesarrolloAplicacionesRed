/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkscanner;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;

/**
 *
 * @author Shaiduck
 */
public class MACScanner {
    public static void main(String[] args) throws SocketException
    {
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets))
        {
            displayInterfaceInformation(netint);
        }
    }
    
    private static void displayInterfaceInformation(NetworkInterface netint) throws SocketException
    {
        System.out.printf("Display name: %s%n", netint.getDisplayName());
        System.out.printf("Name: %s%n", netint.getName());
        Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
        for (InetAddress inetAddress : Collections.list(inetAddresses))
        {
            System.out.printf("InetAddress: %s%n", inetAddress);
        }
        
        System.out.printf("Hardware Address : %s%n", Arrays.toString(netint.getHardwareAddress()));
        
    }
}
