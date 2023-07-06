package com.example.hellowordsem9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.hellowordsem9.Adapters.MovimientoAdapter;
import com.example.hellowordsem9.db.DbCuentas;
import com.example.hellowordsem9.models.Movimiento;

import java.util.ArrayList;

public class ListaMovimientosActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MovimientoAdapter movimientoAdapter;
    private ArrayList<Movimiento> listaMovimientos;
    private DbCuentas dbCuentas;
    private int cuentaId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_movimientos);
        // Obtener el ID de la cuenta desde el intent
        cuentaId = getIntent().getIntExtra("cuentaId", 0);

        dbCuentas = new DbCuentas(this);
        // Obtener la lista de movimientos por cuenta
        listaMovimientos = dbCuentas.obtenerMovimientosPorCuenta(cuentaId);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        movimientoAdapter = new MovimientoAdapter(listaMovimientos);
        recyclerView.setAdapter(movimientoAdapter);
    }
}