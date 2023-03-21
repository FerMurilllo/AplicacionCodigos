package com.example.aplicacioncodigos;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface ApiCodigo {

    @FormUrlEncoded
    @POST("validarcodigo")
    Call<codigo> CODIGO_CALL(
            @Field("Codigo_APP") String Codigo_APP
    );

}








