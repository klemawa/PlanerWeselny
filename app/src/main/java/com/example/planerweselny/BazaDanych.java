package com.example.planerweselny;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BazaDanych  extends SQLiteOpenHelper {
    public BazaDanych(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String qry1 = "create table users(username text, email text, password text )";
        db.execSQL(qry1);
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void register(String username, String email, String password){
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users", null, cv);
        db.close();
    }

    public int login(String username , String password) {
        int wynik = 0;
        String[] str = new String[2];
        str[0] = username;
        str[1] = password;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=? and password=?", str);
        if (cursor.moveToFirst()) {
            wynik = 1;
        }
        cursor.close();
        db.close();
        return wynik;
    }
}
