package com.example.hellowordsem9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.hellowordsem9.Adapters.CuentaAdapter;
import com.example.hellowordsem9.db.DbCuentas;
import com.example.hellowordsem9.models.Cuentas;

import java.util.ArrayList;

public class ListaCuentasActivity extends AppCompatActivity {
RecyclerView listaCuentas;
ArrayList<Cuentas> listaArrayCuentas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cuentas);

        listaCuentas = findViewById(R.id.rvListaCuentas);
        listaCuentas.setLayoutManager(new LinearLayoutManager(this));
        DbCuentas dbCuentas = new DbCuentas(ListaCuentasActivity.this);
        listaArrayCuentas = new ArrayList<>();


        CuentaAdapter adapter = new CuentaAdapter(dbCuentas.mostrarCuentas());
        listaCuentas.setAdapter(adapter);
    }


}