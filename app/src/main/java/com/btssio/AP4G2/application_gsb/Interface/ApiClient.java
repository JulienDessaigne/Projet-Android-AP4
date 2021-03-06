package com.btssio.AP4G2.application_gsb.Interface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by veschembes on 06/12/2021.
 */

public class ApiClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        retrofit = new Retrofit.Builder()
                .baseUrl("http://172.24.0.33/GSB/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
