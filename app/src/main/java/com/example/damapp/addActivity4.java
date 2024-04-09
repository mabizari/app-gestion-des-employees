package com.example.damapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class addActivity4 extends AppCompatActivity {

    Button call , SMS, SendSMS ;
    EditText editTel , editSMS ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add4);

        // PHONE CALL
        editTel = findViewById(R.id.EditTel);
        call = findViewById(R.id.Call);


        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phoneNo = editTel.getText().toString();

                if(!TextUtils.isEmpty(phoneNo)) {
                    String dial = "tel:" + phoneNo;
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
                   // editTel.setText("");
                }else {
                    Toast.makeText(addActivity4.this, "Enter a phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //SEND SMS
        editSMS = findViewById(R.id.EditSMS);
        SendSMS = findViewById(R.id.Sms);

        SendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = editSMS.getText().toString();
                String phoneNo = editTel.getText().toString();
                if(!TextUtils.isEmpty(message) && !TextUtils.isEmpty(phoneNo)) {
                    Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phoneNo));
                    smsIntent.putExtra("sms_body", message);
                    startActivity(smsIntent);
                    editSMS.setText("");
                    editTel.setText("");
                }
                else {
                    Toast.makeText(addActivity4.this, "Enter a message First", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //SEND EMAIL
        SMS=findViewById(R.id.Email);
        SMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( addActivity4.this, addActivity5.class );
                startActivity(intent);
            }
        });




    }
}