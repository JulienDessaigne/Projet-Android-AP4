package com.btssio.AP4G2.application_gsb.Modele;

/**
 * Created by dessaigne on 29/11/2021.
 */

public class Departement {

    private long num_departement;
    private long num_region;
    private String nom;

    /**
     * @param num_departement
     * @param num_region
     * @param nom
     */
    public Departement(long num_departement, long num_region, String nom) {
        this.num_departement = num_departement;
        this.num_region = num_region;
        this.nom = nom;
    }

    public long getNum_departement() {
        return num_departement;
    }

    /**
     * @param num_departement
     */
    public void setNum_departement(long num_departement) {
        this.num_departement = num_departement;
    }

    public long getNum_region() {
        return num_region;
    }

    /**
     * @param num_region
     */
    public void setNum_region(long num_region) {
        this.num_region = num_region;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Departement{" +
                "num_departement=" + num_departement +
                ", num_region=" + num_region +
                ", nom='" + nom + '\'' +
                '}';
    }
}
