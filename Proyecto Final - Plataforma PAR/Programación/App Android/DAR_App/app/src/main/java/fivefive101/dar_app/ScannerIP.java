package fivefive101.dar_app;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import fivefive101.dar_app.Consumibles.ConsumeIP;
import fivefive101.dar_app.metodos.IPScanner;
import fivefive101.dar_app.soap.Caller;


public class ScannerIP extends ActionBarActivity {

    public static String respuesta = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba_udp);
        Button b1 = (Button)findViewById(R.id.botonEnviar);
        final AlertDialog ad = new AlertDialog.Builder(this).create();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try
                {
                    respuesta = "START";
                    Caller c = new Caller();
                    c.join();
                    c.start();
                    while(respuesta == "START")
                    {
                        try{
                            Thread.sleep(10);
                        }
                        catch(Exception ex)
                        {

                        }
                    }
                    ad.setTitle("Resultados");
                    ad.setMessage(respuesta);
                }
                catch(Exception ex)
                {
                    ad.setTitle("ERROR FATAL");
                    ad.setMessage(ex.toString());
                }
                ad.show();
            }
        });

    }
    class APICaller extends AsyncTask
    {

        protected void OnPostExecute(Object result)
        {
            String data = (String) result;
            Gson g = new Gson();
            Type t = new TypeToken<ConsumeIP[]>(){}.getType();
            ConsumeIP[] ips = (ConsumeIP [])g.fromJson(data, t);
            //ListView lst = getListView();
            //lst.setAdapter(new ArrayAdapter<ConsumeIP>(getApplicationContext(), android.R.layout.simple_list_item_1, ips));

        }

        @Override
        protected Object doInBackground(Object... arg0) {
            HttpClient client = new DefaultHttpClient();
            HttpGet getIP = new HttpGet("http://192.168.1.67:81/Receptor.asmx?op=scanIPS");
            ResponseHandler<String> handler = new BasicResponseHandler();
            Object result = new Object();
            try {
                result = client.execute(getIP, handler);
            }
            catch (ClientProtocolException e)
            {
                e.printStackTrace();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            return result;
        }
    }
}