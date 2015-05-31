package fivefive101.dar_app.metodos;

import android.widget.TextView;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by Shaiduck on 5/15/15.
 */
public class UDPClient {

    private String modifiedSentence;

    public String iniciaCliente(TextView textoEditable)
     {
         DatagramSocket clientSocket = null;
         modifiedSentence= " ";
         try {
             clientSocket = new DatagramSocket();
         } catch (SocketException ex) {
             modifiedSentence ="ERROR";
             return modifiedSentence;
         }
         InetAddress IPAddress = null;
         try {
             IPAddress = InetAddress.getByName("255.255.255.255");
         } catch (UnknownHostException ex) {
             modifiedSentence ="ERROR";
             return modifiedSentence;
         }
         byte[] sendData = new byte[1024];
         byte[] receiveData = new byte[1024];
         String sentence = textoEditable.getText().toString();
         sendData = sentence.getBytes();
         DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
         try {
             clientSocket.setSoTimeout(10000);
             clientSocket.send(sendPacket);
         } catch (IOException ex) {
             modifiedSentence ="ERROR";
             return modifiedSentence;
         }
         DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
         try {
             clientSocket.receive(receivePacket);
         } catch (IOException ex) {
             modifiedSentence ="ERROR";
             return modifiedSentence;
         }
         modifiedSentence = new String(receivePacket.getData());
         clientSocket.close();
         return modifiedSentence;
     }
}
