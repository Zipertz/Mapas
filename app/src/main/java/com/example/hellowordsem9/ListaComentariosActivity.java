package com.example.hellowordsem9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.hellowordsem9.Adapters.libroAdapter;
import com.example.hellowordsem9.Adapters.publicacionAdapter;
import com.example.hellowordsem9.models.Comentarios;
import com.example.hellowordsem9.models.Libro;
import com.example.hellowordsem9.servicios.ServicesWebComentarios;
import com.example.hellowordsem9.servicios.servicesWeb;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaComentariosActivity extends AppCompatActivity {
    public RecyclerView rv;
    List<Comentarios> comentarios= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_comentarios);
        cargarData();
    }
    @Override
    protected void onResume() {
        super.onResume();
        // Aquí puedes llamar al método que deseas ejecutar cuando la pantalla está en primer plano
        cargarData();
    }

    protected void cargarData(){
        Retrofit retrofit = new  Retrofit.Builder()
                .baseUrl("https://63746cd448dfab73a4df8801.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServicesWebComentarios services = retrofit.create(ServicesWebComentarios.class);
        Call<List<Comentarios>> call=services.getContacts();

        call.enqueue(new Callback<List<Comentarios>>() {
            @Override
            public void onResponse(Call<List<Comentarios>> call, Response<List<Comentarios>> response) {
                if (!response.isSuccessful()){
                    Log.e("asd1234", "error");
                }else{
                    Log.i("asdasd12312", new Gson().toJson(response.body()));
                    Log.i("asd32", "Respuesta correcta");

                    comentarios=response.body();




                    rv= findViewById(R.id.rvLibro);
                    rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rv.setHasFixedSize(true);

                }
            }

            @Override
            public void onFailure(Call<List<Comentarios>> call, Throwable t) {
                Log.e("asd1234", "no hay conexion");
            }
        });

    }
}