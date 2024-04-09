package com.example.damapp;

import static com.example.damapp.R.*;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class addActivity5 extends AppCompatActivity {

    EditText text1,text2,text3;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add5);


                text1 = findViewById(R.id.send1);
                text2 = findViewById(R.id.send2);
                text3 = findViewById(R.id.send3);
                send = findViewById(R.id.button);

                // attach setOnClickListener to button with Intent object define in it
                send.setOnClickListener(view -> {
                    String email_send = text1.getText().toString();
                    String email_subject = text2.getText().toString();
                    String email_body = text3.getText().toString();

                    // define Intent object with action attribute as ACTION_SEND
                    Intent intent = new Intent(Intent.ACTION_SEND);

                    // add three fields to intent using putExtra function
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email_send});
                    intent.putExtra(Intent.EXTRA_SUBJECT, email_subject);
                    intent.putExtra(Intent.EXTRA_TEXT, email_body);

                    // set type of intent
                    intent.setType("message/rfc822");

                    // startActivity with intent with chooser as Email client using createChooser function
                    startActivity(Intent.createChooser(intent, "Choose an Email client :"));
                });
            }
        }











