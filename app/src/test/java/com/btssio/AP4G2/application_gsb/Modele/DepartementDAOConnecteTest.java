package com.btssio.AP4G2.application_gsb.Modele;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLOutput;
import java.util.ArrayList;

/**
 * Created by veschembes on 17/01/2022.
 */
public class DepartementDAOConnecteTest {

    @Before
    public void setUp() throws Exception {
        System.out.println("———— FIN  DU TEST————");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("————DÉBUT DU TEST————");
    }

    @Test
    public void DepartementDAOConnecte() throws Exception {
        System.out.println(" — TEST Sur DepartementDAOConnecte()");
        DepartementDAOConnecte DepartementAccess = new DepartementDAOConnecte() {
            @Override
            public void onTacheTerminee(String resultat) {}

            @Override
            public void onTacheTerminee(ArrayList<Departement> resultat) {}

            @Override
            public void onTacheTerminee(Departement resultat) {}

            @Override
            public void onErreur(String message) {}
        };
        Assert.assertNotNull("Le constructeur créé est null", DepartementAccess);
    }

    @Test
    public void getDepartementsDAOConnecte() throws Exception {
        System.out.println(" — TEST Sur getDepartementsDAOConnecte()");
        DepartementDAOConnecte DepartementAccess = new DepartementDAOConnecte() {
            @Override
            public void onTacheTerminee(String resultat) {}

            @Override
            public void onTacheTerminee(ArrayList<Departement> resultat) {
                Assert.assertNotNull("La réponse de la méthode est null", resultat);
            }

            @Override
            public void onTacheTerminee(Departement resultat) {}

            @Override
            public void onErreur(String message) {}
        };
        DepartementAccess.getDepartementsDAOConnecte();
    }

    @Test
    public void getDepartementsPraticienDAOConnecte() throws Exception {
        System.out.println(" — TEST Sur getDepartementsPraticienDAOConnecte()");
        DepartementDAOConnecte DepartementAccess = new DepartementDAOConnecte() {
            @Override
            public void onTacheTerminee(String resultat) {}

            @Override
            public void onTacheTerminee(ArrayList<Departement> resultat) {
                Assert.assertNotNull("La réponse de la méthode est null", resultat);
            }

            @Override
            public void onTacheTerminee(Departement resultat) {}

            @Override
            public void onErreur(String message) {}
        };
        DepartementAccess.getDepartementsPraticienDAOConnecte();
    }
}