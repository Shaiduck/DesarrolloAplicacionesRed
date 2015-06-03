package fivefive101.dar_app;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import fivefive101.dar_app.metodos.PortScanner;


public class ScannerPuertos extends ActionBarActivity {
    TextView textoEditable;
    ArrayList<String> mensajes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner_puertos);
    }



    public void scannerPuertos(View view) {
        textoEditable = (TextView) findViewById(R.id.textoEditable);
        new IniciaScanner().execute();
    }

    private class IniciaScanner extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            PortScanner x = new PortScanner();
            mensajes = x.iniciaScanner(textoEditable);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            textoEditable.setText("");
            for (int i = 0; i < mensajes.size(); i++) {
                textoEditable.append("\n" + mensajes.get(i));
                super.onPostExecute(result);
            }
        }
    }
}
