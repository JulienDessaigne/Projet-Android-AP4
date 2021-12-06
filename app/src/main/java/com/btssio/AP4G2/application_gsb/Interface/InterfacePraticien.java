package com.btssio.AP4G2.application_gsb.Interface;

import com.btssio.AP4G2.application_gsb.Modele.Medecin;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by veschembes on 29/11/2021.
 */

public interface InterfacePraticien {

    @POST("lesmedcins.php")
    Call<ArrayList<Medecin>> lesmedecins();

    @POST("lesmedcins_par_depart.php")
    Call<ArrayList<Medecin>> lesmedcins_par_depart();

    @POST("lesmedcins_par_nom.php")
    Call<ArrayList<Medecin>> lesmedcins_par_nom();
}
