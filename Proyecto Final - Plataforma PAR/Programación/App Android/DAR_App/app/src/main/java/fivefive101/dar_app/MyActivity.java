package fivefive101.dar_app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MyActivity extends ActionBarActivity {

    public final static String EXTRA_MESSAGE = "com.fivefive101.dar_app.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
    }


    public void startAbout(View view)
    {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }
    public void startListaPginas(View view)
    {
        Intent intent = new Intent(this, ListaPaginas.class);
        startActivity(intent);
    }
    public void startScannerIP(View view)
    {
        Intent intent = new Intent(this, ScannerIP.class);
        startActivity(intent);
    }
    public void startScannerPuertos(View view)
    {
        Intent intent = new Intent(this, ScannerPuertos.class);
        startActivity(intent);
    }
    public void startPrueba(View view) {
        Intent intent = new Intent(this, PruebaUDP.class);
        startActivity(intent);
    }
}
