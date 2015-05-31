/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import udpserver.PortScannerThreads;

/**
 *
 * @author Shaiduck
 */
public class Server {
    
    public static ArrayList<String> direcciones = new ArrayList<String>();
    public static ArrayList<Integer> puertos = new ArrayList<Integer>();
    
    public static void main(String args[])
    {
        DatagramSocket serverSocket = null;
        try {
            serverSocket = new DatagramSocket(9876);
        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        
        while(true)
        {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            try {
                System.out.println("Estamos esperando paquete");
                serverSocket.receive(receivePacket);
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
            String sentence = new String(receivePacket.getData());
            String respuesta = "";
            if (sentence.contains("Prueba"))
            {
                System.out.println("RECEIVED: "+ sentence);
                respuesta = sentence.toUpperCase();
                
                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                sendData = respuesta.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                try {
                    serverSocket.send(sendPacket);
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if (sentence.contains("IP"))
            {
                try {
                    ScannerIP.escanear();
                    for (int i=0; i<direcciones.size(); i++)
                    {
                        respuesta = direcciones.get(i);
                        InetAddress IPAddress = receivePacket.getAddress();
                        int port = receivePacket.getPort();
                        sendData = respuesta.getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                        try {
                            serverSocket.send(sendPacket);
                        } catch (IOException ex) {
                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if (sentence.contains("ports"))
            {
                respuesta = "ack";
                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                sendData = respuesta.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                try {
                    serverSocket.send(sendPacket);
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                try {
                    serverSocket.receive(receivePacket);
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
                sentence = new String(receivePacket.getData());
                PortScannerThreads.iniciaScannerPuertos(sentence);
                
                for (int i=0; i<puertos.size(); i++)
                {
                    respuesta = puertos.get(i).toString();
                    IPAddress = receivePacket.getAddress();
                    port = receivePacket.getPort();
                    sendData = respuesta.getBytes();
                    sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                    try {
                        serverSocket.send(sendPacket);
                    } catch (IOException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            respuesta = "close";
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            sendData = respuesta.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            try {
                serverSocket.send(sendPacket);
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}
