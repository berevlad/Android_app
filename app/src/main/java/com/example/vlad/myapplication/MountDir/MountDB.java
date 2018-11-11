package com.example.vlad.myapplication.MountDir;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

public class MountDB extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Mount.db";
    public static final String TABLE_NAME = "mount_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";

    public MountDB(Context context)
    {
        super(context, DATABASE_NAME, null, 4);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);

        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public List<Mount> getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        List<Mount> mountList = new LinkedList<>();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME ,null);
        Mount mount;
        if (cursor.moveToFirst()) {
            do {
                mount = new Mount();

                mount.setName(cursor.getString(cursor.getColumnIndex(COL_2)));

                mountList.add(mount);
            } while (cursor.moveToNext());
        }


        return mountList;
    }
}

