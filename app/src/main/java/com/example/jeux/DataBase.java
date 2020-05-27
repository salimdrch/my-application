package com.example.jeux;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper{

    public static  final String DATABASE_NAME = "Game.db";
    public static final int DATABASE_VERSION = 1;

    public DataBase(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
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
}