package com.example.mabizari_abdallah__chzd;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BddEmployees extends SQLiteOpenHelper {
    final static private String _DB_NAME="Employees";
    final static private int _DB_VERSION=1;
    BddEmployees(Context context){
        super(context,_DB_NAME,null,_DB_VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql1 ="CREATE TABLE employees (\n" +
                "        emp_no      INT             NOT NULL,\n" +
                "        birth_date  DATE            NOT NULL,\n" +
                "        first_name  VARCHAR(14)     NOT NULL,\n" +
                "        last_name   VARCHAR(16)     NOT NULL,\n" +
                "        gender      TEXT CHECK(gender IN ('M','F'))   NOT NULL,\n" +
                "        hire_date   DATE            NOT NULL,\n" +
                "        emp_img     VARCHAR(20)     NOT NUlL,\n"+
                "        PRIMARY KEY (emp_no)\n" +
                ");";

        db.execSQL(sql1);


        String sql2 ="CREATE TABLE departments (\n" +
                "    dept_no     CHAR(4)         NOT NULL,\n" +
                "    dept_name   VARCHAR(40)     NOT NULL,\n" +
                "    PRIMARY KEY (dept_no),\n" +
                "    UNIQUE  (dept_name)\n" +
                ");";
        db.execSQL(sql2);
        String sql3 ="CREATE TABLE dept_manager (\n" +
                "   emp_no       INT             NOT NULL,\n" +
                "   dept_no      CHAR(4)         NOT NULL,\n" +
                "   from_date    DATE            NOT NULL,\n" +
                "   to_date      DATE            NOT NULL,\n" +
                "   FOREIGN KEY (emp_no)  REFERENCES employees (emp_no)    ON DELETE CASCADE,\n" +
                "   FOREIGN KEY (dept_no) REFERENCES departments (dept_no) ON DELETE CASCADE,\n" +
                "   PRIMARY KEY (emp_no,dept_no)\n" +
                "); \n";
        db.execSQL(sql3);
        String sql4 ="CREATE TABLE dept_emp (\n" +
                "    emp_no      INT             NOT NULL,\n" +
                "    dept_no     CHAR(4)         NOT NULL,\n" +
                "    from_date   DATE            NOT NULL,\n" +
                "    to_date     DATE            NOT NULL,\n" +
                "    FOREIGN KEY (emp_no)  REFERENCES employees   (emp_no)  ON DELETE CASCADE,\n" +
                "    FOREIGN KEY (dept_no) REFERENCES departments (dept_no) ON DELETE CASCADE,\n" +
                "    PRIMARY KEY (emp_no,dept_no)\n" +
                ");\n";
        db.execSQL(sql4);
        String sql5 ="CREATE TABLE titles (\n" +
                "    emp_no      INT             NOT NULL,\n" +
                "    title       VARCHAR(50)     NOT NULL,\n" +
                "    from_date   DATE            NOT NULL,\n" +
                "    to_date     DATE,\n" +
                "    FOREIGN KEY (emp_no) REFERENCES employees (emp_no) ON DELETE CASCADE,\n" +
                "    PRIMARY KEY (emp_no,title, from_date)\n" +
                "); \n";
        db.execSQL(sql5);
        String sql6 ="CREATE TABLE salaries (\n" +
                "    emp_no      INT             NOT NULL,\n" +
                "    salary      INT             NOT NULL,\n" +
                "    from_date   DATE            NOT NULL,\n" +
                "    to_date     DATE            NOT NULL,\n" +
                "    FOREIGN KEY (emp_no) REFERENCES employees (emp_no) ON DELETE CASCADE,\n" +
                "    PRIMARY KEY (emp_no, from_date)\n" +
                "); \n";
        db.execSQL(sql6);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
