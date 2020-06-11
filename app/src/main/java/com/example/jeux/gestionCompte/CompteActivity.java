package com.example.jeux.gestionCompte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.jeux.AccueilActivity;
import com.example.jeux.dataBase.DataBase;
import com.example.jeux.MainActivity;
import com.example.jeux.R;

public class CompteActivity extends AppCompatActivity {

    private ImageButton imageButton;
    private EditText name;
    private EditText firstname;
    private EditText mdp;
    private Button valide;
    private DataBase dataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_compte );
        this.imageButton = findViewById( R.id.img_bt );
        this.name = findViewById( R.id.name );
        this.firstname = findViewById( R.id.firstname );
        this.mdp = findViewById( R.id.mdp );
        this.valide = findViewById( R.id.b_valide );
        dataBase = new DataBase( this );

        imageButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent r = new Intent( getApplicationContext(), MainActivity.class );
                startActivity( r );
                finish();
            }
        });

        valide.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!( name.getText().toString().trim().equals("") &&
                        firstname.getText().toString().trim().equals("") &&
                        mdp.getText().toString().trim().equals("")) &&
                        ! (dataBase.pseudoInDataBase( name.getText().toString().trim() )))
                {
                    dataBase.insertJoueur( name.getText().toString(), firstname.getText().toString(), mdp.getText().toString() );
                    Intent i = new Intent( getApplicationContext(), AccueilActivity.class );
                    i.putExtra( "idJoueur", dataBase.getIdJoueur( name.getText().toString() ));
                    Log.i( "DataBase", "id->" + dataBase.getIdJoueur( name.getText().toString() ));
                    startActivity( i );
                    finish();
                }
                Toast.makeText(getApplicationContext(),"Pseudo existe déjà !",Toast.LENGTH_SHORT).show();


            }
        } );


    }
}
