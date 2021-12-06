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

public interface InterfaceMedecin {

    @POST("getJures.php")
    Call<ArrayList<Medecin>> getlesJures();

    @FormUrlEncoded
    @POST("getJureByIdJ.php")
    Call<Medecin> getJureparid(@Field("idJ") long idJure);
}
