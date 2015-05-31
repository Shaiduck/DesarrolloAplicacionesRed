package fivefive101.dar_app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class About extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TextView enlace1 = (TextView) findViewById(R.id.nombre1);
        TextView enlace2 = (TextView) findViewById(R.id.nombre2);
        TextView enlace3 = (TextView) findViewById(R.id.nombre3);
        TextView enlace4 = (TextView) findViewById(R.id.nombre4);
        enlace1.setClickable(true);
        enlace2.setClickable(true);
        enlace3.setClickable(true);
        enlace4.setClickable(true);
        enlace1.setMovementMethod(LinkMovementMethod.getInstance());
        enlace2.setMovementMethod(LinkMovementMethod.getInstance());
        enlace3.setMovementMethod(LinkMovementMethod.getInstance());
        enlace4.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
