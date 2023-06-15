package com.example.hellowordsem9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hellowordsem9.Adapters.libroAdapter;
import com.example.hellowordsem9.models.Libro;
import com.example.hellowordsem9.servicios.servicesWeb;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetalleLibroActivity extends AppCompatActivity implements OnMapReadyCallback{
    ImageView imgA;
    Button btnEliminar;
    private libroAdapter adapter;
    GoogleMap mMap;
    EditText tvmLatitud;
    EditText tvmLongitud;
    Button btnMapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_libro);

        String libroJson = getIntent().getStringExtra("Libros");
        Libro libro = new Gson().fromJson(libroJson, Libro.class);


        imgA = findViewById(R.id.imgAvatar);
        EditText tvTitulo = findViewById(R.id.tvTitulomin);
        EditText tvmResumen = findViewById(R.id.tvResumenmin);
        EditText tvmAutor = findViewById(R.id.tvAutormin);
        EditText tvmUrl = findViewById(R.id.tvUrl);
        tvmLatitud = findViewById(R.id.tvLatitud);
        tvmLongitud = findViewById(R.id.tvLongitud);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnMapa= findViewById(R.id.btnVerMapa);
        Button btnEditar = findViewById(R.id.btnActualizar);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Picasso.get()
                .load(libro.img) // Carga la imagen desde el enlace proporcionado en el objeto Libro
                .into(imgA);
        tvTitulo.setText(libro.titulo);
        tvmResumen.setText(libro.resumen);
        tvmAutor.setText(libro.autor);
        tvmUrl.setText(libro.img);
        tvmLatitud.setText(String.valueOf(libro.latitude));
        tvmLongitud.setText(String.valueOf(libro.longitude));
        Log.i("primero","Primerooo");
        //setCurrentLocation(libro.latitude,libro.longitude);

        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(DetalleLibroActivity.this, MapsActivity.class);
                String LatitudJson = new Gson().toJson(new LatLng(Double.parseDouble(String.valueOf(tvmLatitud.getText())),Double.parseDouble(String.valueOf(tvmLongitud.getText()))));
                intent.putExtra("LatLong",LatitudJson);
               startActivity(intent);
            }
        });


        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://63746cd448dfab73a4df8801.mockapi.io/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                servicesWeb services = retrofit.create(servicesWeb.class);

                Libro libroEditado = new Libro();
                libroEditado.titulo = String.valueOf(tvTitulo.getText());
                libroEditado.resumen = String.valueOf(tvmResumen.getText());
                libroEditado.autor = String.valueOf(tvmAutor.getText());
                libroEditado.img = String.valueOf(tvmUrl.getText());
                libroEditado.latitude = Double.valueOf(String.valueOf(tvmLatitud.getText()));
                libroEditado.longitude = Double.valueOf(String.valueOf(tvmLongitud.getText()));

                Call<Void> call = services.actualizar(libro.id,libroEditado);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            // La imagen se agregó correctamente a MockAPI
                            onBackPressed();
                        } else {
                            // Hubo un error al agregar la imagen a MockAPI
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        // Error de red o de la API
                    }
                });
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://63746cd448dfab73a4df8801.mockapi.io/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                servicesWeb services = retrofit.create(servicesWeb.class);


                Call<Void> call = services.delete(libro.id);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            // La imagen se agregó correctamente a MockAPI
                            onBackPressed();
                        } else {
                            // Hubo un error al agregar la imagen a MockAPI
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        // Error de red o de la API
                    }
                });
            }
        });


    }

    public void setAdapter(libroAdapter adapter) {
        this.adapter = adapter;
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        Log.i("Segundo","Segundooooo"+tvmLongitud.getText());
        LatLng actual = new LatLng(Double.parseDouble(String.valueOf(tvmLatitud.getText())),Double.parseDouble(String.valueOf(tvmLongitud.getText())));
        Log.i("Tercero","Tercerooo"+actual.toString());
        mMap.addMarker(new MarkerOptions().position(actual).title("actual"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(actual));
    }



}