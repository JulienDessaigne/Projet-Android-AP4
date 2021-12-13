package com.btssio.AP4G2.application_gsb.Modele;

/**
 * Created by warmee on 29/11/2021.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class BDSQLiteOpenHelper extends SQLiteOpenHelper{
    private String requete=
            "   Create table departement(" +
            "    NUM_DEPARTEMENT text primary key ," +
            "    NOM text);" +

            "    Create table praticien (" +
            "    PRA_NUM  int primary key ," +
            "    PRA_NOM text not null," +
            "    PRA_PRENOM text not null," +
            "    PRA_ADRESSE text," +
            "    PRA_CODESPOSTAL text," +
            "    PRA_VILLE text," +
            "    PRA_COEFNOTORIETE real," +
            "    PRA_NUMTEL string," +
            "    NUM_DEPARTEMENT text foreign key references departement (NUM_DEPARTEMENT));";

    /**
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public BDSQLiteOpenHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    /**
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(requete);
    }


    /**
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
