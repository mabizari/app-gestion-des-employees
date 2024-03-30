package com.example.mabizari_abdallah__chzd;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.room.Database;


public class BddEmployees extends SQLiteOpenHelper {
    final static private String _DB_NAME="Employees";
    final static private int _DB_VERSION=2;

    public BddEmployees(Context context){
        super(context,_DB_NAME,null,_DB_VERSION);


    }

    @Override

    public void onCreate(SQLiteDatabase db) {

        String sql1 ="CREATE TABLE employees (\n" +
                "        emp_no      INTEGER         PRIMARY KEY AUTOINCREMENT     ,\n" +
                "        birth_date  DATE            NOT NULL,\n" +
                "        first_name  VARCHAR(14)     NOT NULL,\n" +
                "        last_name   VARCHAR(16)     NOT NULL,\n" +
                "        gender      TEXT CHECK(gender IN ('M','F'))   NOT NULL,\n" +
                "        hire_date   DATE            NOT NULL,\n" +
                "        emp_img     VARCHAR(20)     NOT NUlL,\n"+
                "        email       TEXT            NOT NULL,\n"+
                "        tel_num     INT(10)             NOT NULL);";

        db.execSQL(sql1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    String query2="DROP TABLE IF EXISTS employees";
    db.execSQL(query2);
    onCreate(db);
    }
}
