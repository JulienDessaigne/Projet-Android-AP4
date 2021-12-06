package com.btssio.AP4G2.application_gsb.Modele;


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

    public DepartementDAOConnecte(){
        apiDepartement = ApiClient.getClient().create(InterfaceDepartement.class);
    }

    public void getDepartementsMedecinDAOConnecte() {
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
                    onErreur("erreur responseString Departement : "+responseString);
                }
            }
    
            @Override
            public void onFailure(Call<ArrayList<Departement>> call, Throwable t) {
                onErreur("erreur onFailure Departement: "+t.getMessage());
            }
        });
    }
}
