package com.btssio.AP4G2.application_gsb.Modele;


import android.util.Log;

import com.btssio.AP4G2.application_gsb.Interface.ApiClient;
import com.btssio.AP4G2.application_gsb.Interface.EventAsync;
import com.btssio.AP4G2.application_gsb.Interface.InterfaceDepartement;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * Created by warmee on 06/12/2021.
 */

public abstract class DepartementDAOConnecte implements EventAsync<Departement> {
    private InterfaceDepartement apiDepartement;

    /**
     * Constructeur initialisant l'api
     */
    public DepartementDAOConnecte(){
        apiDepartement = ApiClient.getClient().create(InterfaceDepartement.class);
    }

    /**
     * Récupère les departements
     */
    public void getDepartementsDAOConnecte() {
        Call<ArrayList<Departement>> callDepartement;
        callDepartement = apiDepartement.lesDepartements();
        callDepartement.enqueue(new Callback<ArrayList<Departement>>() {

            @Override
            public void onResponse(Call<ArrayList<Departement>> call, Response<ArrayList<Departement>> response) {
                //String displayResponse = "";
                if (response.isSuccessful()) {
                    onTacheTerminee(response.body());
                } else {
                    int responseString = response.code();
                    Log.d("ERREUR", "Erreur Response Departement : getDepartementsDAOConnecte");
                    onErreur("Code d'erreur : " + responseString);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Departement>> call, Throwable t) {
                Log.d("ERREUR", "onFailure Departement : getDepartementsDAOConnecte");
                onErreur("Erreur : "+t.getMessage());
            }
        });
    }

    /**
     * Récupère les départements des praticiens
     */
    public void getDepartementsPraticienDAOConnecte() {
        Call<ArrayList<Departement>> callDepartement;
        callDepartement = apiDepartement.lesDepartementsDesPraticiens();
        callDepartement.enqueue(new Callback<ArrayList<Departement>>() {
    
            @Override
            public void onResponse(Call<ArrayList<Departement>> call, Response<ArrayList<Departement>> response) {
                //String displayResponse = "";
                if (response.isSuccessful()) {
                    onTacheTerminee(response.body());
                } else {
                    int responseString = response.code();
                    Log.d("ERREUR", "Erreur Response Departement : getDepartementsPraticienDAOConnecte");
                    onErreur("Code d'erreur : " + responseString);
                }
            }
    
            @Override
            public void onFailure(Call<ArrayList<Departement>> call, Throwable t) {
                Log.d("ERREUR", "onFailure Departement : getDepartementsPraticienDAOConnecte");
                onErreur("Erreur : "+t.getMessage());
            }
        });
    }

}
