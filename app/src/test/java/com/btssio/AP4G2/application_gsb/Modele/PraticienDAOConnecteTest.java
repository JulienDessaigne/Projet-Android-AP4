package com.btssio.AP4G2.application_gsb.Modele;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by veschembes on 17/01/2022.
 */
public class PraticienDAOConnecteTest {

    @Before
    public void setUp() throws Exception {
        System.out.println("————DÉBUT DU TEST————");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("———— FIN  DU TEST————");
    }

    @Test
    public void PraticienDAOConnecte() throws Exception {
        System.out.println(" — TEST Sur PraticienDAOConnecte()");
        PraticienDAOConnecte PraticienAccess = new PraticienDAOConnecte() {
            @Override
            public void onTacheTerminee(String resultat) {}

            @Override
            public void onTacheTerminee(ArrayList<Praticien> resultat) {}

            @Override
            public void onTacheTerminee(Praticien resultat) {}

            @Override
            public void onErreur(String message) {}
        };
        Assert.assertNotNull("Le constructeur créé est null", PraticienAccess);
    }

    @Test
    public void getPraticienDAOConnecte() throws Exception {
        System.out.println(" — TEST Sur getPraticienDAOConnecte()");
        PraticienDAOConnecte PraticienAccess = new PraticienDAOConnecte() {
            @Override
            public void onTacheTerminee(String resultat) {}

            @Override
            public void onTacheTerminee(ArrayList<Praticien> resultat) {
                Assert.assertNotNull("La réponse de la méthode est null", resultat);
            }

            @Override
            public void onTacheTerminee(Praticien resultat) {}

            @Override
            public void onErreur(String message) {}
        };
        PraticienAccess.getPraticienDAOConnecte();
    }

    @Test
    public void getPraticiensParDepartementDAOConnecte() throws Exception {
        System.out.println(" — TEST Sur getPraticienDAOConnecte()");
        PraticienDAOConnecte PraticienAccess = new PraticienDAOConnecte() {
            @Override
            public void onTacheTerminee(String resultat) {}

            @Override
            public void onTacheTerminee(ArrayList<Praticien> resultat) {
                Assert.assertNotNull("La réponse de la méthode est null", resultat);
            }

            @Override
            public void onTacheTerminee(Praticien resultat) {}

            @Override
            public void onErreur(String message) {}
        };
        PraticienAccess.getPraticiensParDepartementDAOConnecte("GARD");
    }

    @Test
    public void getPraticiensParNomDAOConnecte() throws Exception {
        System.out.println(" — TEST Sur getPraticienDAOConnecte()");
        PraticienDAOConnecte PraticienAccess = new PraticienDAOConnecte() {
            @Override
            public void onTacheTerminee(String resultat) {}

            @Override
            public void onTacheTerminee(ArrayList<Praticien> resultat) {
                Assert.assertNotNull("La réponse de la méthode est null", resultat);
            }

            @Override
            public void onTacheTerminee(Praticien resultat) {}

            @Override
            public void onErreur(String message) {}
        };
        PraticienAccess.getPraticiensParNomDAOConnecte("Chubilleau");
    }

}