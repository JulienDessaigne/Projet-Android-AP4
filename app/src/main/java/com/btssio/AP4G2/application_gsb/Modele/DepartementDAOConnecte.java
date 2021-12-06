package com.btssio.AP4G2.application_gsb.Modele;


import com.btssio.AP4G2.application_gsb.Interface.ApiClient;
import com.btssio.AP4G2.application_gsb.Interface.EventAsync;
import com.btssio.AP4G2.application_gsb.Interface.InterfaceDepartement;
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

}
