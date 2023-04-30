package com.example.crudapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String db = "empdb";
    public static final String tb = "emptb";
    public static final String col_id = "id";
    public static final String col_name = "name";
    public static final String col_dept = "dept";
    public static final int version = 1;

    public DatabaseHandler(@Nullable Context context, int version) {
        super(context, db, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + tb + "(" + col_id + " INTEGER PRIMARY KEY, " + col_name + " TEXT, " + col_dept + " TEXT)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tb);
        onCreate(sqLiteDatabase);
    }

    public void adddata(Employee employee) {
        ContentValues values = new ContentValues();
        values.put(col_name, employee.getName());
        values.put(col_dept, employee.getDepartment());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(tb, null, values);
        db.close();
    }

    public ArrayList<Employee> getalldata() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + tb, null);

        ArrayList<Employee> employees = new ArrayList<>();
        if(cursor.moveToFirst()) {
            do {
                Employee employee = new Employee();
                employee.setId(Integer.parseInt(cursor.getString(0)));
                employee.setName(cursor.getString(1));
                employee.setDepartment(cursor.getString(2));
                employees.add(employee);
            } while (cursor.moveToNext());
        }

        return employees;
    }

    public void edit(Employee employee) {

    }

    public void delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + tb + " WHERE " + col_id + " = " + id);
    }
}
