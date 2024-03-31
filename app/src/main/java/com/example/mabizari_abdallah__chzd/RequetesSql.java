package com.example.mabizari_abdallah__chzd;

import android.database.Cursor;

public interface RequetesSql {
    Cursor selectData(int id_employee);

    boolean updateData(int id_employee,String lastName,String firstName,String birthdate,String num_Tel,String email_add,String gender,String hire_date,String emp_img);
    boolean deleteData();
    boolean insertData();
}


