package com.btssio.AP4G2.application_gsb.Modele;

import android.util.Log;

import com.btssio.AP4G2.application_gsb.Interface.ApiClient;
import com.btssio.AP4G2.application_gsb.Interface.EventAsync;
import com.btssio.AP4G2.application_gsb.Interface.InterfacePraticien;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by warmee on 06/12/2021.
 */

public abstract class PraticienDAOConnecte implements EventAsync<Praticien>{
    private InterfacePraticien apiPraticien;

    public PraticienDAOConnecte(){
        apiPraticien = ApiClient.getClient().create(InterfacePraticien.class);
    }

    public void getPraticienDAOConnecte(){
        Call<ArrayList<Praticien>> callPraticien;
        callPraticien = apiPraticien.lesPraticiens();
        callPraticien.enqueue(new Callback<ArrayList<Praticien>>() {
            @Override
            public void onResponse(Call<ArrayList<Praticien>> call, Response<ArrayList<Praticien>> response) {
                if (response.isSuccessful()) {
                    onTacheTerminee(response.body());
                } else {
                    int responseString = response.code();
                    onErreur("erreur : "+responseString);
                    Log.d("log", "erreur : "+responseString);
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Praticien>> call, Throwable t) {
                onErreur("onFailurePra : "+t.getMessage());
                Log.d("log", "erreur : "+t.getMessage());
            }
        });
    }
}
