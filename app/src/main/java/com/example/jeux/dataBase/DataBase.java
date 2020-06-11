package com.example.jeux.dataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.jeux.R;
import com.example.jeux.type.Questions;
import com.example.jeux.type.Reponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DataBase extends SQLiteOpenHelper{

    public static  final String DATABASE_NAME = "Game.db";
    public static final int DATABASE_VERSION = 1;
    private Context context;

    public DataBase(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;
        Log.i("DataBase", "Base de donnée ouverte");
    }

    // Création des tables  pour la base de donnée
    @Override
    public void onCreate(SQLiteDatabase db) {
        /*Requete sql*/

        // Table joueur
        String sql_Joueur = "create table Joueurs ("
                      + "idJoueur integer primary key autoincrement,"
                      + "name text not null,"
                      + "firstName text not null, "
                      + "mdp password not null"
                      + ")";
        db.execSQL( sql_Joueur ); // executer la requete
        Log.i("DataBase", "table_joueurs créer");

        // Table Score
        String  sql_Score = "create table Scores ("
                          + "idScore integer primary key autoincrement,"
                          + "score integer not null,"
                          + "idJoueur integer not null,"
                          + "foreign key ('idJoueur') references Joueurs('idJoueur')"
                          + ")";
        db.execSQL( sql_Score );
        Log.i( "DataBase", "table score créer");

        // Table Thème
        String sql_theme = "create table Theme ("
                         + "idTheme integer primary key autoincrement,"
                         + "theme text not null"
                         + ")";
        db.execSQL( sql_theme );
        Log.i( "DataBase", "table theme créer" );

        // Table question
        String sql_question = "create table Questions ("
                + "idQuestion integer primary key autoincrement,"
                + "question text not null,"
                + "idTheme integer not null,"
                + "foreign key ('idTheme') references Theme('idTheme')"
                + ")";
        db.execSQL( sql_question );
        Log.i( "DataBase", "table question créer" );

        // Table reponse
        String sql_reponse = "create table Reponses ("
                + "idReponse integer primary key autoincrement,"
                + "reponse text not null,"
                + "bonneReponse integer not null,"
                + "idQuestion integer not null,"
                + "foreign key ('idQuestion') references Questions('idQuestion')"
                + ")";
        db.execSQL( sql_reponse );
        importCsvToBdd( context );
        Log.i( "DataBase", "table reponse créer" );
    }

    // METHODE PAR DEFAULT SI ON VEUT FAIRE UNE MAJ DE LA BASE DE DONNEE
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /*  INSERER UN JOUEUR */
    public void insertJoueur(String name, String firstName, String mdp){
        String strsql_insert = "insert into Joueurs (name, firstName, mdp) values ('" + name + "','" + firstName + "', '" + mdp +"')";
        getWritableDatabase().execSQL( strsql_insert );
        Log.i( "DataBase", "Joueur inséré" );
    }

    // Verifier si le joueur est dans la base de donnée NAME = PSEUDO
    public boolean inDataBase(String name, String mdp){
        String strsql = "Select name, mdp from Joueurs where name = '" + name + "' and mdp = '" + mdp + "'";
        Cursor c =  getWritableDatabase().rawQuery( strsql, null );
        if (c.getCount() != 0){
            Log.i( "DataBase", "Joueur dans la base de donnée" );
            c.close();
            return true;
        }
        Log.i( "DataBase", "Joueur n'est pas dans la base de donnée" );
        c.close();
        return false;
    }

    // Verifier si le pseudo existe déjà lors de la création du compte
    public boolean pseudoInDataBase(String name){
        String strsql = "Select name from Joueurs where name = '" + name + "'";
        Cursor c = getWritableDatabase().rawQuery( strsql, null );
        if (c.getCount() != 0){
            Log.i( "DataBase", "Pseudo dans la base de donnée" );
            c.close();
            return true;
        }
        Log.i( "DataBase", "Pseudo n'est pas dans la base de donnée" );
        c.close();
        return false;
    }

    public void ajouterScores(int score, int idJoueur){
        String str_sql = "insert into Scores (score,idJoueur) values ('" + score + "','" + idJoueur + "')";
        getWritableDatabase().execSQL( str_sql );
        Log.i( "DataBase", "Score ajouté" );
    }

    // Inserer theme
    public void insertTheme( String theme){
        String sql_theme1 = "insert into Theme (theme) values ('" + theme +"')";
        getWritableDatabase().execSQL( sql_theme1 );
    }

    //theme dans la base de donnée
    public boolean themeInDataBase(String theme){
        String strsql = "Select * from Theme where theme = '" + theme + "'";
        Cursor c =  getWritableDatabase().rawQuery( strsql, null );
        if (c.getCount() != 0){
            Log.i( "DataBase", "Theme dans la base de donnée" );
            c.close();
            return true;
        }
        Log.i( "DataBase", "Theme n'est pas dans la base de donnée" );
        c.close();
        return false;
    }

    // retourner id du joueur
    public int getIdJoueur(String pseudo){
        String str_sql = "Select idJoueur from Joueurs where name = '"+ pseudo +"'";
        Cursor c = getWritableDatabase().rawQuery( str_sql,null );
        c.moveToFirst();
        Log.i("DataBase", "IdJoueur recuperer" + c.getInt( 0 ) );
        return c.getInt( 0 );
    }

    // retourner l'id du theme
    public int getIdTheme(String theme){
        String sql = "Select idTheme from Theme where theme = '" + theme +"'";
        Cursor c = getWritableDatabase().rawQuery( sql,null );
        c.moveToFirst();
        Log.i("DataBase", "IdTheme recuperer" + c.getInt( 0 ) );
        return c.getInt( 0 );
    }


    // retourner l'id du question
    public int getIdQuestion(String question){
        String sql = "Select idQuestion from Questions where question = '" + question +"'";
        Cursor c = getWritableDatabase().rawQuery( sql,null );
        c.moveToFirst();
        Log.i("DataBase", "IdQuestion recuperer" + String.valueOf( c.getInt( 0 )));
        return c.getInt( 0 );
    }

    // inserer la question dans la base de donnée
    public void insererQuestion(int idTheme, String question){
        String sql = "insert into Questions (question, idTheme) values ('" + question + "','" + idTheme + "')";
        getWritableDatabase().execSQL( sql );
    }

    // inserer la reponse dans la base de donnée
    public void insererReponse(int idQuestion, String reponse, int bonneReponse){
        String sql = "insert into Reponses (reponse, bonneReponse, idQuestion) values ('" + reponse + "','" + bonneReponse + "','" + idQuestion +"')";
        getWritableDatabase().execSQL( sql );
    }

    //recuperer liste des reponses
    public ArrayList<Reponse> recupR(int idQuestion){
        ArrayList<Reponse>  rep = new ArrayList<>(  );
        String sql = "Select * from Reponses where idQuestion = " + idQuestion;
        Cursor rCursor = getWritableDatabase().rawQuery( sql,null );
        for(rCursor.moveToFirst(); !rCursor.isAfterLast(); rCursor.moveToNext()){
            rep.add( new Reponse( rCursor.getInt( 0 ), rCursor.getString( 1 ), rCursor.getInt( 2 ) ) );
        }
        return rep;
    }

    //recuperer liste des questions
    public ArrayList<Questions> recupQ(int idTheme){
        ArrayList<Questions>  q = new ArrayList<>(  );
        String sql = "Select * from Questions where idTheme = " + idTheme + " order by random() limit 5";
        Cursor qCursor = getWritableDatabase().rawQuery( sql,null );
        for(qCursor.moveToFirst(); !qCursor.isAfterLast(); qCursor.moveToNext()){
            q.add( new Questions( qCursor.getInt( 0 ), qCursor.getString( 1 ), recupR(qCursor.getInt( 0 ) ) ));
        }
        return q;
    }

    // recuperer la liste de score
    public ArrayList<Integer> recupScore(int idJoueur){
       ArrayList<Integer> s = new ArrayList<>(  );
       String sql = "Select score from Scores where idJoueur = "+ idJoueur;
       Cursor cursor = getWritableDatabase().rawQuery( sql,null );
       for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
           s.add(cursor.getInt( 0 ) );
       }
       Log.i( "DataBase", "TabScoreRecuperer" );
        return s;
    }



    // Import CSV to BDD

    public void importCsvToBdd(Context ctx) {
        try {
            InputStream file = ctx.getResources().openRawResource( R.raw.questionr ); // Recupérer le fichier csv
            InputStreamReader FileReader = new InputStreamReader( file ); // lire le fichier
            BufferedReader r = new BufferedReader( FileReader ); // enregistre dans une memoire
            String line;
            String value;
            ArrayList<String> dataRow;
            StringTokenizer token; // decouper la ligne du csv
            while ((line = r.readLine()) != null) {
                dataRow = new ArrayList<>();
                token = new StringTokenizer( line, ";" ); // decouper la ligne à chaque ;
                while (token.hasMoreElements()) { // tant que y a des cases dans le tableau, il continu
                    value = (String) token.nextElement(); // Caste en String les lignes récupérées
                    dataRow.add( value ); // met dans l' arrayList
                }
                String theme = dataRow.get( 0 );
                String question = dataRow.get( 1 );
                String reponse1= dataRow.get( 2 );
                int bonneReponse1 = Integer.parseInt(dataRow.get(3));
                String reponse2 = dataRow.get( 4 );
                int bonneReponse2 = Integer.parseInt(dataRow.get(5));
                String reponse3 =dataRow.get( 6 );
                int bonneReponse3 = Integer.parseInt(dataRow.get( 7 ));
                String reponse4= dataRow.get( 8 );
                int bonneReponse4 = Integer.parseInt(dataRow.get( 9 ));

                if ( ! themeInDataBase( theme )){
                    insertTheme( theme );
                 }
                int id_theme = getIdTheme( theme );
                insererQuestion( id_theme, question);
                int id_question = getIdQuestion( question );
                insererReponse( id_question, reponse1, bonneReponse1 );
                insererReponse( id_question, reponse2, bonneReponse2 );
                insererReponse( id_question, reponse3, bonneReponse3 );
                insererReponse( id_question, reponse4, bonneReponse4 );
            }
            Log.i( "DataBase","importation reussi" );
            }catch(IOException e){
                e.printStackTrace();
            }

    }

}