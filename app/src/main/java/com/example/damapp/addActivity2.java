package com.example.damapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
public class addActivity2 extends AppCompatActivity   {

    ListView lv;
    DBhelper mdb;
   static  MyCursorAdapter adapter  ;
   MyCursorAdapter mc;
     ImageView imageView;
    FloatingActionButton Button;
    SearchView searchViewl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(R.style.Theme_Damapp);
        setContentView(R.layout.activity_add2);

        mdb = new DBhelper(addActivity2.this);
        Cursor  cursor  = mdb.getEmployee( );
         lv = findViewById(R.id.lv);
         adapter = new MyCursorAdapter(addActivity2.this ,cursor );
          lv.setAdapter(adapter);

          lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  Cursor cursor1=(Cursor) adapter.getItem(position);
                  String i =cursor1.getString(0);
                  String fn =cursor1.getString(1);
                  String ln =cursor1.getString(2);
                  int  te =cursor1.getInt(3);
                  String em =cursor1.getString(4);
                  byte[] im=cursor1.getBlob(5);
                  Intent intent = new Intent( addActivity2.this,addActivity3.class );

                  intent.putExtra("id",i);
                  intent.putExtra("fn",fn);
                  intent.putExtra("ln",ln);
                  intent.putExtra("te",te);
                  intent.putExtra("eml",em);
                  intent.putExtra("image",im);
                  startActivity(intent);
              }
          });

          searchViewl=findViewById(R.id.Searchl);
        searchViewl.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                   DBhelper d = new DBhelper(addActivity2.this);
                   String nn= searchViewl.getQuery().toString().trim();

                   Cursor c= d.Search(nn);
                  // String i =c.getString(0);
                   mc =new MyCursorAdapter(addActivity2.this,c);

                   //searchViewl.setSuggestionsAdapter(mc);
                  lv.setAdapter(mc);
              }
        });

    }

}