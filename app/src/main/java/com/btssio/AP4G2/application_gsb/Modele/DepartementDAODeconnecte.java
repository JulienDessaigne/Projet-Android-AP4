package com.btssio.AP4G2.application_gsb.Modele;

/**
 * Created by dessaigne on 29/11/2021.
 */

import android.content.Context;
import android.database.Cursor;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class DepartementDAODeconnecte {

    private static String base = "BDDepartement";
    private static int version = 1;
    private BDSQLiteOpenHelper accesBD;

    /**
     * @param ct
     */
    public DepartementDAODeconnecte(Context ct) {


        accesBD = new BDSQLiteOpenHelper(ct, base, null, version);

    }

    /**
     * @return
     */


    /**
     * @return ArrayList<Departement>
     */
    private ArrayList<Departement> getLesDepartementsDesPraticiens() {
        Cursor curseur;
        String sql = "select NUM_DEPARTEMENT, PRA_NOM from praticien join departement on departement.NUM_DEPARTEMENT=praticien.PRA_NUM;";
        curseur = accesBD.getReadableDatabase().rawQuery(sql, null);
        return cursorToDepartementArrayList(curseur);


    }



    /**
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

            NOM = curseur.getString(2);
            listeDepartement.add(new Departement(NUM_DEPARTEMENT, NOM));
            curseur.moveToNext();
        }

        return listeDepartement;
    }


}
