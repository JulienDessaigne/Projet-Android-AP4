package com.btssio.AP4G2.application_gsb.Controler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.btssio.AP4G2.application_gsb.Modele.Departement;
import com.btssio.AP4G2.application_gsb.Modele.DepartementDAOConnecte;
import com.btssio.AP4G2.application_gsb.Modele.DepartementDAODeconnecte;
import com.btssio.AP4G2.application_gsb.Modele.Praticien;
import com.btssio.AP4G2.application_gsb.Modele.PraticienDAOConnecte;
import com.btssio.AP4G2.application_gsb.Modele.PraticienDAODeconnecte;
import com.btssio.AP4G2.application_gsb.R;

import java.util.ArrayList;
import java.util.HashMap;

public class AffichageInfosPraticien extends AppCompatActivity {

    private TextView textViewNumPraticien;
    private TextView textViewNomPraticien;
    private TextView textViewPrenomPraticien;
    private TextView textViewTelephonePraticien;
    private TextView textViewAdressePraticien;
    private TextView textViewCodePostalPraticien;
    private TextView textViewVillePraticien;
    private TextView textViewCoefNotorietePraticien;
    private TextView textViewNumDepartPraticien;
    private TextView textViewNomDepartPraticien;
    private TextView textViewNumRegionPraticien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_infos_praticien);

        Initialiser();
        getLePraticien();
    }

    public void Initialiser() {
        // Valorisation des variables représentant les éléments XML
        textViewNumPraticien = findViewById(R.id.textViewNumPraticienValeur);
        textViewNomPraticien = findViewById(R.id.textViewNomPraticienValeur);
        textViewPrenomPraticien = findViewById(R.id.textViewPrenomPraticienValeur);
        textViewTelephonePraticien = findViewById(R.id.textViewTelephonePracticienValeur);
        textViewAdressePraticien = findViewById(R.id.textViewAdressePracticienValeur);
        textViewCodePostalPraticien = findViewById(R.id.textViewCodePostalPraticienValeur);
        textViewVillePraticien = findViewById(R.id.textViewVillePraticienValeur);
        textViewCoefNotorietePraticien = findViewById(R.id.textViewCoefNotorietePraticienValeur);
        textViewNumDepartPraticien = findViewById(R.id.textViewNumeroDepartPraticienValeur);
        textViewNomDepartPraticien = findViewById(R.id.textViewNomDepartPraticienValeur);
        textViewNumRegionPraticien = findViewById(R.id.textViewNumRegionPraticienValeur);
    }

    public void getLePraticien() {

        // Récupération de l'information de source d'information
        Bundle extras = getIntent().getExtras();
        String boutonChoisi = (extras != null) ? extras.getString("consultationType") : "";
        String nomPraticien = (extras != null) ? extras.getString("nomPraticien") : "";
        final String prenomPraticien = (extras != null) ? extras.getString("prenomPraticien") : "";


        // Récupération de l'information en fonction du bouton cliqué
        if (boutonChoisi != null) {
            if (boutonChoisi.equals("Connecte")) {

                // Récupération des données via Retrofit
                final PraticienDAOConnecte PraticienAccess = new PraticienDAOConnecte() {

                    @Override
                    public void onTacheTerminee(final ArrayList<Praticien> lesPraticiens) {

                        // Appel de la méthode permettant de retrouver le bon praticien en fonction de son prénom
                        affichage_infos(findPraticienByPrenom(lesPraticiens,prenomPraticien));
                    }

                    @Override
                    public void onTacheTerminee(String resultat) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onTacheTerminee(Praticien resultat) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onErreur(String message) {
                        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
                        // TODO Auto-generated method stub
                    }
                };
                PraticienAccess.getPraticiensParNomDAOConnecte(nomPraticien);
            } else {
                if (boutonChoisi.equals("Deconnecte")) {
                    // Récupération des données via SQLiteOpenHelper
                    PraticienDAODeconnecte PraticienDAODeconnecteAcces = new PraticienDAODeconnecte(this);
                    affichage_infos(findPraticienByPrenom(PraticienDAODeconnecteAcces.getPraticiensByNom(nomPraticien),prenomPraticien));
                } else {
                    // Aucune action réalisée
                }
            }
        }
    }

    public Praticien findPraticienByPrenom(ArrayList<Praticien> lesPraticiens, String lePrenom) {

        // Déclaration du compteur et du booléen de sortie de boucle
        Integer i = 0;
        Boolean trouve = false;

        // Recherche du Praticien ayant un prénom correspondant
        while (i < lesPraticiens.size() && !trouve) {
            if (lesPraticiens.get(i).getPRA_PRENOM().equals(lePrenom)) {
                trouve = true;
            } else {
                i++;
            }
        }

        // Est-ce que l'on a trouvé un praticien ayant un prénom correspondant
        return (trouve) ? lesPraticiens.get(i) : null;
    }

    public void affichage_infos(Praticien lePraticien) {

        // Valorisation des éléments XML
        textViewNumPraticien.setText(String.valueOf(lePraticien.getPRA_NUM()));
        textViewNomPraticien.setText(lePraticien.getPRA_NOM());
        textViewPrenomPraticien.setText(lePraticien.getPRA_PRENOM());
        textViewAdressePraticien.setText(lePraticien.getPRA_ADRESSE());
        textViewVillePraticien.setText(lePraticien.getPRA_VILLE());
        textViewCodePostalPraticien.setText(lePraticien.getPRA_CP());
        textViewTelephonePraticien.setText(lePraticien.getPRA_TELEPHONE());
        textViewCoefNotorietePraticien.setText(String.valueOf(lePraticien.getPRA_COEFNOTORIETE()));
        textViewNumDepartPraticien.setText(lePraticien.getNUM_DEPARTEMENT());
//        textViewNomDepartPraticien.setText(lePraticien.getNOM_DEPARTEMENT());
//        textViewNumRegionPraticien.setText(lePraticien.getNUM_REGION());
    }
}
