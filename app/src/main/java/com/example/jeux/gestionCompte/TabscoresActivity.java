package com.example.jeux.gestionCompte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.jeux.AccueilActivity;
import com.example.jeux.R;
import com.example.jeux.dataBase.DataBase;

import java.util.ArrayList;

public class TabscoresActivity extends AppCompatActivity {

    private ListView l;
    private ImageButton imageButton;
    private DataBase dataBase;
    private ArrayList<Integer> listScore = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_tabscores );
        dataBase = new DataBase( this );
        imageButton = findViewById( R.id.bt_sc );
        l = findViewById( R.id.l_score );
        Intent i = getIntent();
        int idJ = i.getIntExtra( "idJoueur",0 );
        listScore = dataBase.recupScore( idJ );
        ArrayAdapter scoreAdapter = new ArrayAdapter( this,android.R.layout.simple_list_item_1,listScore );
        l.setAdapter( scoreAdapter );

        imageButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( getApplicationContext(), AccueilActivity.class );
                Intent e = getIntent();
                i.putExtra( "idJoueur", e.getIntExtra( "idJoueur",0 ) );
                Log.i( "DataBase accueille", "id->" + i.getIntExtra( "idJoueur" ,0) );
                startActivity( i );
                finish();
            }
        } );
    }
}
