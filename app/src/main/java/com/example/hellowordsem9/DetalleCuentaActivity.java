package com.example.hellowordsem9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.hellowordsem9.db.DbCuentas;
import com.example.hellowordsem9.models.Cuentas;


public class DetalleCuentaActivity extends AppCompatActivity {
    EditText etNombreDetalleCuenta;
    Button btnCrearMovimiento, btnVerMovimiento, Sincronisar;
    Cuentas cuenta;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_cuenta);

        etNombreDetalleCuenta = findViewById(R.id.etNombreDetalleCuenta);
        btnCrearMovimiento = findViewById(R.id.btnCrearMovimiento);
        btnVerMovimiento = findViewById(R.id.btnVerMovimiento);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = 0;
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = savedInstanceState.getInt("ID", 0);
        }

        DbCuentas dbCuentas = new DbCuentas(DetalleCuentaActivity.this);
        cuenta = dbCuentas.detalleCuentas(id);
        if (cuenta != null) {
            etNombreDetalleCuenta.setText(cuenta.getNombre());
        }

        btnCrearMovimiento.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AgregarMovimientoActivity.class);
            intent.putExtra("cuentaId", id); // Pasa el ID de la cuenta seleccionada al crear movimiento
            startActivity(intent);
        });

        btnVerMovimiento.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ListaMovimientosActivity.class);
            intent.putExtra("cuentaId", id); // Pasa el ID de la cuenta seleccionada a la actividad de lista de movimientos
            startActivity(intent);
        });
        //Sincronisar.setOnClickListener(view -> sincronizarDatos());
    }


}
