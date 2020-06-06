package com.example.jeux.play;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import com.example.jeux.R;


public class ThemeCursorAdapter extends CursorAdapter {
    public ThemeCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_theme, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvBody = (TextView) view.findViewById(R.id.tvBody);
        String theme = cursor.getString(cursor.getColumnIndexOrThrow("theme"));
        tvBody.setText(theme);

    }
}
