package com.example.hellowordsem9.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.hellowordsem9.models.Contacto;
import com.example.hellowordsem9.models.Cuentas;
import com.example.hellowordsem9.models.Movimiento;

import java.util.ArrayList;

public class DbCuentas extends DbHelper{
    Context context;
    public DbCuentas(@Nullable Context context) {
        super(context);
        this.context = context;
    }

   public  long insertaCuenta(String nombre){
       long id=0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("Nombre", nombre);

            id = db.insert(TABLE_CUENTAS,null,values);

        }catch (Exception ex){
            ex.toString();
        }
       return id;
   }

   public ArrayList<Cuentas> mostrarCuentas(){
       DbHelper dbHelper = new DbHelper(context);
       SQLiteDatabase db = dbHelper.getWritableDatabase();
       ArrayList<Cuentas> listaCuentas = new ArrayList<>();
       Cuentas cuenta = null;
       Cursor cursorCuentas = null;
       cursorCuentas = db.rawQuery("SELECT * FROM " + TABLE_CUENTAS,null);
       if (cursorCuentas.moveToFirst()) {
       do {
           cuenta = new Cuentas();
           cuenta.setId(cursorCuentas.getInt(0));
           cuenta.setNombre(cursorCuentas.getString(1));
           listaCuentas.add(cuenta);

       }while (cursorCuentas.moveToNext());
       }
       cursorCuentas.close();
       return  listaCuentas;
   }

    public Cuentas detalleCuentas(int id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cuentas cuenta = null;
        Cursor cursorCuentas = null;
        cursorCuentas = db.rawQuery("SELECT * FROM " + TABLE_CUENTAS + " WHERE id =" + id + " LIMIT 1",null);
        if (cursorCuentas.moveToFirst()) {

                cuenta = new Cuentas();
                cuenta.setId(cursorCuentas.getInt(0));
                cuenta.setNombre(cursorCuentas.getString(1));



        }
        cursorCuentas.close();
        return  cuenta;
    }
    public long insertaMovimiento(int cuentaId, String tipoMovimiento, double monto, String motivo, double latitud, double longitud) {
        long movimientoId = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("cuentaId", cuentaId);
            values.put("tipo_movimiento", tipoMovimiento);
            values.put("monto", monto);
            values.put("motivo", motivo);
            values.put("latitud", latitud);
            values.put("longitud", longitud);

            movimientoId = db.insert(TABLE_MOVIMIENTOS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }
        return movimientoId;
    }
    public ArrayList<Movimiento> obtenerMovimientosPorCuenta(int cuentaId) {
        ArrayList<Movimiento> listaMovimientos = new ArrayList<>();

        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MOVIMIENTOS + " WHERE cuentaId = ?", new String[]{String.valueOf(cuentaId)});

            int tipoMovimientoIndex = cursor.getColumnIndex(COLUMN_TIPO_MOVIMIENTO);
            int montoIndex = cursor.getColumnIndex(COLUMN_MONTO);
            int motivoIndex = cursor.getColumnIndex(COLUMN_MOTIVO);
            int latitudIndex = cursor.getColumnIndex(COLUMN_LATITUD);
            int longitudIndex = cursor.getColumnIndex(COLUMN_LONGITUD);

            while (cursor.moveToNext()) {
                String tipoMovimiento = cursor.getString(tipoMovimientoIndex);
                double monto = cursor.getDouble(montoIndex);
                String motivo = cursor.getString(motivoIndex);
                double latitud = cursor.getDouble(latitudIndex);
                double longitud = cursor.getDouble(longitudIndex);

                Movimiento movimiento = new Movimiento(tipoMovimiento, monto, motivo, latitud, longitud);
                listaMovimientos.add(movimiento);
            }

            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return listaMovimientos;
    }



}
