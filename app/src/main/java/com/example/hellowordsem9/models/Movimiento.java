package com.example.hellowordsem9.models;

public class Movimiento {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private  int id;
    private String tipoMovimiento;
    private double monto;
    private String motivo;
    private double latitud;
    private double longitud;

    public Movimiento(String tipoMovimiento, double monto, String motivo, double latitud, double longitud) {
        this.tipoMovimiento = tipoMovimiento;
        this.monto = monto;
        this.motivo = motivo;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    // Agrega los métodos getter y setter si los necesitas
    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

}
