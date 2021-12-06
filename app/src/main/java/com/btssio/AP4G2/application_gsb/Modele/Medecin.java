package com.btssio.AP4G2.application_gsb.Modele;

/**
 * Created by dessaigne on 29/11/2021.
 */

public class Medecin {
    private long numero;
    private String nom;
    private String prenom;
    private String adresse;
    private long CP;
    private String ville;
    private long coef_nototoriete;
    private String type_code;
    private long tel;

    public Medecin(long numero, String nom, String prenom, String adresse, long CP, String ville, long coef_nototoriete, String type_code, long tel) {
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.CP = CP;
        this.ville = ville;
        this.coef_nototoriete = coef_nototoriete;
        this.type_code = type_code;
        this.tel = tel;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public long getCP() {
        return CP;
    }

    public void setCP(long CP) {
        this.CP = CP;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public long getCoef_nototoriete() {
        return coef_nototoriete;
    }

    public void setCoef_nototoriete(long coef_nototoriete) {
        this.coef_nototoriete = coef_nototoriete;
    }

    public String getType_code() {
        return type_code;
    }

    public void setType_code(String type_code) {
        this.type_code = type_code;
    }

    public long getTel() {
        return tel;
    }

    public void setTel(long tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Medecin{" +
                "numero=" + numero +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", CP=" + CP +
                ", ville='" + ville + '\'' +
                ", coef_nototoriete=" + coef_nototoriete +
                ", type_code='" + type_code + '\'' +
                ", tel=" + tel +
                '}';
    }
}
