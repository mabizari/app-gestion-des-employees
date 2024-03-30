package com.example.mabizari_abdallah__chzd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class Employees extends BddEmployees implements RequetesSql {
        Context contxt;
        private String id=null,lastName, firstName, birthdate, num_Tel, email_add, gender, hire_date,emp_img;

        Employees(Context contxt){
            super(contxt);
            this.contxt=contxt;
        }



    Employees(Context contxt,String id,String lastName,String firstName,String birthdate,String num_Tel,String email_add,String gender,String hire_date,String emp_img) {
        super(contxt);
        this.contxt=contxt;
        this.id=id;
        this.lastName =lastName;
        this.firstName =firstName;
        this.birthdate =birthdate;
        this.num_Tel =num_Tel;
        this.email_add =email_add;
        this.gender =gender;
        this.hire_date =hire_date;
        this.emp_img =emp_img;

    }

    Employees(Context contxt,String lastName,String firstName,String birthdate,String num_Tel,String email_add,String gender,String hire_date,String emp_img) {
        super(contxt);
        this.contxt=contxt;
        this.lastName =lastName;
        this.firstName =firstName;
        this.birthdate =birthdate;
        this.num_Tel =num_Tel;
        this.email_add =email_add;
        this.gender =gender;
        this.hire_date =hire_date;
        this.emp_img =emp_img;

    }

    @Override
    public Cursor selectData(int id_employee) {
        SQLiteDatabase db=this.getReadableDatabase();
        String[] args=new String[]{String.valueOf(id_employee)};
        String query="SELECT * FROM employees \n"+
                "WHERE emp_no=? ";
        Cursor cursor=db.rawQuery(query,args);

        return cursor;
    }

    @Override
    public boolean updateData(int id_employee,String lastName,String firstName,String birthdate,String num_Tel,String email_add,String gender,String hire_date,String emp_img) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result;
        String[] args = new String[]{String.valueOf(id_employee)};
        ContentValues info = new ContentValues();
        info.put("last_name",     lastName);
        info.put("first_name",    firstName);
        info.put("birth_date",    birthdate);
        info.put("gender",        gender);
        info.put("tel_num",       num_Tel);
        info.put("hire_date",     hire_date);
        info.put("email",         email_add);
        info.put("emp_img",       emp_img);

        result = db.update("empoyees", info, "emp_no=?", args);
        if(result ==-1){
            return false;
        } else
            return true;
    }

    @Override
    public boolean deleteData() {
        long result;
        SQLiteDatabase db=this.getWritableDatabase();
        String[] args=new String[]{String.valueOf(this.id)};
        result=db.delete("employees","emp_no=?",args);

        if(result!=-1){
            Toast.makeText(contxt,"operation Success employee deleted !!!!!",Toast.LENGTH_LONG).show();
            return true;
        }else {

            Toast.makeText(contxt, "operation Failed  employee data steel in the table!!!!!", Toast.LENGTH_LONG).show();
            return false;
        }

    }

    @Override
    public boolean insertData() {
        long result;
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues info =new ContentValues();
        info.put("emp_no",this.id);
        info.put("last_name",   this.lastName);
        info.put("first_name",  this.firstName);
        info.put("birth_date",  this.birthdate);
        info.put("gender",      this.gender);
        info.put("tel_num",     this.num_Tel);
        info.put("hire_date",   this.hire_date);
        info.put("email",       this.email_add);
        info.put("emp_img",     this.emp_img);

        result=db.insert("employees",null,info);
            if(result!=-1){
                this.id=String.valueOf(result);
                Toast.makeText(contxt,"operation Success  !!!!!",Toast.LENGTH_LONG).show();
                return true;
            }else {

                Toast.makeText(contxt, "operation Failed  !!!!!", Toast.LENGTH_LONG).show();
                return false;
            }
    }
    public boolean insertData(String id,String lastName,String firstName,String birthdate,String num_Tel,String email_add,String gender,String hire_date,String emp_img) {
        long result;
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues info =new ContentValues();
        info.put("emp_no",      id);
        info.put("last_name",   lastName);
        info.put("first_name",  firstName);
        info.put("birth_date",  birthdate);
        info.put("gender",      gender);
        info.put("tel_num",     num_Tel);
        info.put("hire_date",   hire_date);
        info.put("email",       email_add);
        info.put("emp_img",     emp_img);

        result=db.insert("employees",null,info);
        if(result!=-1){
            this.id=String.valueOf(result);
            Toast.makeText(contxt,"operation Success  !!!!!",Toast.LENGTH_LONG).show();
            return true;
        }else {

            Toast.makeText(contxt, "operation Failed  !!!!!", Toast.LENGTH_LONG).show();
            return false;
        }
    }
}

