package com.example.hellowordsem9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hellowordsem9.db.DbCuentas;
import com.example.hellowordsem9.servicios.servicesWeb;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AgregarMovimientoActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener, LocationListener {
    EditText etLatitud, etLongitud, etTipo, etMonto, etMotivo;
    Button btnAgregarMovimiento;
    int cuentaId;
    GoogleMap mMap;
    private static final int REQUEST_CAMERA = 1;
    private LocationManager mLocationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_movimiento);

        etTipo = findViewById(R.id.etTipo);
        etMonto = findViewById(R.id.etMonto);
        etMotivo = findViewById(R.id.etMotivo);
        etLatitud = findViewById(R.id.etLatitud);
        etLongitud = findViewById(R.id.etLongitud);
        btnAgregarMovimiento = findViewById(R.id.btnAgregarMovimiento);
        Button btnCamara = findViewById(R.id.btnCameraa);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        if(
                checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED ||
                        checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            String[] permissions = new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            };
            requestPermissions(permissions, 3000);

        }
        else {
            // configurar frecuencia de actualización de GPS
            mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            //mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L, 1, this);
            Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        }


        cuentaId = getIntent().getIntExtra("cuentaId", 0); // Obtener el ID de la cuenta seleccionada

        btnAgregarMovimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener los valores del formulario
                String tipoMovimiento = etTipo.getText().toString();
                double monto = Double.parseDouble(etMonto.getText().toString());
                String motivo = etMotivo.getText().toString();
                double latitud = Double.parseDouble(etLatitud.getText().toString());
                double longitud = Double.parseDouble(etLongitud.getText().toString());

                // Llamar al método para insertar el movimiento en la base de datos
                DbCuentas dbCuentas = new DbCuentas(AgregarMovimientoActivity.this);
                long movimientoId = dbCuentas.insertaMovimiento(cuentaId, tipoMovimiento, monto, motivo, latitud, longitud);

                // Verificar si la inserción del movimiento fue exitosa
                if (movimientoId != -1) {
                    Toast.makeText(AgregarMovimientoActivity.this, "Movimiento creado", Toast.LENGTH_SHORT).show();
                    limpiarFormulario();
                } else {
                    Toast.makeText(AgregarMovimientoActivity.this, "Error al crear el movimiento", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOpenCamara();
            }
        });
    }
    private void onOpenCamara() {
        if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            Log.i("My app","El permiso de la cámara ya se ha otorgado, puedes realizar la acción deseada aquí");
            OpenCamera();
        } else {
            // El permiso de la cámara no se ha otorgado, solicítalo al usuario
            requestPermissions(new String[]{Manifest.permission.CAMERA}, 1000);
            onOpenCamara();
            Log.i("My app","No tienes permiso");
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");
        }
    }

    private void OpenCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA );
    }
    private void limpiarFormulario() {
        etTipo.setText("");
        etMonto.setText("");
        etMotivo.setText("");
        etLatitud.setText("");
        etLongitud.setText("");
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapLongClickListener(this);

        showCurrentLocation();
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        etLatitud.setText(String.valueOf(latLng.latitude));
        etLongitud.setText(String.valueOf(latLng.longitude));

        mMap.clear();
        LatLng mexico = new LatLng(latLng.latitude,latLng.longitude);
        mMap.addMarker(new MarkerOptions().position(mexico).title(""));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mexico));
    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        etLatitud.setText(String.valueOf(latLng.latitude));
        etLongitud.setText(String.valueOf(latLng.longitude));

        mMap.clear();
        LatLng mexico = new LatLng(latLng.latitude,latLng.longitude);
        mMap.addMarker(new MarkerOptions().position(mexico).title(""));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mexico));
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        Double latitude = location.getLatitude();
        Double longitude = location.getLongitude();

        LatLng latLng = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        Log.i("MAIN_APP: Location - ",  "Latitude: " + latitude);
        Log.i("MAIN_APP: Location - ",  "Longitude: " + longitude);
    }

    private void showCurrentLocation() {
        // Verificar si los permisos de ubicación están disponibles
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            // Obtener la última ubicación conocida del dispositivo
            FusedLocationProviderClient fusedLocationClient =
                    LocationServices.getFusedLocationProviderClient(this);
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, location -> {
                        if (location != null) {
                            // Obtener la latitud y longitud
                            LatLng currentLatLng = new LatLng(location.getLatitude(),
                                    location.getLongitude());

                            // Mover la cámara del mapa a la ubicación actual
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15));
                            mMap.addMarker(new MarkerOptions().position(currentLatLng).title("Marker"));
                            etLatitud.setText(String.valueOf(currentLatLng.latitude));
                            etLongitud.setText(String.valueOf(currentLatLng.longitude));
                        }
                    });
        }
    }

}
