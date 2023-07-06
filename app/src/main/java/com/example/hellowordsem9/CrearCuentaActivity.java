package com.example.hellowordsem9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hellowordsem9.db.DbCuentas;

public class CrearCuentaActivity extends AppCompatActivity {
EditText etNombreCuenta;
Button btnCrearCuenta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);
        etNombreCuenta = findViewById(R.id.etNombreCuenta);
        btnCrearCuenta = findViewById(R.id.btnCrearCuenta);


        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbCuentas dbCuentas = new DbCuentas(CrearCuentaActivity.this);
                long id=dbCuentas.insertaCuenta(etNombreCuenta.getText().toString());
                if (id>0){
                    Toast.makeText(CrearCuentaActivity.this, "Dato Creado", Toast.LENGTH_LONG).show();
                    limpiar();
                }else{
                    Toast.makeText(CrearCuentaActivity.this, "Error al Guardar ", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    private void limpiar(){
        etNombreCuenta.setText("");
    }
}