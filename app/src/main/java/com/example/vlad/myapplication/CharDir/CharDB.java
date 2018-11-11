package com.example.vlad.myapplication.CharDir;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

public class CharDB extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Char.db";
    public static final String TABLE_NAME = "char_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "REALM";
    public static final String COL_4 = "CLASA";
    public static final String COL_5 = "RACE";
    public static final String COL_6 = "FACTION";

    public CharDB(Context context)
    {
        super(context, DATABASE_NAME, null, 2);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT, REALM TEXT, CLASA TEXT, RACE INTEGER, FACTION INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String name,String realm, String clasa, String race, String faction) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,realm);
        contentValues.put(COL_4,clasa);
        contentValues.put(COL_5,race);
        contentValues.put(COL_6,faction);

        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getData(String name, String realm) {
        SQLiteDatabase db = this.getWritableDatabase();
        List<Character> characterList = new LinkedList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE name = ? AND realm = ?", new String[] {name,realm});
        Character character;
        if (cursor.moveToFirst()) {
            do {
                character = new Character();

                character.setName(cursor.getString(cursor.getColumnIndex(COL_2)));
                character.setRealm(cursor.getString(cursor.getColumnIndex(COL_3)));
                character.setClasa(cursor.getString(cursor.getColumnIndex(COL_4)));
                character.setRace(cursor.getInt(cursor.getColumnIndex(COL_5)));
                character.setFaction(cursor.getInt(cursor.getColumnIndex(COL_6)));

                characterList.add(character);
            } while (cursor.moveToNext());
        }


        return cursor;
    }
}
