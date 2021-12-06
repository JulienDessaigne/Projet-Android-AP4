package com.btssio.AP4G2.application_gsb.Modele;

/**
 * Created by dessaigne on 29/11/2021.
 */

public class Praticien {
    private long PRA_NUM ;
    private String PRA_NOM ;
    private String PRA_PRENOM ;
    private String PRA_ADRESSE  ;
    private String PRA_CP ;
    private String PRA_VILLE ;
    private float PRA_COEFNOTORIETE ;
    private String TYP_CODE ;
    private String PRA_TELEPHONE ;
    private String NUM_DEPARTEMENT;

    public Praticien(long PRA_NUM, String PRA_NOM, String PRA_PRENOM, String PRA_ADRESSE, String PRA_CP, String PRA_VILLE, float PRA_COEFNOTORIETE, String TYP_CODE, String PRA_TELEPHONE, String NUM_DEPARTEMENT) {
        this.PRA_NUM = PRA_NUM;
        this.PRA_NOM = PRA_NOM;
        this.PRA_PRENOM = PRA_PRENOM;
        this.PRA_ADRESSE = PRA_ADRESSE;
        this.PRA_CP = PRA_CP;
        this.PRA_VILLE = PRA_VILLE;
        this.PRA_COEFNOTORIETE = PRA_COEFNOTORIETE;
        this.TYP_CODE = TYP_CODE;
        this.PRA_TELEPHONE = PRA_TELEPHONE;
        this.NUM_DEPARTEMENT = NUM_DEPARTEMENT;
    }

    public long getPRA_NUM() {
        return PRA_NUM;
    }

    public void setPRA_NUM(long PRA_NUM) {
        this.PRA_NUM = PRA_NUM;
    }

    public String getPRA_NOM() {
        return PRA_NOM;
    }

    public void setPRA_NOM(String PRA_NOM) {
        this.PRA_NOM = PRA_NOM;
    }

    public String getPRA_PRENOM() {
        return PRA_PRENOM;
    }

    public void setPRA_PRENOM(String PRA_PRENOM) {
        this.PRA_PRENOM = PRA_PRENOM;
    }

    public String getPRA_ADRESSE() {
        return PRA_ADRESSE;
    }

    public void setPRA_ADRESSE(String PRA_ADRESSE) {
        this.PRA_ADRESSE = PRA_ADRESSE;
    }

    public String getPRA_CP() {
        return PRA_CP;
    }

    public void setPRA_CP(String PRA_CP) {
        this.PRA_CP = PRA_CP;
    }

    public String getPRA_VILLE() {
        return PRA_VILLE;
    }

    public void setPRA_VILLE(String PRA_VILLE) {
        this.PRA_VILLE = PRA_VILLE;
    }

    public float getPRA_COEFNOTORIETE() {
        return PRA_COEFNOTORIETE;
    }

    public void setPRA_COEFNOTORIETE(float PRA_COEFNOTORIETE) {
        this.PRA_COEFNOTORIETE = PRA_COEFNOTORIETE;
    }

    public String getTYP_CODE() {
        return TYP_CODE;
    }

    public void setTYP_CODE(String TYP_CODE) {
        this.TYP_CODE = TYP_CODE;
    }

    public String getPRA_TELEPHONE() {
        return PRA_TELEPHONE;
    }

    public void setPRA_TELEPHONE(String PRA_TELEPHONE) {
        this.PRA_TELEPHONE = PRA_TELEPHONE;
    }

    public String getNUM_DEPARTEMENT() {
        return NUM_DEPARTEMENT;
    }

    public void setNUM_DEPARTEMENT(String NUM_DEPARTEMENT) {
        this.NUM_DEPARTEMENT = NUM_DEPARTEMENT;
    }

    /*public Departement getDepartementByNum(String NUM_DEPARTEMENT){



    }*/

    @Override
    public String toString() {
        return "Praticien{" +
                "PRA_NUM=" + PRA_NUM +
                ", PRA_NOM='" + PRA_NOM + '\'' +
                ", PRA_PRENOM='" + PRA_PRENOM + '\'' +
                ", PRA_ADRESSE='" + PRA_ADRESSE + '\'' +
                ", PRA_CP='" + PRA_CP + '\'' +
                ", PRA_VILLE='" + PRA_VILLE + '\'' +
                ", PRA_COEFNOTORIETE=" + PRA_COEFNOTORIETE +
                ", TYP_CODE='" + TYP_CODE + '\'' +
                ", PRA_TELEPHONE='" + PRA_TELEPHONE + '\'' +
                ", NUM_DEPARTEMENT='" + NUM_DEPARTEMENT + '\'' +
                '}';
    }
}
