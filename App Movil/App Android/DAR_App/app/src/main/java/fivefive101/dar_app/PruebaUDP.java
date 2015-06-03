package fivefive101.dar_app;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import fivefive101.dar_app.metodos.UDPClient;


public class PruebaUDP extends ActionBarActivity {

    TextView textoEditable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba_udp);

    }


    public void clienteUDP(View view)
    {
        textoEditable = (TextView)findViewById(R.id.textoEditable);
        new IniciaCliente().execute();
    }

    private class IniciaCliente extends AsyncTask<Void, Void, Void>
    {
        String modifiedSentence;
        @Override
        protected Void doInBackground(Void... params) {
            UDPClient x = new UDPClient();
            modifiedSentence = x.iniciaCliente(textoEditable);
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
                textoEditable.append("\n " + modifiedSentence);
                super.onPostExecute(result);
        }
    }
}
