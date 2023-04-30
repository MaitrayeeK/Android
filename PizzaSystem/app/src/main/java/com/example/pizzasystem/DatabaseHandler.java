package com.example.pizzasystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import com.example.pizzasystem.OrderMaster;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static String database = "PIZZASYSTEM";

    public DatabaseHandler(Context context, int version) {
        super(context, database, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE ORDERMASTER(ID INTEGER PRIMARY KEY, TABLENO INTEGER, CUSTOMER TEXT, MOBILE TEXT, ORDERDATE TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE ORDERDETAIL(ID INTEGER PRIMARY KEY,ORDERID INTEGER, PIZZACATEGORY TEXT, PIZZASIZE TEXT, QUANTITY INTEGER, PRICE INTEGER,FOREIGN KEY(ORDERID) REFERENCES ORDERMASTER(ID))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ORDERMASTER");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ORDERDETAIL");
        onCreate(sqLiteDatabase);
    }

    public long adddata(OrderMaster orderMaster) {
        ContentValues c = new ContentValues();
        c.put("TABLENO" , orderMaster.getTableno());
        c.put("CUSTOMER" , orderMaster.getCustomer());
        c.put("MOBILE" , orderMaster.getMobile());
        c.put("ORDERDATE" , orderMaster.getDate());

        SQLiteDatabase db = getWritableDatabase();

        return db.insert("ORDERMASTER", null, c);
    }

    public long addoddata(OrderDetail orderDetail) {
        ContentValues c = new ContentValues();
        c.put("ORDERID", orderDetail.getOid());
        c.put("PIZZACATEGORY", orderDetail.getCategory());
        c.put("PIZZASIZE", orderDetail.getSize());
        c.put("QUANTITY", orderDetail.getQty());
        c.put("PRICE", orderDetail.getPrice());

        SQLiteDatabase db = getWritableDatabase();

        return db.insert("ORDERDETAIL", null, c);
    }

    public String[] getallorder() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT CUSTOMER FROM ORDERMASTER", null);
        String[] result = new String[c.getCount()];
        c.moveToFirst();
        for(int i = 0; i < c.getCount(); i++){
            String row = c.getString(0);
            result[i] = row;
            c.moveToNext();
        }
        return result;
    }

    public ArrayList<OrderDetail> getorderdetailsbycustomer(String customer) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor co = db.rawQuery("SELECT ID FROM ORDERMASTER WHERE CUSTOMER = ?", new String[] {customer});
        String id = null;
        co.moveToFirst();
        for(int i = 0; i < co.getCount(); i++){
            id = co.getString(0);
            co.moveToNext();
        }
        Cursor c = db.rawQuery("SELECT * FROM ORDERDETAIL WHERE ORDERID = ?", new String[] {id});
        ArrayList<OrderDetail> result = new ArrayList<>();
        if(c.moveToFirst()) {
            do{
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setId(Integer.parseInt(c.getString(0)));
                orderDetail.setCategory(c.getString(2));
                orderDetail.setSize(c.getString(3));
                orderDetail.setQty(Integer.parseInt(c.getString(4)));
                orderDetail.setPrice(Integer.parseInt(c.getString(5)));
                result.add(orderDetail);
            } while (c.moveToNext());
        }
        return result;
    }

    public ArrayList<OrderDetail> getorderdetailbyorderid(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM ORDERDETAIL WHERE ORDERID = ?", new String[] {id});
        ArrayList<OrderDetail> result = new ArrayList<>();
        if(c.moveToFirst()) {
            do{
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setId(Integer.parseInt(c.getString(0)));
                orderDetail.setCategory(c.getString(2));
                orderDetail.setSize(c.getString(3));
                orderDetail.setQty(Integer.parseInt(c.getString(4)));
                orderDetail.setPrice(Integer.parseInt(c.getString(5)));
                result.add(orderDetail);
            } while (c.moveToNext());
        }
        return result;
    }
}
