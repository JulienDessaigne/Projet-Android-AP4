package com.btssio.AP4G2.application_gsb.Controler;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;

import com.btssio.AP4G2.application_gsb.Modele.Medecin;
import com.btssio.AP4G2.application_gsb.Modele.MedecinDAO;
import com.btssio.AP4G2.application_gsb.R;

import java.util.ArrayList;

public class affichage_medecin_deco extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_medecin_deco);
        initialisation();
    }

    private void initialisation() {

        ArrayList<Medecin> listeMedecinRecherche;
        MedecinDAO medecinAccess = new MedecinDAO(this);

        Medecin medecin = new Medecin(1,"Notini","Alain", "114 r Authie", 1,"LA ROCHE SUR YON", 1,"MH", 1 );
        medecinAccess.addMedecin(medecin);

        listeMedecinRecherche = medecinAccess.getMedecins();
        for (Medecin unViticulteur : listeMedecinRecherche){
            Log.d("Init L32", unViticulteur.toString());
        }

    }

}
