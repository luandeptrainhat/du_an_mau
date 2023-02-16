package com.example.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.database.Dbhelper;

public class ThuThuDAO {
    Dbhelper dbhelper;

    public ThuThuDAO(Context context) {
        dbhelper = new Dbhelper(context);
    }

    //đăng nhập
    public boolean checkDangNhap(String matt, String pass) {
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THUTHU WHERE matt=? AND pass=?", new String[]{matt, pass});
        if (cursor.getCount() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean capnhatMK(String user, String oldPass, String newPass) {
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THUTHU WHERE matt=? AND pass=?", new String[]{user, oldPass});
        if (cursor.getCount() > 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("pass", newPass);
            long check = sqLiteDatabase.update("THUTHU", contentValues, "matt=?", new String[]{user});
            if (check == -1) {
                return false;
            }

            return true;
        }
        return false;
    }

}
