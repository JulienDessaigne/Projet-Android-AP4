package com.btssio.AP4G2.application_gsb.Modele;

/**
 * Created by dessaigne on 29/11/2021.
 */

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;


public class MedecinDAO {

    private static String base = "BDMedecin";
    private static int version = 1;
    private BDSQLiteOpenHelper accesBD;

    public MedecinDAO(Context ct) {


        accesBD = new BDSQLiteOpenHelper(ct, base, null, version);

    }

    /**
     * @return
     */
    private ArrayList<Medecin> getMedecins(){
        Cursor curseur;
        String sql = "select * from medecin;";
        curseur = accesBD.getReadableDatabase().rawQuery(sql,null);
        return cursorToMedecinArrayList(curseur);

    }

    /**
     * @param numero_departement
     * @return
     */
    private ArrayList<Medecin> getMedecinsByDepartement(int numero_departement){
        Cursor curseur;
        String sql = "select * from medecin join departement on departement.num_departement=medecin.numero where num_departement="+numero_departement+";";
        curseur = accesBD.getReadableDatabase().rawQuery(sql,null);
        return cursorToMedecinArrayList(curseur);

    }

    /**
     * @param departement
     * @return
     */
    private ArrayList<Medecin> getMedecinsByDepartement(Departement departement){
        Cursor curseur;
        long numero_departement = departement.getNum_departement();
        String sql = "select * from medecin join departement on departement.num_departement=medecin.numero where num_departement="+numero_departement+";";
        curseur = accesBD.getReadableDatabase().rawQuery(sql,null);
        return cursorToMedecinArrayList(curseur);

    }

    /**
     * @param nom
     * @return
     */
    private ArrayList<Medecin> getMedecinsByNom(String nom){
        Cursor curseur;
        String sql = "select * from medecin where nom="+nom+";";
        curseur = accesBD.getReadableDatabase().rawQuery(sql,null);
        return cursorToMedecinArrayList(curseur);

    }



    /**
     * @param curseur
     * @return
     */
    private ArrayList<Medecin> cursorToMedecinArrayList(Cursor curseur) {
        ArrayList<Medecin> listeMedecin = new ArrayList<Medecin>();
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
            coef_nototoriete=curseur.getLong(6);
            type_code=curseur.getString(7);
            tel = curseur.getLong(8);
            listeMedecin.add(new Medecin(numero,nom,prenom,adresse,CP,ville,coef_nototoriete,type_code,tel));
            curseur.moveToNext();
        }

        return listeMedecin;
    }


}
