package com.example.jeux.play;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jeux.AccueilActivity;
import com.example.jeux.MainActivity;
import com.example.jeux.R;
import com.example.jeux.dataBase.DataBase;

public class ScoreActivity extends AppCompatActivity {

    private int id;
    private TextView textView;
    private ImageButton imageButton;
    private DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_score );
        textView = findViewById( R.id.score );
        imageButton = findViewById( R.id.b_retour );
        Intent i = getIntent();
        id = i.getIntExtra( "idJoueur",0 );
        int score = i.getIntExtra( "score",0 );
        dataBase = new DataBase( this );
        dataBase.ajouterScores( score,id );
        Log.i( "DataBase", "score ajouter" );
        Log.i( "DataBase", "id" + id );
        textView.setText( "Score : " + score );
        imageButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( getApplicationContext(), AccueilActivity.class );
                i.putExtra( "idJoueur", id );
                Log.i( "DataBase","id-> " + id );
                startActivity( i );
                finish();
            }
        } );
    }
}
