package com.example.damapp;

import android.graphics.Bitmap;
import android.net.Uri;

public class Employee  {
        public String id, first_name, last_name,email;
        byte[] image;
        public int tel;
        public Employee(String id, String first_name, String last_name, int tel, String email,byte[] image)
        {
                this.id=id ;
                this.first_name=first_name;
                this.last_name=last_name;
                this.tel=tel;
                this.email=email;
                this.image=image;
        }

}
