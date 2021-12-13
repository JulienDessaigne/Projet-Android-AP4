package com.btssio.AP4G2.application_gsb.Modele;

/**
 * Created by dessaigne on 29/11/2021.
 */

public class Departement {

    private String NUM_DEPARTEMENT;
    private String NOM;

    /**
     * @param NUM_DEPARTEMENT
     * @param NOM
     */
    public Departement(String NUM_DEPARTEMENT, String NOM) {
        this.NUM_DEPARTEMENT = NUM_DEPARTEMENT;
        this.NOM = NOM;
    }

    /**
     * @return String
     */
    public String getNUM_DEPARTEMENT() {
        return NUM_DEPARTEMENT;
    }

    /**
     * @param NUM_DEPARTEMENT
     */
    public void setNUM_DEPARTEMENT(String NUM_DEPARTEMENT) {
        this.NUM_DEPARTEMENT = NUM_DEPARTEMENT;
    }

    /**
     * @return String
     */
    public String getNOM() {
        return NOM;
    }

    /**
     * @param NOM
     */
    public void setNOM(String NOM) {
        this.NOM = NOM;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "Departement{" +
                "NUM_DEPARTEMENT=" + NUM_DEPARTEMENT +
                ", NOM='" + NOM + '\'' +
                '}';
    }
}
