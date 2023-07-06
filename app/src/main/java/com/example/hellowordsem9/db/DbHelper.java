package com.example.hellowordsem9.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "cuanta.db";
    public static final String TABLE_CUENTAS = "cuentas";
    public static final String TABLE_MOVIMIENTOS = "movimientos";
    public static final String COLUMN_TIPO_MOVIMIENTO = "tipo_movimiento";
    public static final String COLUMN_MONTO = "monto";
    public static final String COLUMN_MOTIVO = "motivo";
    public static final String COLUMN_LATITUD = "latitud";
    public static final String COLUMN_LONGITUD = "longitud";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createCuentasTableQuery = "CREATE TABLE " + TABLE_CUENTAS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL)";
        sqLiteDatabase.execSQL(createCuentasTableQuery);

        // Crear tabla movimientos
        String createMovimientosTableQuery = "CREATE TABLE " + TABLE_MOVIMIENTOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "cuentaId INTEGER," +
                COLUMN_TIPO_MOVIMIENTO + " TEXT," +
                COLUMN_MONTO + " REAL CHECK(" + COLUMN_MONTO + " >= 0)," +
                COLUMN_MOTIVO + " TEXT," +
                COLUMN_LATITUD + " REAL," +
                COLUMN_LONGITUD + " REAL," +
                "FOREIGN KEY(cuentaId) REFERENCES " + TABLE_CUENTAS + "(id))";
        sqLiteDatabase.execSQL(createMovimientosTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIMIENTOS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CUENTAS);
        onCreate(sqLiteDatabase);
    }
}
