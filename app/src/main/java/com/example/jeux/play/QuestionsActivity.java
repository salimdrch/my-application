package com.example.jeux.play;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jeux.R;
import com.example.jeux.dataBase.DataBase;
import com.example.jeux.type.Questions;
import com.example.jeux.type.Reponse;

import java.util.ArrayList;


public class QuestionsActivity extends AppCompatActivity {

    private ArrayAdapter repAdapter;
    private GridView repView;
    int score = 0;
    int v = 0;
    private TextView q;
    private DataBase dataBase;
    private ArrayList<Questions> listQuestions = new ArrayList<>();
    private ArrayList<String> rep = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_questions );
        dataBase = new DataBase( this );
        Intent i = getIntent();
        int idTheme = i.getIntExtra( "idtheme", 0 );
        listQuestions = dataBase.recupQ(idTheme);
        q = findViewById( R.id.question );
        repView = findViewById( R.id.repView );
        repAdapter = new ArrayAdapter( this,android.R.layout.simple_list_item_1,rep );
        actualiser( v );
        Log.i("actualiser", "c'est actualiser !!");
        repView.setAdapter( repAdapter );
        repView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (v < 5) {
                    Reponse r = listQuestions.get( v ).getR( position );
                    Log.i( "reponse ->" + r.getReponse() + ", BonneR", "->" + r.getBonneReponse()  );
                    if (r.getBonneReponse() == 1) {
                        score += 10;
                        Log.i( "actualiser", "score : " + score );
                        Toast.makeText(getApplicationContext(),"Bonne réponse !",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(),"Mauvaise réponse !",Toast.LENGTH_SHORT).show();
                    }
                    v += 1;}
                    if (v != 5){actualiser( v );
                    }else{
                    Intent i = new Intent( getApplicationContext(),ScoreActivity.class );
                    Intent e = getIntent();
                    i.putExtra( "idJoueur", e.getIntExtra( "idJoueur",0 ) );
                    Log.i( "DataBase accueille", "id->" + i.getIntExtra( "idJoueur" ,0) );
                    i.putExtra( "score", score );
                    startActivity( i );
                    finish();
                    Log.i( "actualiser", "nouvelle activite" );
                }
                
                Log.i( "actualiser", "v : " + v );
            }
            });
    }

    public void actualiser(int i){
        Questions question = listQuestions.get( i );
        rep = question.getSTringReponse();
        q.setText( question.getQuestion() );
        repAdapter.clear(); // retirer toute les reponses
        repAdapter.addAll( rep ); // ajouter les reponses de la nouvelle question
        repAdapter.notifyDataSetChanged(); // informer l'adapteur du changement
        Log.i("actualiser", "c'est actualiser !!");
    }

}
