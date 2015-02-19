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
import java.awt.List;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class PortScannerThreads {
    public static int port;
    @SuppressWarnings("resource")
    
    public static void main(String[] args)
    {
        System.out.println("Escribe la direccion IP a scannear: ");
        Scanner userEntry = new Scanner(System.in);
        String host = userEntry.nextLine();
        
        try
        {
            startThreads(host);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();;
        }
    }
    
    private static void startThreads(String host) throws InterruptedException
    {
        int numThreads=10;
        int count=1;
        Thread[] threads = new Thread[numThreads];
        System.out.println("Creando hilos");
        for (int i = 0; i < threads.length; i++)
        {
            if (i==0)
            {
                threads[i] = new Thread(new Runner(host, 70));
                threads[i].start();
            }
            else
            {
                threads[i] = new Thread(new Runner(host, count*2000));
                threads[i].start();
            }
            count++;
        }
        for(int i = 0; i < threads.length; i++)
        {
            threads[i].join();
        }
        System.out.println("HECHO");
    }
}

class Runner implements Runnable
{
    static int port;
    private final String host;
    private final int count;
    PortScannerThreads principal = new PortScannerThreads();
    
    public Runner(String host, int count)
    {
        this.host = host;
        this.count = count;
    }
    
    public void run()
    {
        int puertos = 0;
        for(int port = count; puertos <2000; port++)
        {
            Socket socket;
            try
            {
                socket = new Socket(host, port);
                System.out.println("El puerto "+port+" esta abierto.");
                socket.close();
            }
            catch(IOException ioEx)
            {
                //System.out.println("El puerto "+port+" no esta abierto.");
            }
            puertos++;
        }
        Thread.yield();
    }
}
