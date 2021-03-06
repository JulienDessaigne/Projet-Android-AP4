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
    /**
     * Fonction qui valorise tous les ??l??ments visuel du layout
     */
    public void Initialiser() {
        // Valorisation des variables repr??sentant les ??l??ments XML

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

        // R??cup??ration de l'information de source d'information
        Bundle extras = getIntent().getExtras();
        String boutonChoisi = (extras != null) ? extras.getString("consultationType") : "";
        String nomPraticien = (extras != null) ? extras.getString("nomPraticien") : "";
        final String prenomPraticien = (extras != null) ? extras.getString("prenomPraticien") : "";


        // R??cup??ration de l'information en fonction du bouton cliqu??
        if (boutonChoisi != null) {
            //Si on choisi la fonction connect??
            if (boutonChoisi.equals("Connecte")) {

                // R??cup??ration des donn??es via Retrofit
                final PraticienDAOConnecte PraticienAccess = new PraticienDAOConnecte() {

                    @Override
                    public void onTacheTerminee(final ArrayList<Praticien> lesPraticiens) {

                        // Appel de la m??thode permettant de retrouver le bon praticien en fonction de son pr??nom
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
                        afficherErreur("La connection n'a pas pu ??tre ??tablie.\n" + message);
                    }
                };
                PraticienAccess.getPraticiensParNomDAOConnecte(nomPraticien);
            } else {

                //Si on choisi la fonction d??connect??
                if (boutonChoisi.equals("Deconnecte")) {

                    // R??cup??ration des donn??es via SQLiteOpenHelper
                    PraticienDAODeconnecte PraticienDAODeconnecteAcces = new PraticienDAODeconnecte(this);
                    affichage_infos(findPraticienByPrenom(PraticienDAODeconnecteAcces.getPraticiensByNom(nomPraticien),prenomPraticien));
                } else {
                    // Message d'erreur
                    // dans le cas d'un acc??s ?? l'affichage sans passage par les boutons disponibles
                    afficherErreur("Erreur fonctionnalit?? choisie");
                }
            }
        }
    }

    /**
     * Fonction qui permet de trouver le bon praticien gr??ce au pr??nom et ainsi d'??viter les probl??mes d'homonyme
     * @param lesPraticiens
     * @param lePrenom
     * @return
     */
    public Praticien findPraticienByPrenom(ArrayList<Praticien> lesPraticiens, String lePrenom) {

        // D??claration du compteur et du bool??en de sortie de boucle
        Integer i = 0;
        Boolean trouve = false;

        // Recherche du Praticien ayant un pr??nom correspondant
        while (i < lesPraticiens.size() && !trouve) {
            if (lesPraticiens.get(i).getPRA_PRENOM().equals(lePrenom)) {
                trouve = true;
            } else {
                i++;
            }
        }

        // Est-ce que l'on a trouv?? un praticien ayant un pr??nom correspondant
        return (trouve) ? lesPraticiens.get(i) : null;
    }

    /**
     * Fonciton permettant de retrouver le bon praticien en fonction de son pr??nom
     * @param lePraticien
     */
    public void affichage_infos(final Praticien lePraticien) {

        // Valorisation des ??l??ments XML
        textViewNumPraticienValeur.setText(String.valueOf(lePraticien.getPRA_NUM()));
        textViewNomPraticienValeur.setText(lePraticien.getPRA_NOM());
        textViewPrenomPraticienValeur.setText(lePraticien.getPRA_PRENOM());
        textViewAdressePraticienValeur.setText(lePraticien.getPRA_ADRESSE());
        textViewVillePraticienValeur.setText(lePraticien.getPRA_VILLE());
        textViewCodePostalPraticienValeur.setText(lePraticien.getPRA_CP());
        textViewTelephonePraticienValeur.setText(lePraticien.getPRA_TELEPHONE());
        textViewCoefNotorietePraticienValeur.setText(String.valueOf(lePraticien.getPRA_COEFNOTORIETE()));
        textViewNumDepartPraticienValeur.setText(lePraticien.getNUM_DEPARTEMENT());

        // R??cup??ration de l'information de source d'information
        Bundle extras = getIntent().getExtras();
        String boutonChoisi = (extras != null) ? extras.getString("consultationType") : "";

        // R??cup??ration de l'information en fonction du bouton cliqu??
        if (boutonChoisi != null) {
            //Si on choisi la fonction connect??
            if (boutonChoisi.equals("Connecte")) {

                // R??cup??ration des donn??es via Retrofit
                final DepartementDAOConnecte DepartementAccess = new DepartementDAOConnecte() {

                    @Override
                    public void onTacheTerminee(final ArrayList<Departement> lesDepartements) {

                        // Valorisation de l'??l??ment XML du nom du d??partement
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
                        afficherErreur("La connection n'a pas pu ??tre ??tablie.\n" + message);
                    }
                };
                DepartementAccess.getDepartementsPraticienDAOConnecte();
            } else {
                //Si on choisi la fonction d??connect??
                if (boutonChoisi.equals("Deconnecte")) {
                    // R??cup??ration des donn??es via SQLiteOpenHelper
                    DepartementDAODeconnecte DepartementDAODeconnecteAcces = new DepartementDAODeconnecte(this);
                    ArrayList<Departement> lesDepartements = DepartementDAODeconnecteAcces.getDepartements();
                    textViewNomDepartPraticienValeur.setText(findDepartementNomById(lesDepartements, lePraticien.getNUM_DEPARTEMENT()));
                } else {
                    // Message d'erreur
                    // dans le cas d'un acc??s ?? l'affichage sans passage par les boutons disponibles
                    afficherErreur("Erreur fonctionnalit?? choisie");
                }
            }
        }
    }

    /**
     * Fonction permettant de trouver le nom du d??partement du praticien
     * @param lesDepartements
     * @param NUM_DEPARTEMENT
     * @return
     */
    public String findDepartementNomById(ArrayList<Departement> lesDepartements, String NUM_DEPARTEMENT) {

        // D??claration du compteur et du bool??en de sortie de boucle
        Integer i = 0;
        Boolean trouve = false;

        // Recherche du Praticien ayant un pr??nom correspondant
        while (i < lesDepartements.size() && !trouve) {
            if (lesDepartements.get(i).getNUM_DEPARTEMENT().equals(NUM_DEPARTEMENT)) {
                trouve = true;
            } else {
                i++;
            }
        }

        // Est-ce que l'on a trouv?? un d??partement ayant un num??ro correspondant
        return (trouve) ? lesDepartements.get(i).getNOM() : "Aucun nom trouv??";
    }

    /**
     * Fonction qui permet d'afficher un message d'erreur dans l'application
     * @param messageErreur
     */
    public void afficherErreur(String messageErreur) {

        // Affectation du message au champs titre de la page
        textViewTitreInfos.setText(messageErreur);

        // Changement de visibilit?? des textView libell??s pour qu'ils ne soient visibles
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

        // Non chChangement de visibilit?? des textView valoris??s par l'utilisateur pour qu'ils ne soient visibles
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
