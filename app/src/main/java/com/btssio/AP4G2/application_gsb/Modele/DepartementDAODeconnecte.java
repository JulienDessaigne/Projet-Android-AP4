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
    private ArrayList<Departement> lesDepartements = new ArrayList<>();


    public ArrayList<Departement> getLesDepartements() {
        return lesDepartements;
    }

    public void setLesDepartements(ArrayList<Departement> lesDepartements) {
        this.lesDepartements = lesDepartements;
    }

    /**
     * @param ct
     */
    public DepartementDAODeconnecte(Context ct) {


        accesBD = new BDSQLiteOpenHelper(ct, base, null, version);

    }

    /**
     * @return
     */
    private ArrayList<Departement> getDepartement() {
        Cursor curseur;
        String sql = "select * from departement;";
        curseur = accesBD.getReadableDatabase().rawQuery(sql, null);
        this.lesDepartements=cursorToDepartementArrayList(curseur);
        return this.lesDepartements;

    }

    /**
     * @return
     */
    private ArrayList<Departement> getLesDepartementsDesPraticiens() {
        Cursor curseur;
        String sql = "select num_departement, nom from praticien join departement on departement.num_departement=praticien.numero;";
        curseur = accesBD.getReadableDatabase().rawQuery(sql, null);
        return cursorToDepartementArrayList(curseur);


    }

    public Departement getDepartementByNum(String NUM_DEPARTEMENT){

        Departement leDepartement = null;
        int i = 0 ;
        boolean trouve = false;
        while (i<lesDepartements.size() && ! trouve){
            if (lesDepartements.get(i).getNUM_DEPARTEMENT()==NUM_DEPARTEMENT){
                trouve=true;
            }
            else {
                i++;
            }
        }
        if (trouve){
            leDepartement=lesDepartements.get(i);
        }

        return leDepartement;

    }

    /**
     * @param curseur
     * @return
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
