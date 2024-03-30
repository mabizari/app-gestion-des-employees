package com.example.mabizari_abdallah__chzd;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

public class Data extends CursorAdapter {

    Data(Context contxt,Cursor data){
        super(contxt,data);



    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.activity_emploee_item,parent,
                false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {


    }
}
