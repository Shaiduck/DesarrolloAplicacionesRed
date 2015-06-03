package fivefive101.dar_app.metodos;

import android.widget.TextView;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Created by Shaiduck on 5/15/15.
 */
public class IPScanner {
    String modifiedSentence;
    ArrayList<String> mensajes = new ArrayList<String>();

    public ArrayList<String> iniciaScanner(TextView textoEditable)
    {
        DatagramSocket clientSocket = null;
        modifiedSentence = " ";
        try {
            clientSocket = new DatagramSocket();
        } catch (SocketException ex) {
            mensajes.add("ERROR");
            return mensajes;
        }
        InetAddress IPAddress = null;
        try {
            IPAddress = InetAddress.getByName("255.255.255.255");
        } catch (UnknownHostException ex) {
            mensajes.add("ERROR");
            return mensajes;
        }
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        String sentence = textoEditable.getText().toString();
        sendData = sentence.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
        try {
           // clientSocket.setSoTimeout(1000000000);
            clientSocket.send(sendPacket);
        } catch (IOException ex) {
            mensajes.add("ERROR");
            return mensajes;
        }
        while (!modifiedSentence.contains("close")) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            try {
                clientSocket.receive(receivePacket);
            } catch (IOException ex) {
                mensajes.add("ERROR");
                return mensajes;
            }
            modifiedSentence = new String(receivePacket.getData());
            if (!modifiedSentence.contains("close")) {
                mensajes.add(modifiedSentence);
            }
        }
        clientSocket.close();
        return mensajes;
    }
}
