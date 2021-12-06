package com.btssio.AP4G2.application_gsb.Controler;

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
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.btssio.AP4G2.application_gsb.R;

import java.util.ArrayList;
import java.util.HashMap;

public class AffichageConsultation extends AppCompatActivity {

    private Spinner spinnerDepartement;
    private ListView listviewEntete;
    private ListView listviewConcurrent;

    SpinnerAdapter spinner_departement;
    ListAdapter adapter_entete;
    ListAdapter adapter_concurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_consultation);


        Initialiser();
        remplirspinnerDepartement();
        remplirlistviewEntete();
        remplirlistviewConcurrent();
        gestion_suppression();
    }

    public void Initialiser() {
        listviewEntete = findViewById(R.id.listViewEntete);
        listviewConcurrent = findViewById(R.id.listViewMedecin);
    }

    public void remplirspinnerDepartement() {
        Log.d("log", "methode remplirSinViticulteurs()");

        final ViticulteurDAO viticulteurAcces = new ViticulteurDAO() {

            @Override
            public void onTacheTerminee(final ArrayList<Viticulteur> lesViticulteurs) {
                Log.d("log", "MainActivity.getViticulteurs : " + lesViticulteurs.toString());

                // remplissage du spinner a l'aide de la liste recuperee
                Spinner spinViticulteurs = findViewById(R.id.spinViticulteurs);

                ArrayAdapter<String> spinViticulteursAdapter = new ArrayAdapter<>(
                        MainActivity.this.getBaseContext(), android.R.layout.simple_spinner_item
                );

                for (int i = 0; i < lesViticulteurs.size(); i++) {
                    spinViticulteursAdapter.add(lesViticulteurs.get(i).getNomV());
                }

                spinViticulteurs.setAdapter(spinViticulteursAdapter);
                // gestion des evenements sur le spinner de selection du viticulteur
                spinViticulteurs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        // TODO Auto-generated method stub
                        Log.d("log", arg2 + " " + lesViticulteurs.get(arg2));
                        viticulteurSelectionne = lesViticulteurs.get(arg2);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub
                    }
                });
            }

            @Override
            public void onTacheTerminee(String resultat) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTacheTerminee(Viticulteur resultat) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onErreur(String message) {
                Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
                // TODO Auto-generated method stub
            }
        };
        viticulteurAcces.getViticulteursDAO();
    }

    public void remplirlistviewEntete() {

        // Création d'un tableau associatif
        HashMap<String, String> map_entete = new HashMap<>();
        map_entete.put("nom", "Nom");
        map_entete.put("prenom", " Prenom");
        map_entete.put("note", " Note");

        // Création collection
        ArrayList<HashMap<String, String>> maliste_entete = new ArrayList<>();
        maliste_entete.add(map_entete);

        try {

            adapter_entete = new SimpleAdapter(this,
                    maliste_entete, R.layout.colonnes, new
                    String[] { "nom", "prenom", "note" }, new
                    int[] {
                    R.id.textViewColonne1, R.id.textViewColonne2, R.id.textViewColonne3
            });
            // MAJ de la listview à utilisant l'adapter

            listviewEntete.setAdapter(adapter_entete);
        } catch (Exception e) {}
    }

    public void remplirlistviewConcurrent() {

        final Gestionnaire_viticulteur unGestionnaireviticulteur = (Gestionnaire_viticulteur) getApplicationContext();

        // Création collection
        ArrayList<HashMap<String, String>> maliste_concurrent = new ArrayList<>();

        for (Viticulteur unViticulteur : unGestionnaireviticulteur.getLesViticulteurs()) {

            if (unViticulteur instanceof Viticulteur_concurrent) {

                // Création d'un tableau associatif
                HashMap<String, String> map_concurrent = new HashMap<>();

                // Rajout des valeurs dans le Hashmap
                map_concurrent.put("nom", unViticulteur.getNomV());
                map_concurrent.put("prenom", unViticulteur.getPrenomV());
                map_concurrent.put("note", String.valueOf(((Viticulteur_concurrent) unViticulteur).getNoteV()));

                // Rajout du Hashmap dans la collection
                maliste_concurrent.add(map_concurrent);
            }
        }

        try {

            adapter_concurrent = new SimpleAdapter(this,
                    maliste_concurrent,
                    R.layout.colonnes,
                    new String[] { "nom", "prenom", "note" },
                    new int[] { R.id.NomC, R.id.PrenomC, R.id.NoteC
                    });

            // MAJ de la listview à utilisant l'adapter
            listviewConcurrent.setAdapter(adapter_concurrent);
        } catch (Exception e) {}
    }

    public void gestion_suppression(){

        final Gestionnaire_viticulteur unGestionnaireviticulteur = (Gestionnaire_viticulteur) getApplicationContext();

        listviewConcurrent.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String itemClique = adapterView.getItemAtPosition(i).toString();
                itemClique = itemClique.substring(1, itemClique.length()-1);
                String[] keyValuePairs = itemClique.split(",");
                HashMap<String,String> ItemDecoupe = new HashMap<>();

                for(String pair : keyValuePairs) {
                    String[] entry = pair.split("=");
                    ItemDecoupe.put(entry[0].trim(), entry[1].trim());
                }

                Toast.makeText(getApplicationContext(), "Viticulteur supprimé !", Toast.LENGTH_LONG).show();
                unGestionnaireviticulteur.supprimerViticulteur(unGestionnaireviticulteur.getViticulteurs(ItemDecoupe.get("nom"), ItemDecoupe.get("prenom")));

                remplirlistviewConcurrent();
            }
        });
    }
}
