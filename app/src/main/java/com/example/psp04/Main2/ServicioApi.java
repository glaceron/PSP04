package com.example.psp04.Main2;

import com.example.psp04.Main2.modelo.Lista;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ServicioApi {
    @Headers("Content-Type: application/json")
    @GET("urbanismo-infraestructuras/estacion-bicicleta.json?rf=html&start=0&rows=130")
    Call<Lista> getResults();
}
