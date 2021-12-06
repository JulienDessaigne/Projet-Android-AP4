package com.btssio.AP4G2.application_gsb.Controler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.btssio.AP4G2.application_gsb.R;

public class MainActivity extends AppCompatActivity {
    private Button btnAffichageConn;
    private Button btnAffichageImport;
    private Button btnAffichageDeco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Initialiser();

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
                Intent intent = new Intent(getBaseContext(), AffichageConsultation.class);
                startActivity(intent);
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
    }

    public void Initialiser() {
        btnAffichageConn = findViewById(R.id.bouton1);
        btnAffichageImport = findViewById(R.id.bouton2);
        btnAffichageDeco = findViewById(R.id.bouton3);
    }
}
