package com.btssio.AP4G2.application_gsb.Controler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

public class AffichageInfosPraticien extends AppCompatActivity {
    
    private TextView textViewTitreInfos;

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

    private TextView textViewNumPraticienValeur;
    private TextView textViewNomPraticienValeur;
    private TextView textViewPrenomPraticienValeur;
    private TextView textViewTelephonePraticienValeur;
    private TextView textViewAdressePraticienValeur;
    private TextView textViewCodePostalPraticienValeur;
    private TextView textViewVillePraticienValeur;
    private TextView textViewCoefNotorietePraticienValeur;
    private TextView textViewNumDepartPraticienValeur;
    private TextView textViewNomDepartPraticienValeur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_infos_praticien);

        Initialiser();
        getLePraticien();
    }

    public void Initialiser() {
        // Valorisation des variables représentant les éléments XML

        textViewTitreInfos = findViewById(R.id.textViewNumPraticien);

        textViewNumPraticien = findViewById(R.id.textViewNumPraticien);
        textViewNomPraticien = findViewById(R.id.textViewNomPraticien);
        textViewPrenomPraticien = findViewById(R.id.textViewPrenomPraticien);
        textViewTelephonePraticien = findViewById(R.id.textViewTelephonePracticien);
        textViewAdressePraticien = findViewById(R.id.textViewAdressePracticien);
        textViewCodePostalPraticien = findViewById(R.id.textViewCodePostalPraticien);
        textViewVillePraticien = findViewById(R.id.textViewVillePraticien);
        textViewCoefNotorietePraticien = findViewById(R.id.textViewCoefNotorietePraticien);
        textViewNumDepartPraticien = findViewById(R.id.textViewNumeroDepartPraticien);
        textViewNomDepartPraticien = findViewById(R.id.textViewNomDepartPraticien);

        textViewNumPraticienValeur = findViewById(R.id.textViewNumPraticienValeur);
        textViewNomPraticienValeur = findViewById(R.id.textViewNomPraticienValeur);
        textViewPrenomPraticienValeur = findViewById(R.id.textViewPrenomPraticienValeur);
        textViewTelephonePraticienValeur = findViewById(R.id.textViewTelephonePracticienValeur);
        textViewAdressePraticienValeur = findViewById(R.id.textViewAdressePracticienValeur);
        textViewCodePostalPraticienValeur = findViewById(R.id.textViewCodePostalPraticienValeur);
        textViewVillePraticienValeur = findViewById(R.id.textViewVillePraticienValeur);
        textViewCoefNotorietePraticienValeur = findViewById(R.id.textViewCoefNotorietePraticienValeur);
        textViewNumDepartPraticienValeur = findViewById(R.id.textViewNumeroDepartPraticienValeur);
        textViewNomDepartPraticienValeur = findViewById(R.id.textViewNomDepartPraticienValeur);
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
                        afficherErreur("La connection n'a pas pu être établie.\n" + message);
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

    public void affichage_infos(final Praticien lePraticien) {

        // Valorisation des éléments XML
        textViewNumPraticienValeur.setText(String.valueOf(lePraticien.getPRA_NUM()));
        textViewNomPraticienValeur.setText(lePraticien.getPRA_NOM());
        textViewPrenomPraticienValeur.setText(lePraticien.getPRA_PRENOM());
        textViewAdressePraticienValeur.setText(lePraticien.getPRA_ADRESSE());
        textViewVillePraticienValeur.setText(lePraticien.getPRA_VILLE());
        textViewCodePostalPraticienValeur.setText(lePraticien.getPRA_CP());
        textViewTelephonePraticienValeur.setText(lePraticien.getPRA_TELEPHONE());
        textViewCoefNotorietePraticienValeur.setText(String.valueOf(lePraticien.getPRA_COEFNOTORIETE()));
        textViewNumDepartPraticienValeur.setText(lePraticien.getNUM_DEPARTEMENT());

        // Récupération de l'information de source d'information
        Bundle extras = getIntent().getExtras();
        String boutonChoisi = (extras != null) ? extras.getString("consultationType") : "";

        // Récupération de l'information en fonction du bouton cliqué
        if (boutonChoisi != null) {
            if (boutonChoisi.equals("Connecte")) {

                // Récupération des données via Retrofit
                final DepartementDAOConnecte DepartementAccess = new DepartementDAOConnecte() {

                    @Override
                    public void onTacheTerminee(final ArrayList<Departement> lesDepartements) {

                        // Valorisation de l'élément XML du nom du département
                        textViewNomDepartPraticienValeur.setText(findDepartementNomById(lesDepartements, lePraticien.getNUM_DEPARTEMENT()));
                    }

                    @Override
                    public void onTacheTerminee(String resultat) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onTacheTerminee(Departement resultat) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onErreur(String message) {
                        afficherErreur("La connection n'a pas pu être établie.\n" + message);
                    }
                };
                DepartementAccess.getDepartementsPraticienDAOConnecte();
            } else {
                if (boutonChoisi.equals("Deconnecte")) {
                    // Récupération des données via SQLiteOpenHelper
                    DepartementDAODeconnecte DepartementDAODeconnecteAcces = new DepartementDAODeconnecte(this);
                    ArrayList<Departement> lesDepartements = DepartementDAODeconnecteAcces.getDepartements();
                    textViewNomDepartPraticien.setText(findDepartementNomById(lesDepartements, lePraticien.getNUM_DEPARTEMENT()));
                } else {
                    // Aucune action réalisée
                }
            }
        }
    }

    public String findDepartementNomById(ArrayList<Departement> lesDepartements, String NUM_DEPARTEMENT) {

        // Déclaration du compteur et du booléen de sortie de boucle
        Integer i = 0;
        Boolean trouve = false;

        // Recherche du Praticien ayant un prénom correspondant
        while (i < lesDepartements.size() && !trouve) {
            if (lesDepartements.get(i).getNUM_DEPARTEMENT().equals(NUM_DEPARTEMENT)) {
                trouve = true;
            } else {
                i++;
            }
        }

        // Est-ce que l'on a trouvé un département ayant un numéro correspondant
        return (trouve) ? lesDepartements.get(i).getNOM() : "Aucun nom trouvé";
    }

    public void afficherErreur(String messageErreur) {

        // Affectation du message au champs titre de la page
        textViewTitreInfos.setText(messageErreur);

        // Changement de visibilité des textView libellés pour qu'ils ne soient visibles
        textViewNumPraticien.setVisibility(View.GONE);
        textViewNomPraticien.setVisibility(View.GONE);
        textViewPrenomPraticien.setVisibility(View.GONE);
        textViewTelephonePraticien.setVisibility(View.GONE);
        textViewAdressePraticien.setVisibility(View.GONE);
        textViewCodePostalPraticien.setVisibility(View.GONE);
        textViewVillePraticien.setVisibility(View.GONE);
        textViewCoefNotorietePraticien.setVisibility(View.GONE);
        textViewNumDepartPraticien.setVisibility(View.GONE);
        textViewNomDepartPraticien.setVisibility(View.GONE);

        // Non chChangement de visibilité des textView valorisés par l'utilisateur pour qu'ils ne soient visibles
        textViewNumPraticienValeur.setVisibility(View.GONE);
        textViewNomPraticienValeur.setVisibility(View.GONE);
        textViewPrenomPraticienValeur.setVisibility(View.GONE);
        textViewTelephonePraticienValeur.setVisibility(View.GONE);
        textViewAdressePraticienValeur.setVisibility(View.GONE);
        textViewCodePostalPraticienValeur.setVisibility(View.GONE);
        textViewVillePraticienValeur.setVisibility(View.GONE);
        textViewCoefNotorietePraticienValeur.setVisibility(View.GONE);
        textViewNumDepartPraticienValeur.setVisibility(View.GONE);
        textViewNomDepartPraticienValeur.setVisibility(View.GONE);
    }
}
