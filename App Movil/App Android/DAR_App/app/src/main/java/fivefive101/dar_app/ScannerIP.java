package fivefive101.dar_app;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
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


public class ScannerIP extends ListActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        APICaller a = new APICaller();
        a.execute();
    }
    class APICaller extends AsyncTask
    {

        protected void OnPostExecute(Object result)
        {
            String data = (String) result;
            Gson g = new Gson();
            Type t = new TypeToken<ConsumeIP[]>(){}.getType();
            ConsumeIP[] ips = (ConsumeIP [])g.fromJson(data, t);
            ListView lst = getListView();
            lst.setAdapter(new ArrayAdapter<ConsumeIP>(getApplicationContext(), android.R.layout.simple_list_item_1, ips));

        }

        @Override
        protected Object doInBackground(Object... arg0) {
            HttpClient client = new DefaultHttpClient();
            HttpGet getIP = new HttpGet("AQUI VA LA DIRECCION");
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