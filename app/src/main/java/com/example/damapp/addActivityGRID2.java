package com.example.damapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

public class addActivityGRID2 extends AppCompatActivity {

    DBhelper db;
    GridView gd;
    MyCursorAdapter adapter;
    Cursor cursor;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grid2);
        GridView gd;


        DBhelper db = new DBhelper(addActivityGRID2.this);
        Cursor cursor = db.getEmployee();
        gd = (GridView) findViewById(R.id.idgd);
         adapter = new MyCursorAdapter(addActivityGRID2.this, cursor);
        gd.setAdapter(adapter);


        gd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor1 = (Cursor) adapter.getItem(position);
                String i = cursor1.getString(0);
                String f = cursor1.getString(1);
                String l = cursor1.getString(2);
                int t = cursor1.getInt(3);
                String e = cursor1.getString(4);
                byte[] im=cursor1.getBlob(5);
                Intent intent = new Intent(addActivityGRID2.this, addActivity3.class);
                intent.putExtra("id", i);
                intent.putExtra("fn", f);
                intent.putExtra("ln", l);
                intent.putExtra("te", t);
                intent.putExtra("eml", e);
                intent.putExtra("image",im);
                startActivity(intent);


            }
        });

        searchView = findViewById(R.id.SearchG);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBhelper dbb = new DBhelper(addActivityGRID2.this);

               String nnnn= searchView.getQuery().toString();

                Cursor c=dbb.Search(nnnn);
               MyCursorAdapter mc =new MyCursorAdapter( addActivityGRID2.this,c);
                gd.setAdapter(mc);




            }
        });

    }
}