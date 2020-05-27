package com.example.jeux;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button b_connex;
    private Button b_cc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );this.b_connex = (Button)  findViewById(R.id.b_connex);
        this.b_cc = (Button) findViewById( R.id.b_cc );
        b_connex.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent(getApplicationContext(), ConnexionActivity.class);
                startActivity(o);
                finish();
            }
        } );

        b_cc.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( getApplicationContext(), CompteActivity.class );
                startActivity( i );
                finish();
            }
        } );




    }
}
