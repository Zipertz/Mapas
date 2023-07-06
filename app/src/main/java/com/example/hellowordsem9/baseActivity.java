package com.example.hellowordsem9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hellowordsem9.db.DbHelper;

public class baseActivity extends AppCompatActivity {
Button btnCrear,btnCrearCuenta,btnListarCuenta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        btnCrear = findViewById(R.id.btnCrear);
        btnCrearCuenta = findViewById(R.id.btnCrearCuenta);
        btnListarCuenta = findViewById(R.id.btnListarCuenta);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper dbHelper = new DbHelper(baseActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if(db != null){
                    Toast.makeText(baseActivity.this, "BASE DE DATOS CREADA", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(baseActivity.this, "ERRORAL CREAR BASE DE DATOS ", Toast.LENGTH_LONG).show();
                }

            }
        });

        btnCrearCuenta.setOnClickListener(view -> {


            Intent intent = new Intent(getApplicationContext(), CrearCuentaActivity.class);
            startActivity(intent);

        });

        btnListarCuenta.setOnClickListener(view -> {

            Intent intent = new Intent(getApplicationContext(), ListaCuentasActivity.class);
            startActivity(intent);

        });
    }
}