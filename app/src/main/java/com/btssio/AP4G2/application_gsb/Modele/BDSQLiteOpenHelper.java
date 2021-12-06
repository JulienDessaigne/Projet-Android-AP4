package com.btssio.AP4G2.application_gsb.Modele;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by warmee on 29/11/2021.
 */


public class BDSQLiteOpenHelper extends SQLiteOpenHelper {
    private String requete="create table praticien ("
            + "numero INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "nom TEXT NOT NULL,"
            + "prenom INTEGER NOT NULL,"
            + "adresse TEXT NOT NULL," +
            "CP INTEGER NOT NULL," +
            "ville TEXT NOT NULL," +
            "coef_notoriete INTEGER NOT NULL," +
            "type_code TEXT NOT NULL," +
            "tel INTEGER);";


    public BDSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(requete);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
