package com.example.damapp;


import static android.content.Intent.ACTION_PICK;
import static android.widget.Toast.LENGTH_LONG;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;

public class addActivity3 extends AppCompatActivity  {
    private static final int RESULT_LOAD_IMAGE = 1;
    private static final int PICK_FROM_GALLERY = 2;
    Uri selectedImage;
    byte[] byteArray;
    String picturePath;
    private Bitmap thumbnail;
    ImageView imageView;
    String id;
    TextView t1, t2, t3, t4, t5;
    Button Delete, Update,Contact;
    ImageView fbtn;
   // private Instant ImagePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add3);

         id = getIntent().getExtras().getString("id");
        String fn = getIntent().getExtras().getString("fn");
        String ln = getIntent().getExtras().getString("ln");
        int te = getIntent().getExtras().getInt("te");
        String eml = getIntent().getExtras().getString("eml");
        byte[] path_img=getIntent().getByteArrayExtra("image");
        thumbnail=BitmapFactory.decodeByteArray(path_img,0,path_img.length);

        t1 = findViewById(R.id.text11);
        t2 = findViewById(R.id.text22);
        t3 = findViewById(R.id.text33);
        t4 = findViewById(R.id.text44);
        t5 = findViewById(R.id.text55);
        imageView=findViewById(R.id.image);

        t1.setText(id);
        t2.setText(fn);
        t3.setText(ln);
        t4.setText(String.valueOf(te));
        t5.setText(eml);
        imageView.setImageBitmap(thumbnail);
        // UPDATE
        Update = findViewById(R.id.btnUpdate);
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBhelper d=new DBhelper(addActivity3.this);
                String i=t1.getText().toString().trim();
                String f=t2.getText().toString().trim();
                String l=t3.getText().toString().trim();
                int t=  Integer.valueOf(t4.getText().toString().trim()) ;
                String e=t5.getText().toString().trim();
                //thumbnail=imageView.getDrawingCache();
                //byte[] byteArray = convertBitmapToByteArray(thumbnail, Bitmap.CompressFormat.JPEG, 100);
                Toast.makeText(addActivity3.this,"it work"+byteArray,LENGTH_LONG).show();
                d.update(id,i, f, l, t, e ,byteArray);
                id=i;
            }
        });

        // DELETE
        Delete = findViewById(R.id.btnDelete);
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBhelper dd = new DBhelper(addActivity3.this);
                String i = t1.getText().toString().trim();
                dd.delete(i);
            }
        });

        //CONTACT
        Contact=findViewById(R.id.btnContact);
        Contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( addActivity3.this, addActivity4.class );
                startActivity(intent);
            }
        });


       fbtn = findViewById(R.id.floatingActionButton);

       fbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent in = new   Intent(ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
               startActivityForResult(in,RESULT_LOAD_IMAGE);
           }
       });
        //MaterialButton pickImage = findViewById(R.id.pickImage);





        //fbtn = findViewById(R.id.floatingActionButton);
        //fbtn.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View v) {
               // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                  //  ImagePicker.with((TemporalAdjuster) addActivity3.this).clone().compress(1024).maxResultsize(1080, 1080).start();
                //  boolean a = true;
               // if (a == true)
                  //  if (!checkCameraPermission()){
                      //  requestCameraPermissions();

                        // }else
                       // PickImage(); }
              //  else{
                     //  if (!checkStoragePermission()) {
                          //  requestStoragePermissions();
                       // } else {
                           //PickImage();
                      //  }
                   //}





           // }

        //});



    }











   // private void PickImage() {

       // CropImage.activity().start(this);



   // }

   // private void requestStoragePermissions() {
       // requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},100);
   // }

   // private boolean checkStoragePermission() {
     //   boolean bb = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
      //  return bb;
   // }

  //  private void requestCameraPermissions() {
    //    requestPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},100);

   // }

   // private boolean checkCameraPermission() {
      //  boolean b = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
       // boolean bb = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
       // return  b&&bb;

  //  }


    //private class Builder {


     //   private ActivityResultContracts.PickVisualMedia.ImageOnly mediaType;

      //  public void setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly mediaType) {
         //   this.mediaType = mediaType;
       // }

      //  public ActivityResultContracts.PickVisualMedia.ImageOnly getMediaType() {
          //  return mediaType;
      //  }
  //  }
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
              byteArray = convertBitmapToByteArray(thumbnail, Bitmap.CompressFormat.JPEG, 100);
              Toast.makeText(addActivity3.this,"it work"+byteArray,LENGTH_LONG).show();
              imageView.setImageBitmap(thumbnail);
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
}
