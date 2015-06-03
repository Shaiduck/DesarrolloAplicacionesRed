package fivefive101.dar_app.soap;

import fivefive101.dar_app.ScannerIP;

/**
 * Created by Shaiduck on 6/1/2015.
 */
public class Caller extends Thread {

    public CallSoap cs;

    public void run()
    {
        try
        {
            cs = new CallSoap();
            String resp = cs.Call();
            ScannerIP.respuesta = resp;
        }
        catch(Exception ex)
        {
            ScannerIP.respuesta = ex.toString();
        }
    }
}
