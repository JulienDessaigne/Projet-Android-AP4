package com.btssio.AP4G2.application_gsb.Controler;

import android.content.Intent;
import android.icu.util.LocaleData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.btssio.AP4G2.application_gsb.Modele.Departement;
import com.btssio.AP4G2.application_gsb.Modele.DepartementDAOConnecte;
import com.btssio.AP4G2.application_gsb.Modele.DepartementDAODeconnecte;
import com.btssio.AP4G2.application_gsb.Modele.Praticien;
import com.btssio.AP4G2.application_gsb.Modele.PraticienDAOConnecte;
import com.btssio.AP4G2.application_gsb.Modele.PraticienDAODeconnecte;
import com.btssio.AP4G2.application_gsb.R;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Button btnAffichageConn,btnAffichageMAJ;
    private Button btnAffichageImport;
    private Button btnAffichageDeco;
    private TextView TexteViewRetour;
    private String reponse;
    private PraticienDAODeconnecte praticienDecoAccess;
    private DepartementDAODeconnecte departementDecoAccess;
    private PraticienDAODeconnecte praticienDAODeconnecteAcces;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Initialiser();
        gestionBoutton();
    }

    /**
     * Valorisation des variables représentant les éléments XML
     * Valorisation des accès à la BdD locale
     */
    public void Initialiser() {
        btnAffichageConn = findViewById(R.id.bouton1);
        btnAffichageImport = findViewById(R.id.bouton2);
        btnAffichageDeco = findViewById(R.id.bouton3);
        btnAffichageMAJ = findViewById(R.id.bouton4);
        TexteViewRetour = findViewById(R.id.textView);

        praticienDecoAccess = new PraticienDAODeconnecte(this);
        departementDecoAccess = new DepartementDAODeconnecte(this);
        praticienDAODeconnecteAcces = new PraticienDAODeconnecte(this);
    }

    /**
     * Fonction gérant les boutons du layout
     */
    public void gestionBoutton() {

        //Bouton pour la fonction connecté
        btnAffichageConn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AffichageConsultation.class);
                intent.putExtra("consultationType","Connecte");
                startActivity(intent);
            }
        });

        //Bouton pour l'importation des données
        btnAffichageImport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Test","=============test=============");
                DepartementDAOConnecte DepartementDAO = new DepartementDAOConnecte(){
                    @Override

                public void onTacheTerminee(String resultat) {
                }
                @Override
                public void onTacheTerminee(ArrayList<Departement> resultat) {
                    if(resultat.isEmpty()){
                        Toast.makeText(getApplicationContext(),"Aucun departement importer (Collection vide)",Toast.LENGTH_SHORT ).show();
                    }else{
                        for(Departement unDepartement : resultat){
                            //Log.d("Collection departement",unDepartement.toString());
                            departementDecoAccess.addDepartement(unDepartement);
                        }
                        final PraticienDAOConnecte PraticienDAO = new PraticienDAOConnecte() {
                            @Override
                            public void onTacheTerminee(String resultat) {
                            }

                            @Override
                            public void onTacheTerminee(ArrayList<Praticien> resultat) {
                                if(resultat.isEmpty()){
                                    Log.d("Collection Praticien","==========================");
                                    Log.d("Collection Praticien","collection praticien vide");
                                    Log.d("Collection Praticien","==========================");
                                }else{
                                    for(Praticien unPraticien : resultat){
                                        //Log.d("Collection Praticien",unPraticien.toString());
                                        praticienDecoAccess.addPraticien(unPraticien);
                                    }

                                    ArrayList<Praticien> lesPraticiens = praticienDAODeconnecteAcces.getPraticiens();
                                    for(final Praticien unNomPrenomPrat:lesPraticiens){
                                        final PraticienDAOConnecte PraticienDAONomPrenom = new PraticienDAOConnecte() {
                                            @Override
                                            public void onTacheTerminee(String resultat) {
                                            }
                                            @Override
                                            public void onTacheTerminee(ArrayList<Praticien> resultat) {
                                                if(resultat!=null){
                                                    Log.d("Praticien début","==========================");
                                                    for(Praticien unPraticien : resultat){
                                                        if(unPraticien.getPRA_PRENOM().equals(unNomPrenomPrat.getPRA_PRENOM())){
                                                            Log.d("Liste praticien",unPraticien.toString());
                                                            praticienDecoAccess.addNumDepartementPraticien(unNomPrenomPrat.getPRA_PRENOM(),unNomPrenomPrat.getPRA_NOM(),unPraticien.getNUM_DEPARTEMENT());
                                                        }
                                                    }
                                                    Log.d("Praticien fin","==========================");
                                                }else{
                                                    Log.d("Modif praticien","==========================");
                                                    Log.d("Modif praticien","collection praticien vide");
                                                    Log.d("Modif praticien","==========================");
                                                }
                                                String dateAujourdhui = java.text.DateFormat.getDateTimeInstance().format(new Date());
                                                reponse="La base de donnée a bien été créée \n"+dateAujourdhui;
                                                TexteViewRetour.setText(reponse);
                                                TexteViewRetour.setVisibility(View.VISIBLE);
                                            }
                                            @Override
                                            public void onTacheTerminee(Praticien resultat) {
                                            }
                                            @Override
                                            public void onErreur(String message) {
                                                reponse="La connection n'a pas pu être établie.\n"+message;
                                                TexteViewRetour.setText(reponse);
                                                TexteViewRetour.setVisibility(View.VISIBLE);
                                            }
                                        };
                                        //Log.d("Modif praticien",unNomPrenomPrat.getPRA_NOM());
                                        PraticienDAONomPrenom.getPraticiensParNomDAOConnecte(unNomPrenomPrat.getPRA_NOM());
                                    }
                                    ArrayList<Departement> lesDepartements = departementDecoAccess.getDepartements();
                                    Log.d("Département début","==========================");
                                    for(Departement leDepartements : lesDepartements){
                                        Log.d("Liste Département",leDepartements.toString());
                                    }
                                    Log.d("Département fin","==========================");


                                }
                            }

                            @Override
                            public void onTacheTerminee(Praticien resultat) {
                            }

                            @Override
                            public void onErreur(String message) {
                                reponse="La connection n'a pas pu être établie.\n"+message;
                                TexteViewRetour.setText(reponse);
                                TexteViewRetour.setVisibility(View.VISIBLE);
                            }
                        };
                        PraticienDAO.getPraticienDAOConnecte();

                    }
                }

                @Override
                public void onTacheTerminee(Departement resultat) {
                }

                @Override
                public void onErreur(String message) {
                    reponse="La connection n'a pas pu être établie.\n"+message;
                    TexteViewRetour.setText(reponse);
                    TexteViewRetour.setVisibility(View.VISIBLE);
                }
            };
            DepartementDAO.getDepartementsDAOConnecte();
            }

        });


        //Bouton pour la fonction déconnecté
        btnAffichageDeco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AffichageConsultation.class);
                intent.putExtra("consultationType","Deconnecte");
                startActivity(intent);
            }
        });

        btnAffichageMAJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
