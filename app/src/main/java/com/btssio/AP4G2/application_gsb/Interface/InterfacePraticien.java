package com.btssio.AP4G2.application_gsb.Interface;

import com.btssio.AP4G2.application_gsb.Modele.Praticien;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by veschembes on 29/11/2021.
 */

public interface InterfacePraticien {

    @POST("lesmedecins.php")
    Call<ArrayList<Praticien>> lesPraticiens();

    @GET("lesmedecins_par_depart.php")
    Call<ArrayList<Praticien>> lesPraticiensParDepart(@Query("libelledepartement") String libelleDepartement);

    @GET("lesmedecins_par_nom.php")
    Call<ArrayList<Praticien>> lesPraticiensParNom(@Query("nom") String nom);
}
