package com.example.vlad.myapplication.RealmDir;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

public class RealmDB extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Realm.db";
    public static final String TABLE_NAME = "realm_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "TYPE";

    public RealmDB(Context context)
    {
        super(context, DATABASE_NAME, null, 2);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,TYPE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String name,String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,type);

        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public List<Realm> getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        List<Realm> realmList = new LinkedList<>();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME ,null);
        Realm realm;
        if (cursor.moveToFirst()) {
            do {
                realm = new Realm();

                realm.setName(cursor.getString(cursor.getColumnIndex(COL_2)));
                realm.setType(cursor.getString(cursor.getColumnIndex(COL_3)));

                realmList.add(realm);
            } while (cursor.moveToNext());
        }


        return realmList;
    }
}
