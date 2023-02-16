package com.example.myapplication.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.database.Dbhelper;
import com.example.myapplication.model.ThanhVien;

import java.util.ArrayList;

public class ThanhVienDAO {
    Dbhelper dbhelper;

    public ThanhVienDAO(Context context) {
        dbhelper = new Dbhelper(context);
    }

    public ArrayList<ThanhVien> getDSThanhVien() {
        ArrayList<ThanhVien> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THANHVIEN", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new ThanhVien(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)
                ));
            } while (cursor.moveToNext());
        }
        return list;
    }
}
