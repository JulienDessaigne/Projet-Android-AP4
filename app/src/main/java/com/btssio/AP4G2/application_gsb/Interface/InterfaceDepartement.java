package com.btssio.AP4G2.application_gsb.Interface;

import com.btssio.AP4G2.application_gsb.Modele.Departement;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
/**
 * Created by warmee on 06/12/2021.
 */

public interface InterfaceDepartement {

    @POST("lesdepartements.php")
    Call<ArrayList<Departement>> lesdepartements();

    @POST("lesdepartements_des_medecins.php")
    Call<ArrayList<Departement>> lesdepartements_des_medecins();
}
