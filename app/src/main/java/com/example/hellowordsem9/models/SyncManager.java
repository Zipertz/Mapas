package com.example.hellowordsem9.models;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.hellowordsem9.servicios.ApiService;

import java.util.List;

public class SyncManager {
    private static final String BASE_URL = "https://api.mockapi.io/";
    private ApiService apiService;

    public SyncManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public void sincronizarCuentas(List<Cuentas> cuentas) {
        apiService.enviarCuentas(cuentas).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Éxito al enviar las cuentas
                } else {
                    // Error al enviar las cuentas
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Error de conexión
            }
        });
    }

    public void sincronizarMovimientos(List<Movimiento> movimientos) {
        apiService.enviarMovimientos(movimientos).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Éxito al enviar los movimientos
                } else {
                    // Error al enviar los movimientos
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Error de conexión
            }
        });
    }
}
