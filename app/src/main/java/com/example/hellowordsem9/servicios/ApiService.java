package com.example.hellowordsem9.servicios;

import com.example.hellowordsem9.models.Cuentas;
import com.example.hellowordsem9.models.Movimiento;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("cuentas")
    Call<Void> enviarCuentas(@Body List<Cuentas> cuentas);

    @POST("movimientos")
    Call<Void> enviarMovimientos(@Body List<Movimiento> movimientos);
}
