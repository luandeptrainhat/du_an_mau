package com.example.myapplication.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.database.Dbhelper;
import com.example.myapplication.model.Sach;

import java.util.ArrayList;

public class SachDAO {
    Dbhelper dbhelper;
    public SachDAO(Context context){
        dbhelper = new Dbhelper(context);
    }

    //lấy toàn bộ sách có trong thư viên
    public ArrayList<Sach> getDSDauSach(){
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SACH",null);
        if (cursor.getCount()!=0){
            cursor.moveToFirst();
            do {
                list.add(new Sach(
                   cursor.getInt(0),
                   cursor.getString(1),
                   cursor.getInt(2),
                   cursor.getInt(3)
                ));
            }while (cursor.moveToNext());
        }


        return list;
    }

}
