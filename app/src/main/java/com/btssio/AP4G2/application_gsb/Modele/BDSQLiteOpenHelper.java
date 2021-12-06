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
    private String requete="Create table praticien (" +
            "    pra_num int primary key ," +
            "    pra_nom text not null," +
            "    pra_prenom text not null," +
            "    pra_adresse text," +
            "    pra_codesPostal smallint," +
            "    pra_ville text," +
            "    pra_coefnotoriete float,"+
            "    type_code char(5)"+
            "    pra_numTel int," +
            "    idDepartement int foreign key references departement (idDepartement));" +
            "   " +
            "Create table departement(" +
            "    num_departement int primary key ," +
            "    num_region smallint   " +
            "    libelleDepartement text" +
            ");";

    /**
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public BDSQLiteOpenHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }

    /**
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(requete);

        db.execSQL("insert into praticien (pra_num,pra_nom,pra_prenom,pra_adresse,pra_codesPostal,pra_ville,pra_coefnotoriete,type_code,pra_numTel,idDepartement) values();");
        db.execSQL("insert into departement (num_departement,num_region,libelleDepartement) values()");

        ContentValues value = new ContentValues();
        value.put("nomV", "Esquerra");
        value.put("niveauV",50);
        db.insert("viticulteur", null, value);
    }

    /**
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
    }
}
