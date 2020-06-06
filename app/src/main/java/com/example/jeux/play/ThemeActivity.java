package com.example.jeux.play;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jeux.R;
import com.example.jeux.dataBase.DataBase;
import com.example.jeux.type.Theme;

import java.util.ArrayList;

public class ThemeActivity extends AppCompatActivity {

    private DataBase dataBase;
    private ArrayList<String> themes = new ArrayList<>(  );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_theme );
        dataBase = new DataBase( this );
        // dataBase.insertTheme();
        Cursor themeCursor = dataBase.getWritableDatabase().rawQuery("SELECT  theme as _id, theme FROM Theme ", null);
        for (themeCursor.moveToFirst(); !themeCursor.isAfterLast(); themeCursor.moveToNext()){
            themes.add( themeCursor.getString( 1 ) );
        }
        ListView lvTheme = findViewById(R.id.list_theme);
        ArrayAdapter themeAdapter = new ArrayAdapter( this,android.R.layout.simple_list_item_1,themes );
        lvTheme.setAdapter(themeAdapter);
        lvTheme.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent( getApplicationContext(), QuestionsActivity.class );
                i.putExtra( "nomT", themes.get( position ) );
                startActivity( i );
                finish();
                Log.i( "Test"," position : " + themes.get( position ));
            }
        } );
    }
}
