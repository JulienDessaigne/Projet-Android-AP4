package com.btssio.AP4G2.application_gsb.Modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by dessaigne on 29/11/2021.
 */
public class PraticienDAODeconnecte {

    private static String base = "BDGSB";
    private static int version = 1;
    private BDSQLiteOpenHelper accesBD;

    /**
     * Constructeur initialisant la BdD locale
     * @param ct
     */
    public PraticienDAODeconnecte(Context ct) {
        accesBD = new BDSQLiteOpenHelper(ct, base, null, version);
    }

    /**
     * Récupère tous les praticiens
     * @return ArrayList<Praticien>
     */
    public ArrayList<Praticien> getPraticiens(){
        Cursor curseur;
        String sql = "select * from praticien;";
        curseur = accesBD.getReadableDatabase().rawQuery(sql,null);
        return cursorToPraticienArrayList(curseur);
    }

    /**
     * Récèpre les praticiens d'un département avec le nom du département passé en paramètre
     * @param NUM_DEPARTEMENT
     * @return ArrayList<Praticien>
     */
    public ArrayList<Praticien> getPraticiensByDepartement(String NUM_DEPARTEMENT){
        Cursor curseur;
        String sql = "select * from praticien where NUM_DEPARTEMENT="+NUM_DEPARTEMENT+";";
        curseur = accesBD.getReadableDatabase().rawQuery(sql,null);
        return cursorToPraticienArrayList(curseur);
    }


    /**
     * Récèpre les praticiens d'un département avec objet Departement en paramètre
     * @param departement
     * @return ArrayList<Praticien>
     */
    public ArrayList<Praticien> getPraticiensByDepartement(Departement departement){
        Cursor curseur;
        String numero_departement = departement.getNUM_DEPARTEMENT();
        String sql = "select * from praticien where NUM_DEPARTEMENT='"+numero_departement+"';";
        curseur = accesBD.getReadableDatabase().rawQuery(sql,null);
        return cursorToPraticienArrayList(curseur);

    }

    /**
     * Récupère les praticiens ayant le même nom passé en paramètre
     * @param nom
     * @return ArrayList<Praticien>
     */
    public ArrayList<Praticien> getPraticiensByNom(String nom){
        Cursor curseur;
        String sql = "select * from praticien where PRA_NOM='"+nom+"';";
        curseur = accesBD.getReadableDatabase().rawQuery(sql,null);
        return cursorToPraticienArrayList(curseur);
    }

    /**
     * Fonction d'ajout d'un praticien dans la BdD
     * @param unPraticien
     * @return
     */
    public long addPraticien(Praticien unPraticien){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put("PRA_NUM", unPraticien.getPRA_NUM());
        value.put("PRA_NOM", unPraticien.getPRA_NOM());
        value.put("PRA_PRENOM", unPraticien.getPRA_PRENOM());
        value.put("PRA_ADRESSE", unPraticien.getPRA_ADRESSE());
        value.put("PRA_CP", unPraticien.getPRA_CP());
        value.put("PRA_VILLE", unPraticien.getPRA_VILLE());
        value.put("PRA_COEFNOTORIETE", unPraticien.getPRA_COEFNOTORIETE());
        value.put("TYP_CODE", unPraticien.getTYP_CODE());
        value.put("PRA_TELEPHONE", unPraticien.getPRA_TELEPHONE());
        value.put("NUM_DEPARTEMENT", unPraticien.getNUM_DEPARTEMENT());

        ret = bd.insert("praticien", null, value);

        return ret;
    }


    /**
     *  Converti le curseur passé en paramètre en ArrayList<Praticien>
     * @param curseur
     * @return ArrayList<Praticien>
     */
    public ArrayList<Praticien> cursorToPraticienArrayList(Cursor curseur) {
        ArrayList<Praticien> listePraticien = new ArrayList<Praticien>();
        long PRA_NUM;
        String PRA_NOM;
        String PRA_PRENOM;
        String PRA_ADRESSE;
        String PRA_CP;
        String PRA_VILLE;
        float PRA_COEFNOTORIETE;
        String TYP_CODE;
        String PRA_TELEPHONE;
        String NUM_DEPARTEMENT;


        curseur.moveToFirst();
        while (!curseur.isAfterLast()) {
            PRA_NUM = curseur.getLong(0);
            PRA_NOM = curseur.getString(1);
            PRA_PRENOM = curseur.getString(2);
            PRA_ADRESSE = curseur.getString(3);
            PRA_CP = curseur.getString(4);
            PRA_VILLE = curseur.getString(5);
            PRA_COEFNOTORIETE=curseur.getFloat(6);
            TYP_CODE=curseur.getString(7);
            PRA_TELEPHONE = curseur.getString(8);
            NUM_DEPARTEMENT = curseur.getString(9);
            listePraticien.add(new Praticien(PRA_NUM,PRA_NOM,PRA_PRENOM,PRA_ADRESSE,PRA_CP,PRA_VILLE,PRA_COEFNOTORIETE,TYP_CODE,PRA_TELEPHONE,NUM_DEPARTEMENT));
            curseur.moveToNext();
        }

        return listePraticien;
    }

    /**
     * Ajout du numéro du département passé en paramètre au praticien passé en paramètre
     * @param Prenom
     * @param Nom
     * @param numDepartement
     * @return
     */
    public long addNumDepartementPraticien(String Prenom,String Nom,String numDepartement){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put("NUM_DEPARTEMENT", numDepartement);

        ret = bd.update("praticien", value,"PRA_PRENOM=? and PRA_NOM=?", new String[]{Prenom,Nom});
        return ret;
    }



}
