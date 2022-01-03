package com.btssio.AP4G2.application_gsb.Controler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
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

public class AffichageConsultation extends AppCompatActivity {

    private Departement DepartementSelectionne;
    private Spinner spinnerDepartement;
    private ListView listviewEntete;
    private ListView listviewPraticien;

    ListAdapter adapter_entete;
    ListAdapter adapterPraticien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_consultation);

        Initialiser();
        remplirSpinnerDepartementParBouton();
        remplirlistviewEntete();
        remplirListViewPraticienParBouton(DepartementSelectionne);
        //gestion_infosPraticien();
    }

    public void Initialiser() {

        // Valorisation des variables représentant les éléments XML
        spinnerDepartement = findViewById(R.id.spinnerDepartement);
        listviewEntete = findViewById(R.id.listViewEntete);
        listviewPraticien = findViewById(R.id.listViewPraticien);
    }

    public void remplirSpinnerDepartementParBouton() {

        // Récupération de l'information de source d'information
        Bundle extras = getIntent().getExtras();
        String boutonChoisi = (extras != null) ? extras.getString("consultationType") : "";

        // Récupération de l'information en fonction du bouton cliqué
        if (boutonChoisi != null) {
            if (boutonChoisi.equals("Connecte")) {

                // Récupération des données via Retrofit
                final DepartementDAOConnecte DepartementAcces = new DepartementDAOConnecte() {

                    @Override
                    public void onTacheTerminee(final ArrayList<Departement> lesDepartements) {

                        // Appel de la méthode remplissant le spinner des départements
                        remplirSpinnerDepartementParListeDepartement(lesDepartements);
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
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                        // TODO Auto-generated method stub
                    }
                };
                DepartementAcces.getDepartementsPraticienDAOConnecte();
            } else {
                if (boutonChoisi.equals("Deconnecte")) {
                    // Récupération des données via SQLiteOpenHelper
                    DepartementDAODeconnecte DepartementDAODeconnecteAcces = new DepartementDAODeconnecte(this);
                    remplirSpinnerDepartementParListeDepartement(DepartementDAODeconnecteAcces.getDepartements());
                } else {
                    // Valorisation à vide du tableau de recencement,
                    // dans le cas d'un accès à l'affichage sans passage par les boutons disponibles
                    remplirSpinnerDepartementParListeDepartement(new ArrayList<Departement>());
                }
            }
        }
    }

    public void remplirSpinnerDepartementParListeDepartement(final ArrayList<Departement> lesDepartements) {

        // Déclaration de l'adaptateur pour le spinner
        ArrayAdapter<String> spinDepartementsAdapter = new ArrayAdapter<>(
                AffichageConsultation.this.getBaseContext(), android.R.layout.simple_spinner_item
        );

        // Récupération des noms des départements
        for (int i = 0; i < lesDepartements.size(); i++) {
            spinDepartementsAdapter.add(lesDepartements.get(i).getNOM());
            Log.d("Test",lesDepartements.get(i).getNOM());
        }

        spinnerDepartement.setAdapter(spinDepartementsAdapter);
        // gestion des evenements sur le spinner de selection du Departement
        spinnerDepartement.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                remplirListViewPraticienParBouton(lesDepartements.get(arg2));
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    public void remplirlistviewEntete() {

        // Création d'un tableau associatif
        HashMap<String, String> map_entete = new HashMap<>();
        map_entete.put("PRA_NOM", "Nom");
        map_entete.put("PRA_PRENOM", " Prenom");

        // Création collection
        ArrayList<HashMap<String, String>> maliste_entete = new ArrayList<>();
        maliste_entete.add(map_entete);

        try {

            adapter_entete = new SimpleAdapter(this,
                    maliste_entete, R.layout.colonne, new
                    String[]{"PRA_NOM", "PRA_PRENOM"}, new
                    int[]{
                    R.id.textViewColonne1, R.id.textViewColonne2
            });

            listviewEntete.setAdapter(adapter_entete);
        } catch (Exception e) {
        }
    }

    public void remplirListViewPraticienParBouton(Departement DepartementSelectionne) {

        // Récupération de l'information de source d'information
        Bundle extras = getIntent().getExtras();
        String boutonChoisi = (extras != null) ? extras.getString("consultationType") : "";

        // Récupération de l'information en fonction du bouton cliqué
        if (boutonChoisi.equals("Connecte")) {

            // Récupération des données via Retrofit
            final PraticienDAOConnecte PraticienAcces = new PraticienDAOConnecte() {

                @Override
                public void onTacheTerminee(final ArrayList<Praticien> lesPraticiens) {

                    // Utilisation de la liste de Praticiens récupérée
                    remplirListViewPraticienParListePraticiens(lesPraticiens);
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
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    // TODO Auto-generated method stub
                }
            };

            if (DepartementSelectionne != null) {
                PraticienAcces.getPraticiensParDepartementDAOConnecte(DepartementSelectionne.getNOM());
            }
        } else {
            if (boutonChoisi.equals("Deconnecte")) {
                if (DepartementSelectionne != null) {
                    // Récupération des données via SQLiteOpenHelper
                    PraticienDAODeconnecte praticienDAODeconnecteAcces = new PraticienDAODeconnecte(this);
                    Log.d("DEPARTEMENT SELECTIONNE", DepartementSelectionne.getNUM_DEPARTEMENT());
                    remplirListViewPraticienParListePraticiens(praticienDAODeconnecteAcces.getPraticiensByDepartement(DepartementSelectionne.getNUM_DEPARTEMENT()));
                }


            } else {

                // Valorisation à vide du tableau de recencement,
                // dans le cas d'un accès à l'affichage sans passage par les boutons disponibles
                remplirListViewPraticienParListePraticiens(new ArrayList<Praticien>());
            }
        }
    }

    public void remplirListViewPraticienParListePraticiens(ArrayList<Praticien> lesPraticiens) {

        // Création collection
        ArrayList<HashMap<String, String>> listePraticien = new ArrayList<>();

        for (Praticien unPraticien : lesPraticiens) {

            // Création d'un tableau associatif
            HashMap<String, String> mapPraticien = new HashMap<>();

            // Rajout des valeurs dans le Hashmap
            mapPraticien.put("PRA_NOM", unPraticien.getPRA_NOM());
            mapPraticien.put("PRA_PRENOM", unPraticien.getPRA_PRENOM());

            // Rajout du Hashmap dans la collection
            listePraticien.add(mapPraticien);
        }

        try {

            adapterPraticien = new SimpleAdapter(this,
                    listePraticien,
                    R.layout.colonne,
                    new String[]{"PRA_NOM", "PRA_PRENOM"},
                    new int[]{R.id.textViewColonne1, R.id.textViewColonne2
                    });

            // MAJ de la listview à utilisant l'adapter
            listviewPraticien.setAdapter(adapterPraticien);
        } catch (Exception e) {
        }
    }
/*
    public void gestion_infosPraticien(){

        listviewPraticien.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String itemClique = adapterView.getItemAtPosition(i).toString();
                itemClique = itemClique.substring(1, itemClique.length()-1);
                String[] paireCleValeur = itemClique.split(",");
                HashMap<String,String> ItemDecoupe = new HashMap<>();

                for(String pair : paireCleValeur) {
                    String[] entry = pair.split("=");
                    ItemDecoupe.put(entry[0].trim(), entry[1].trim());
                }

                // Récupération de l'information de source d'information
                Bundle extras = getIntent().getExtras();
                String boutonChoisi = (extras != null) ? extras.getString("consultationType") : "";

                // Affichage de l'activité responsable de l'affichage des données complètes du praticien sélectionné
                Intent intent = new Intent(getBaseContext(), AffichageInfosPraticien.class);
                intent.putExtra("consultationType", boutonChoisi); // Ajout du choix Connecté/Deconnecté
                intent.putExtra("nomPraticien", ItemDecoupe.get("nom")); // Ajout du nom du praticien sélectionné
                intent.putExtra("prenomPraticien", ItemDecoupe.get("prenom")); // Ajout du prénom du praticien sélectionné
                startActivity(intent);
            }
        });
    }*/
}
