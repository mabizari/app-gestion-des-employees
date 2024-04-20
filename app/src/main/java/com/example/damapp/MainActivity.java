package com.example.damapp;

import static android.widget.Toast.LENGTH_LONG;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RouteListingPreference;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private static final int RESULT_LOAD_IMAGE = 1;
    private static final int PICK_FROM_GALLERY = 2;
    byte[] arrayimg;
    Bitmap thumbnail = null;
    Uri selectedImage;
    Button add;
    String picturePath;
    Button consult;
    Button consult2;
    EditText id,first_name,last_name,tel ,email;
    DBhelper  db;
    int i=1;

    @Override
    protected void onCreate(  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Damapp);
       setContentView(R.layout.activity_main);
        onNightModeChanged(R.style.Base_Theme_Damapp);
        id=findViewById(R.id.id);
        first_name=findViewById(R.id.fname);
        last_name=findViewById(R.id.lname);
        tel=findViewById(R.id.tel);
        email=findViewById(R.id.email);

        //CONSULT EMPLOYREE GRID
        consult= findViewById(R.id.btConsult);
        consult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this, addActivityGRID2.class );
                startActivity(intent);
            }
        });

        // CONSULT EMPLOYEE LIST
        consult2= findViewById(R.id.btnConsult2);

        consult2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this, addActivity2.class );
                startActivity(intent);
            }
        });

        // ADD EMPLOYEE
        add = findViewById(R.id.btnadd);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!id.getText().toString().isEmpty()) {
                    db = new DBhelper(MainActivity.this);
                    Employee e = new Employee(id.getText().toString(), first_name.getText().toString(), last_name.getText().toString(), Integer.parseInt(tel.getText().toString().trim()), email.getText().toString(),arrayimg);

                    db.addEmployee(e);
                    id.setText("");
                    first_name.setText("");
                    last_name.setText("");
                    tel.setText("");
                    email.setText("");
                }else{
                    Toast.makeText(MainActivity.this,"all field are required", LENGTH_LONG).show();
                }

            }
        });

        ImageView image=findViewById(R.id.floatingActionButton);
        image.setOnClickListener(new View.OnClickListener() {
            //@RequiresExtension(extension = Build.VERSION_CODES.R, version = 2)
            @Override
            public void onClick(View v) {

                Intent in = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(in,RESULT_LOAD_IMAGE);



            }});


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {

            selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();
            thumbnail=uriToBitmap(selectedImage);
            arrayimg=convertBitmapToByteArray(thumbnail, Bitmap.CompressFormat.JPEG, 100);;
            Toast.makeText(MainActivity.this,"it work"+arrayimg,LENGTH_LONG).show();

        }
    }

    private Bitmap uriToBitmap(Uri selectedFileUri) {
        Bitmap image = null;
        try {
            ParcelFileDescriptor parcelFileDescriptor =
                    getContentResolver().openFileDescriptor(selectedFileUri, "r");
            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
            image = BitmapFactory.decodeFileDescriptor(fileDescriptor);

            parcelFileDescriptor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public static byte[] convertBitmapToByteArray(Bitmap bitmap, Bitmap.CompressFormat compressFormat, int quality) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, quality, stream);
        return stream.toByteArray();
    }

    public void setLocal(Activity activity, String langcode){

        Locale locale=new Locale(langcode);
        Locale.setDefault(locale);
        Configuration config=getResources().getConfiguration();
        config.setLocale(locale);
        getResources().updateConfiguration(config,getResources().getDisplayMetrics());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.options,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.item_francais) {
            setLocal(MainActivity.this,"fr");
            finish();
            startActivity(getIntent());

        }else if(item.getItemId()==R.id.item_engilsh){
            setLocal(MainActivity.this,"en");
            finish();
            startActivity(getIntent());
        }else if(item.getItemId()==R.id.item_arab){
            setLocal(MainActivity.this,"ar");
            finish();
            startActivity(getIntent());
        }
        return super.onOptionsItemSelected(item);
    }
}
