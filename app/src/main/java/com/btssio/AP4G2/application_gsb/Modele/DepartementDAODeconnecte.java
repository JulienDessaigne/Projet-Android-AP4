package com.btssio.AP4G2.application_gsb.Modele;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by dessaigne on 29/11/2021.
 */
public class DepartementDAODeconnecte {

    private static String base = "BDGSB";
    private static int version = 1;
    private BDSQLiteOpenHelper accesBD;

    /**
     * Constructeur initialisant la BdD locale
     * @param ct
     */
    public DepartementDAODeconnecte(Context ct) {
        accesBD = new BDSQLiteOpenHelper(ct, base, null, version);

    }

    /**
     * Récupère les départements des praticiens
     * @return ArrayList<Departement>
     */
    public ArrayList<Departement> getLesDepartementsDesPraticiens() {
        Cursor curseur;
        String sql = "select distinct D.NUM_DEPARTEMENT, D.NOM from departement as D inner join praticien as P on P.NUM_DEPARTEMENT = D.NUM_DEPARTEMENT order by D.NUM_DEPARTEMENT;";
        curseur = accesBD.getReadableDatabase().rawQuery(sql, null);
        return cursorToDepartementArrayList(curseur);
    }

    /**
     * Récupère tous les départements
     * @return ArrayList<Departement>
     */
    public ArrayList<Departement> getDepartements(){

        Cursor curseur;
        String sql = "select * from departement;";
        curseur = accesBD.getReadableDatabase().rawQuery(sql, null);
        return cursorToDepartementArrayList(curseur);

    }


    /**
     * Converti le curseur passé en paramètre en ArrayList<Departement>
     * @param curseur
     * @return ArrayList<Departement>
     */
    private ArrayList<Departement> cursorToDepartementArrayList(Cursor curseur) {
        ArrayList<Departement> listeDepartement = new ArrayList<Departement>();
        String NUM_DEPARTEMENT ;
        String NOM;

        curseur.moveToFirst();
        while (!curseur.isAfterLast()) {
            NUM_DEPARTEMENT = curseur.getString(0);
            NOM = curseur.getString(1);
            listeDepartement.add(new Departement(NUM_DEPARTEMENT, NOM));
            curseur.moveToNext();
        }

        return listeDepartement;
    }

    /**
     * Ajout d'un departement dans la bdd local
     * @param unDepartement
     * @return long
     */
    public long addDepartement(Departement unDepartement){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put("NUM_DEPARTEMENT",unDepartement.getNUM_DEPARTEMENT());
        value.put("NOM",unDepartement.getNOM());


        ret = bd.insert("departement", null, value);

        return ret;


    }

}
