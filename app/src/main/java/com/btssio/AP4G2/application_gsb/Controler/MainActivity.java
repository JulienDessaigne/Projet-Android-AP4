package com.btssio.AP4G2.application_gsb.Controler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.btssio.AP4G2.application_gsb.R;

public class MainActivity extends AppCompatActivity {
    private Button btnAffichageDeco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            btnAffichageDeco = findViewById(R.id.bouton3);

            btnAffichageDeco.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getBaseContext(), affichage_praticien_deco.class);
                    startActivity(intent);
                }
            });
    }
}

