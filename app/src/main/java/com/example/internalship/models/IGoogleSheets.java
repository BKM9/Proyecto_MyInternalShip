package com.example.internalship.models;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface IGoogleSheets {

    @GET
    Call<String> getPacientes(@Url String url);

    @GET
    Call<String> getObservaciones(@Url String url);

    @POST("exec")
    Call<String> getStringRequestBody(@Body String body);

}
