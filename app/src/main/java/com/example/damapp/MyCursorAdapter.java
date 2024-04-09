package com.example.damapp;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyCursorAdapter extends CursorAdapter {

     public MyCursorAdapter (Context context, Cursor cursor) {
         super(context,cursor,0);
     }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
       TextView text_id = view.findViewById(R.id.text1);
        TextView text_first_name= view.findViewById(R.id.text2);
       TextView text_last_name = view.findViewById(R.id.text3);
       TextView  text_tel_number = view.findViewById(R.id.text4);
       TextView  text_email = view.findViewById(R.id.text5);
        ImageView path_image =view.findViewById(R.id.image);

         String id = cursor.getString(0);
        String first_name = cursor.getString(1);
        String last_name= cursor.getString(2);
        int tel = cursor.getInt(3);
        String email = cursor.getString(4);
        byte[] img_uri=cursor.getBlob(5);

        text_id.setText(id);
        text_first_name.setText(first_name);
        text_last_name.setText(last_name);
        text_tel_number.setText(String.valueOf(tel));
        text_email.setText(email);
        Bitmap thumbnail= BitmapFactory.decodeByteArray(img_uri,0,img_uri.length);
        path_image.setImageBitmap(thumbnail);
        //TextView t1=view.findViewById(R.id.text11);
        ///TextView t2=view.findViewById(R.id.text22);
        //TextView t3=view.findViewById(R.id.text33);
        //TextView t4=view.findViewById(R.id.text44);
       /// TextView t5=view.findViewById(R.id.text55);

        //String idd = cursor.getString(0);
        //String first_namee = cursor.getString(1);
       // String last_namee= cursor.getString(2);
       /// int tell = cursor.getInt(3);
        //String emaill = cursor.getString(4);

        //text_id.setText(idd);
       // text_first_name.setText(first_namee);
       // text_last_name.setText(last_namee);
       /// text_tel_number.setText(String.valueOf(tell));
       // text_email.setText(emaill);

        /// <androidx.cardview.widget.CardView
       // android:layout_width="100dp"
       // android:layout_height="100dp"
       // app:cardCornerRadius="50dp" >
       // <ImageView
        //android:id="@+id/image"
       // android:layout_width="match_parent"
       //android:layout_height="match_parent"
        //android:background="@color/bleu"
        //android:src="@drawable/baseline_person_24" />
        //<com.google.android.material.floatingactionbutton.FloatingActionButton
        //app:shapeAppearance="@style/circular"
        //android:id="@+id/floatingActionButton"
        //android:layout_width="wrap_content"
       // android:layout_height="wrap_content"
        //android:layout_marginTop="50dp"
        //android:clickable="true"
        //app:srcCompat="@drawable/baseline_add_a_photo_24" />
    //</androidx.cardview.widget.CardView>




         //<SearchView

       // android:id="@+id/Searchl"
       // android:layout_width="match_parent"
       // android:layout_height="wrap_content"
       // android:queryHint="search here..."
       // android:layout_marginLeft="15dp"
       // android:layout_marginRight="15dp"
        //android:layout_marginTop="10dp"
        //android:layout_marginBottom="10dp"
       // android:iconifiedByDefault="false"
          //      />




    }
}
