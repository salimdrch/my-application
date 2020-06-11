package com.example.jeux.gestionCompte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jeux.AccueilActivity;
import com.example.jeux.dataBase.DataBase;
import com.example.jeux.MainActivity;
import com.example.jeux.R;

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
        this.editTxt = findViewById( R.id.editTxt); // Récuperer le editText
        this.editPwd = findViewById( R.id.editPwd );
        this.b_valider = findViewById( R.id.b_valider );
        this.b_retour = findViewById( R.id.b_retour );
        dataBase = new DataBase( this );

        b_valider.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTxt.getText().toString().trim();
                String mdp = editPwd.getText().toString().trim();
                if (!(name.equals("") && mdp.equals("")) && dataBase.inDataBase( name,mdp )){
                    Intent i = new Intent( getApplicationContext(), AccueilActivity.class );
                    i.putExtra( "idJoueur", dataBase.getIdJoueur( name ));
                    Log.i( "DataBase", "id->" + dataBase.getIdJoueur( name ));

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
