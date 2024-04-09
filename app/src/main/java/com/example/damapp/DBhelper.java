package com.example.damapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;
public class DBhelper extends SQLiteOpenHelper {
    private final Context context;
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "bd";
    private static final String TABLE_NAME = "my_employees";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_First_NAME = "first_name";
    private static final String COLUMN_Last_NAME = "last_name";
    private static final String COLUMN_TEL = "telephone_number";
    private static final String COLUMN_EMAIL = "email_address";
    private static final String COLUMN_IMAGE = "img";
    Employee e;
    String idd;

    public DBhelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }


     @Override
    public void onCreate(SQLiteDatabase bd) {

    String query = "CREATE TABLE my_employees ("+
            "_id                TEXT PRIMARY KEY , " +
            "first_name         TEXT," +
            " last_name         TEXT," +
            " telephone_number  INTEGER," +
            " email_address     TEXT,"+
            " img               TEXT);";
    bd.execSQL(query);
}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Cursor getEmployee ( ) {
     SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.rawQuery("SELECT* FROM my_employees " , null );
         if (cursor.getCount() == 0) return null;
         cursor.moveToFirst();
     return cursor;
    }

    public void addEmployee( Employee e) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID, e.id);
        cv.put(COLUMN_First_NAME, e.first_name);
        cv.put(COLUMN_Last_NAME, e.last_name);
        cv.put(COLUMN_TEL, e.tel);
        cv.put(COLUMN_EMAIL, e.email);
        cv.put(COLUMN_IMAGE,e.image);
        long result = db.insert(TABLE_NAME, null, cv);

        if (result == -1) {
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "added Successfully ", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
    public void update(String where,String id,String f,String l,int te,String em,byte[] img){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues c=new ContentValues();

        c.put(COLUMN_ID,id);
        c.put(COLUMN_First_NAME,f);
        c.put(COLUMN_Last_NAME,l);
        c.put(COLUMN_TEL,te);
        c.put(COLUMN_EMAIL,em);
        c.put(COLUMN_IMAGE,img);
        long result = database.update(TABLE_NAME,c,COLUMN_ID +"=?",new String[]{where});
        if (result == -1) {
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "updated  Successfully ", Toast.LENGTH_SHORT).show();
        }
        database.close();
    }

    public void delete (String id){
        SQLiteDatabase database=this.getWritableDatabase();

        long result = database.delete(TABLE_NAME,COLUMN_ID +"=?",new String[]{id});

        if (result == -1) {
            Toast.makeText(context, " failed ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "  Employee Deleted successufly  ", Toast.LENGTH_SHORT).show();
        }

        database.close();
    }

    public Cursor Search ( String idd){

        SQLiteDatabase database=this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM my_employees WHERE _id =?", new String[]{idd } );

        if(cursor.getCount()==0) Toast.makeText(context, "Not Found", Toast.LENGTH_SHORT).show();
        else {
            Toast.makeText(context, " data exist ", Toast.LENGTH_SHORT).show();
        }

        return cursor;
    }

}





