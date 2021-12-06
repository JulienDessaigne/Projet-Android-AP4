package com.btssio.AP4G2.application_gsb.Modele;

import com.btssio.AP4G2.application_gsb.Interface.ApiClient;
import com.btssio.AP4G2.application_gsb.Interface.EventAsync;
import com.btssio.AP4G2.application_gsb.Interface.InterfacePraticien;

/**
 * Created by warmee on 06/12/2021.
 */

public abstract class PraticienDAOConnecte implements EventAsync<Medecin>{
    private InterfacePraticien apiMedcin;

    public PraticienDAOConnecte(){
        apiMedcin = ApiClient.getClient().create(InterfacePraticien.class);
    }

}
