package com.btssio.AP4G2.application_gsb.Interface;

import com.btssio.AP4G2.application_gsb.Modele.Praticien;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by veschembes on 29/11/2021.
 */

public interface InterfacePraticien {

    @POST("lesmedcins.php")
    Call<ArrayList<Praticien>> lesPraticiens();

    @POST("lesmedcins_par_depart.php")
    Call<ArrayList<Praticien>> lesmedcinsParDepart();

    @POST("lesmedcins_par_nom.php")
    Call<ArrayList<Praticien>> lesmedcins_par_nom();
}
