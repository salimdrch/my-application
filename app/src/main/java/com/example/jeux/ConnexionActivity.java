package com.example.jeux;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConnexionActivity extends AppCompatActivity {

    private EditText editTxt;
    private EditText editPwd;
    private Button b_valider;
    private Button b_retour;
    private DataBase dataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_connexion );
        this.editTxt = (EditText) findViewById( R.id.editTxt); // Récuperer le editText
        this.editPwd = (EditText) findViewById( R.id.editPwd );
        this.b_valider = (Button) findViewById( R.id.b_valider );
        this.b_retour = (Button) findViewById( R.id.b_retour );
        dataBase = new DataBase( this );

        b_valider.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTxt.getText().toString().trim();
                String mdp = editPwd.getText().toString().trim();
                if (!(name.equals("") && mdp.equals("")) && dataBase.inDataBase( name,mdp )){
                    Intent i = new Intent( getApplicationContext(), AccueilActivity.class );
                    startActivity( i );
                    finish();
                }
                Toast.makeText(getApplicationContext(),"Connexion échoué !",Toast.LENGTH_SHORT).show();

            }
        } );

        b_retour.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent r = new Intent( getApplicationContext(), MainActivity.class );
                startActivity( r );
                finish();
            }
        } );
    }
}
