package com.example.app2;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {



    //proprieties
    private final static String COKTAIL_TABLE_NAME = "cocktail";
    private final static String creation="create table "+ COKTAIL_TABLE_NAME + " ("
            + "id INTEGER PRIMARY KEY,"
            + "name TEXT NOT NULL,"
            + "description TEXT NOT NULL,"
            + "logo INTEGER NOT NULL,"
            + "imgpath STRING NOT NULL,"
            + "stepsofpreparation STRING NOT NULL,"
            + "listofingredient STRING NOT NULL,"
            + "listofquantity STRING NOT NULL,"
            + "numberofingredient INTEGER NOT NULL,"
            + "isFavorite INTEGER NOT NULL,"
            + "isAlcohol INTEGER NOT NULL,"
            + "typeofalcohol STRING NOT NULL);";
    private final static String COCKTAIL_TABLE_DROP = "DROP TABLE IF EXISTS "+COKTAIL_TABLE_NAME+";";





    public MySQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // if we change DataBase
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(creation);

    }


    //if version changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(COCKTAIL_TABLE_DROP);
        onCreate(db);
    }
}
