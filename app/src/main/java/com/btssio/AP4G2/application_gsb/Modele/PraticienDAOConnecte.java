package com.btssio.AP4G2.application_gsb.Modele;

import android.support.annotation.NonNull;
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

    /**
     * Constructeur initialisant l'api
     */
    public PraticienDAOConnecte(){
        apiPraticien = ApiClient.getClient().create(InterfacePraticien.class);
    }

    /**
     * Récupère les Praticiens
     */
    public void getPraticienDAOConnecte(){
        Call<ArrayList<Praticien>> callPraticien;
        callPraticien = apiPraticien.lesPraticiens();
        callPraticien.enqueue(new Callback<ArrayList<Praticien>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Praticien>> call, @NonNull Response<ArrayList<Praticien>> response) {
                if (response.isSuccessful()) {
                    onTacheTerminee(response.body());
                } else {
                    int responseString = response.code();
                    Log.d("ERREUR", "Erreur Response Praticien : getPraticienDAOConnecte");
                    onErreur("Code d'erreur : " + responseString);
                }
            }
            @Override
            public void onFailure(@NonNull Call<ArrayList<Praticien>> call, @NonNull Throwable t) {
                onErreur("onFailurePra : "+t.getMessage());
                Log.d("log", "erreur : "+t.getMessage());
            }
        });
    }

    /**
     * Récupère les praticiens d'un département passé en paramètre
     * @param libelleDepartement
     */
    public void getPraticiensParDepartementDAOConnecte(String libelleDepartement) {
        Call<ArrayList<Praticien>> callPraticien;
        callPraticien = apiPraticien.lesPraticiensParDepart(libelleDepartement);
        callPraticien.enqueue(new Callback<ArrayList<Praticien>>() {

            @Override
            public void onResponse(@NonNull Call<ArrayList<Praticien>> call, @NonNull Response<ArrayList<Praticien>> response) {
                //String displayResponse = "";
                if (response.isSuccessful()) {
                    onTacheTerminee(response.body());
                } else {
                    int responseString = response.code();
                    Log.d("ERREUR", "Erreur Response Praticien : getPraticiensParDepartementDAOConnecte");
                    onErreur("Code d'erreur : " + responseString);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Praticien>> call, @NonNull Throwable t) {
                Log.d("ERREUR", "onFailure Praticien : getPraticiensParDepartementDAOConnecte");
                onErreur("Erreur : "+t.getMessage());
            }
        });
    }

    /**
     * Récupère les praticiens par un nom passé en paramètre
     * @param nom_medecin
     */
    public void getPraticiensParNomDAOConnecte(String nom_medecin){
        Call<ArrayList<Praticien>> callPraticien;
        callPraticien = apiPraticien.lesPraticiensParNom(nom_medecin);
        callPraticien.enqueue(new Callback<ArrayList<Praticien>>() {

            @Override
            public void onResponse(@NonNull Call<ArrayList<Praticien>> call, @NonNull Response<ArrayList<Praticien>> response) {
                //String displayResponse = "";
                if (response.isSuccessful()) {
                    onTacheTerminee(response.body());
                } else {
                    int responseString = response.code();
                    Log.d("ERREUR", "Erreur Response Praticien : getPraticiensParNomDAOConnecte");
                    onErreur("Code d'erreur : " + responseString);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Praticien>> call, @NonNull Throwable t) {
                Log.d("ERREUR", "onFailure Praticien : getPraticiensParNomDAOConnecte");
                onErreur("Erreur : "+t.getMessage());
            }
        });
    }
}
