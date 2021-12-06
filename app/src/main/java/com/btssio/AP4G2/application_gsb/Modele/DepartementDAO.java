package com.btssio.AP4G2.application_gsb.Modele;

/**
 * Created by dessaigne on 29/11/2021.
 */

import android.content.Context;
import android.database.Cursor;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class DepartementDAO {

    private static String base = "BDDepartement";
    private static int version = 1;
    private BDSQLiteOpenHelper accesBD;

    /**
     * @param ct
     */
    public DepartementDAO(Context ct) {


        accesBD = new BDSQLiteOpenHelper(ct, base, null, version);

    }

    /**
     * @return
     */
    private ArrayList<Departement> getDepartement() {
        Cursor curseur;
        String sql = "select * from departement;";
        curseur = accesBD.getReadableDatabase().rawQuery(sql, null);
        return cursorToDepartementArrayList(curseur);

    }

    /**
     * @return
     */
    private ArrayList<Departement> getLesDepartementsDesMedecins() {
        Cursor curseur;
        String sql = "select num_departement, nom from medecin join departement on departement.num_departement=medecin.numero;";
        curseur = accesBD.getReadableDatabase().rawQuery(sql, null);
        return cursorToDepartementArrayList(curseur);


    }

    /**
     * @param curseur
     * @return
     */
    private ArrayList<Departement> cursorToDepartementArrayList(Cursor curseur) {
        ArrayList<Departement> listeDepartement = new ArrayList<Departement>();
        long num_departement;
        long num_region;
        String nom;

        curseur.moveToFirst();
        while (!curseur.isAfterLast()) {
            num_departement = curseur.getLong(0);
            num_region = curseur.getLong(1);
            nom = curseur.getString(2);
            listeDepartement.add(new Departement(num_departement, num_region, nom));
            curseur.moveToNext();
        }

        return listeDepartement;
    }


}
