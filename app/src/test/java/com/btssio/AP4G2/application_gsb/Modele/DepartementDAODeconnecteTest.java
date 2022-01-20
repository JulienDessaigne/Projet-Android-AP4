package com.btssio.AP4G2.application_gsb.Modele;

import android.app.Instrumentation;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.btssio.AP4G2.application_gsb.Modele.DepartementDAODeconnecte;
import com.btssio.AP4G2.application_gsb.Modele.PraticienDAODeconnecte;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;


/**
 * Created by dessaigne on 17/01/2022.
 */
public class DepartementDAODeconnecteTest extends AppCompatActivity {
    private BDSQLiteOpenHelper db = null;


    @Before
    public void setUp() throws Exception {

        System.out.println("———— DEBUT  DU TEST————");
        

        db = new BDSQLiteOpenHelper(this, "BDGSB", null, 1);


    }


    @After
    public void tearDown() throws Exception {
        System.out.println("———— FIN  DU TEST————");
        db.close();
    }

    @Test
    public void getLesDepartementsDesPraticiens() throws Exception {

        Cursor curseur;
        String sql = "select distinct D.NUM_DEPARTEMENT, D.NOM from departement as D inner join praticien as P on P.NUM_DEPARTEMENT = D.NUM_DEPARTEMENT order by D.NUM_DEPARTEMENT;";

        curseur = db.getReadableDatabase().rawQuery(sql, null);
        ArrayList<Departement> listeDepartement = new ArrayList<Departement>();
        String NUM_DEPARTEMENT;
        String NOM;

        curseur.moveToFirst();
        while (!curseur.isAfterLast()) {
            NUM_DEPARTEMENT = curseur.getString(0);
            NOM = curseur.getString(1);
            listeDepartement.add(new Departement(NUM_DEPARTEMENT, NOM));
            curseur.moveToNext();
        }

        Assert.assertNotNull("La requete getLesDepartementsDesPraticiens() ne fonctionne pas correctement", listeDepartement);
        Assert.assertSame("La La requete getLesDepartementsDesPraticiens() ne revoie pas de département", 0, listeDepartement.size());
    }


    public void getDepartements() throws Exception {

        /*ArrayList<Departement> lesDepartements = departementDecoAccess.getDepartements();
        Assert.assertNotNull("La requete getDepartements() ne fonctionne pas correctement", lesDepartements);
        Assert.assertSame("La La requete getDepartements() ne revoie pas de département", 0, lesDepartements.size());*/
    }


    public void addDepartement() throws Exception {
    }

}