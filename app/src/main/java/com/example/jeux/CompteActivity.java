package com.example.jeux;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class CompteActivity extends AppCompatActivity {

    private ImageButton imageButton;
    private EditText name;
    private EditText firstname;
    private EditText mdp;
    private Button valide;
    private DataBase dataBase;

    public DataBase getDataBase() {
        return dataBase;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_compte );
        this.imageButton = (ImageButton) findViewById( R.id.img_bt );
        this.name = (EditText) findViewById( R.id.name );
        this.firstname = (EditText) findViewById( R.id.firstname );
        this.mdp = (EditText) findViewById( R.id.mdp );
        this.valide = (Button) findViewById( R.id.b_valide );
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
                    startActivity( i );
                    finish();
                }
                Toast.makeText(getApplicationContext(),"Pseudo existe déjà !",Toast.LENGTH_SHORT).show();


            }
        } );


    }
}
