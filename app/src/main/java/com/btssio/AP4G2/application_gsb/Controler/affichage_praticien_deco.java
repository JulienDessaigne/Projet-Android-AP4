package com.btssio.AP4G2.application_gsb.Controler;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;

import com.btssio.AP4G2.application_gsb.Modele.Praticien;
import com.btssio.AP4G2.application_gsb.Modele.PraticienDAODeconnecte;
import com.btssio.AP4G2.application_gsb.R;

import java.util.ArrayList;

public class affichage_praticien_deco extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_praticien_deco);
        initialisation();
    }

    private void initialisation() {

        ArrayList<Praticien> listePraticienRecherche;
        PraticienDAODeconnecte praticienAcces = new PraticienDAODeconnecte(this);

        Praticien praticien = new Praticien(1,"Notini","Alain", "114 r Authie", "15200","LA ROCHE SUR YON", 1,"MH", "0584656261", "1" );
        praticienAcces.addPraticien(praticien);

        listePraticienRecherche = praticienAcces.getPraticien();
        for (Praticien unViticulteur : listePraticienRecherche){
            Log.d("Init L32", unViticulteur.toString());
        }

    }

}
