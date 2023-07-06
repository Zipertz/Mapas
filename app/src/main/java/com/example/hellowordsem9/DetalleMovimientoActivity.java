package com.example.hellowordsem9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.hellowordsem9.models.Cuentas;
import com.example.hellowordsem9.models.Movimiento;

public class DetalleMovimientoActivity extends AppCompatActivity {

    EditText etTipoMovimiento,etMontoMovimiento,etMotivoMovimiento,etLatitudMovimiento,etLongitudMovimiento;
    Button btnVerMapa;
    Cuentas cuenta;
    Movimiento movimiento;
    int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_movimiento);

        etTipoMovimiento = findViewById(R.id.etTipoMovimiento);
        etMontoMovimiento = findViewById(R.id.etMontoMovimiento);
        etMotivoMovimiento = findViewById(R.id.etMotivoMovimiento);
        etLatitudMovimiento = findViewById(R.id.etLatitudMovimiento);
        etLongitudMovimiento = findViewById(R.id.etLongitudMovimiento);
        btnVerMapa = findViewById(R.id.btnVerMapa);
    }
}