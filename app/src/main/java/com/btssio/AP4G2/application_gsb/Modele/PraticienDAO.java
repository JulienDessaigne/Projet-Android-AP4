package com.btssio.AP4G2.application_gsb.Modele;

/**
 * Created by dessaigne on 29/11/2021.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


public class PraticienDAO {

    private static String base = "BDPraticien";
    private static int version = 1;
    private BDSQLiteOpenHelper accesBD;

    public PraticienDAO(Context ct) {


        accesBD = new BDSQLiteOpenHelper(/*ct, base, null, version*/);

    }

    private ArrayList<Praticien> cursorToPraticienArrayList(Cursor curseur) {
        ArrayList<Praticien> listePraticien = new ArrayList<Praticien>();
        long numero;
        String nom;
        String prenom;
        String adresse;
        long CP;
        String ville;
        long coef_nototoriete;
        String type_code;
        long tel;

        curseur.moveToFirst();
        while (!curseur.isAfterLast()) {
            numero = curseur.getLong(0);
            nom = curseur.getString(1);
            prenom = curseur.getString(2);
            adresse = curseur.getString(3);
            CP = curseur.getLong(4);
            ville = curseur.getString(5);
            listeDepartement.add(new Praticien(numero,nom,prenom,adresse,CP,ville,coef_nototoriete,type_code,tel));
            curseur.moveToNext();
        }

        return listeDepartement;
    }


}
