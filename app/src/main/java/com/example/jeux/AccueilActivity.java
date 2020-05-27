package com.example.jeux;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class AccueilActivity extends AppCompatActivity {

    private ImageButton imageButton;
    private Button bt_play;
    private Button bt_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_accueil );
        this.imageButton = (ImageButton) findViewById( R.id.img_bt );
        this.bt_play = (Button) findViewById( R.id.b_play );
        this.bt_score = (Button) findViewById( R.id.b_score );
        Toast.makeText(getApplicationContext(),"Connecté !",Toast.LENGTH_SHORT).show();
        imageButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent( getApplicationContext(),MainActivity.class );
                startActivity( i );
                finish();
            }
        } );
    }
}
