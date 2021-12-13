package com.btssio.AP4G2.application_gsb.Controler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.btssio.AP4G2.application_gsb.Modele.Departement;
import com.btssio.AP4G2.application_gsb.Modele.DepartementDAOConnecte;
import com.btssio.AP4G2.application_gsb.Modele.DepartementDAODeconnecte;
import com.btssio.AP4G2.application_gsb.Modele.Praticien;
import com.btssio.AP4G2.application_gsb.Modele.PraticienDAOConnecte;
import com.btssio.AP4G2.application_gsb.Modele.PraticienDAODeconnecte;
import com.btssio.AP4G2.application_gsb.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button btnAffichageConn,btnAffichageMAJ;
    private Button btnAffichageImport;
    private Button btnAffichageDeco;
    private PraticienDAODeconnecte praticienDecoAccess;
    private DepartementDAODeconnecte departementDecoAccess;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Initialiser();
        gestionBoutton();
    }


    public void Initialiser() {
        btnAffichageConn = findViewById(R.id.bouton1);
        btnAffichageImport = findViewById(R.id.bouton2);
        btnAffichageDeco = findViewById(R.id.bouton3);
        btnAffichageMAJ = findViewById(R.id.bouton4);
        praticienDecoAccess = new PraticienDAODeconnecte(this);
        departementDecoAccess = new DepartementDAODeconnecte(this);
    }


    public void gestionBoutton() {
        btnAffichageConn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AffichageConsultation.class);
                intent.putExtra("consultationType","Connecte");
                startActivity(intent);
            }
        });

        btnAffichageImport.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PraticienDAOConnecte PraticienDeco = new PraticienDAOConnecte() {
                    @Override
                    public void onTacheTerminee(String resultat) {
                    }
                    @Override
                    public void onTacheTerminee(ArrayList<Praticien> resultat) {
                        if(resultat.isEmpty()){
                            Toast.makeText(getApplicationContext(),"Aucun praticien importer (Collection vide)",Toast.LENGTH_SHORT ).show();
                        }else{
                            for(Praticien unPraticien : resultat){
                                praticienDecoAccess.addPraticien(unPraticien);
                            }
                        }
                    }
                    @Override
                    public void onTacheTerminee(Praticien resultat) {
                    }
                    @Override
                    public void onErreur(String message) {
                    }
                };

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
                                departementDecoAccess.addDepartement(unDepartement);
                            }
                        }
                    }

                    @Override
                    public void onTacheTerminee(Departement resultat) {
                    }
                    @Override
                    public void onErreur(String message) {
                    }
                };


            }
        });

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
                Intent intent = new Intent(getBaseContext(), AffichageConsultation.class);
                startActivity(intent);

            }
        });
    }
}
