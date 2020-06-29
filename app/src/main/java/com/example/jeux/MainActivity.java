package com.example.jeux;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.jeux.dataBase.DataBase;
import com.example.jeux.gestionCompte.CompteActivity;
import com.example.jeux.gestionCompte.ConnexionActivity;

public class MainActivity extends AppCompatActivity {

    private Button b_connex;
    private Button b_cc;
    private static int i = 1;
    private DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        this.b_connex = findViewById(R.id.b_connex);
        dataBase = new DataBase( this );
        this.b_cc = findViewById( R.id.b_cc );

        // sharedPreferences permet stocker une collection de clé, valeur de façon permanente
        SharedPreferences sharedPref = getPreferences( Context.MODE_PRIVATE);
        boolean firstTime = sharedPref.getBoolean("firstTime", false); // recuperer la valeur de la clé firstTime et si elle n'existe pas sa  valeur de dépard est false
        if (!firstTime){
            dataBase.importCsvToBdd( this );
            SharedPreferences.Editor editor = sharedPref.edit(); // ecrire dans la collection
            editor.putBoolean("firstTime", true); // mettre la valeur de la clé firstTime a true
            editor.apply(); // valider
        }




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
