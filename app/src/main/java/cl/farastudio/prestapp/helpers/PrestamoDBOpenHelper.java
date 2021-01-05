package cl.farastudio.prestapp.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.SQLDataException;

public class PrestamoDBOpenHelper extends SQLiteOpenHelper {

    private final String sqlCreate ="CREATE TABLE prestamo("+"id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
            "tipoPrestamo TEXT,"+
            "nombre TEXT,"+
            "nombreProducto TEXT,"+
            "marca TEXT,"+
            "estado TEXT,"+
            "accesorios TEXT,"+
            "cantidadJuegos INTEGER,"+
            "ram TEXT,"+
            "modelo TEXT,"+
            "hdd TEXT,"+
            "monto REAL,"+
            "comision REAL,"+
            "montoFinal REAL,"+
            "paginas INTEGER,"+
            "fechaPrestamo TEXT,"+
            "fechaEntrega TEXT,"+
            "nota TEXT)";

    public PrestamoDBOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlCreate);
        sqLiteDatabase.execSQL("INSERT INTO prestamo(tipoPrestamo, nombre, nombreProducto, marca, estado, accesorios, cantidadJuegos" +
                ", ram, modelo, hdd, monto, comision, montoFinal, fechaPrestamo, fechaEntrega, nota)"+
                "VALUES ('Dinero'"+  //tipoPrestamo
                ",'Fabian Astorga'" +  //nombre
                ",null" +  //nombreProducto
                ",null" +  //marca
                ",null" +  //estado
                ",null" +  //accesorios
                ",null" +  //cantidadJuegos
                ",null"+  //ram
                ",null" +  //modelo
                ",null" +  //hdd
                ",5000" +  //monto
                ",2.5" +  //comision
                ",5125" +  //montoFinal
                ",null" +  //paginas
                ",'05/1/2021'" +  //fechaPrestamo
                ",'10/1/2021'"+  //fechaEntrega
                ",'Si devuelve en mas tiempo se cobra 2% mas de comision')");  //nota
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS prestamo");
        sqLiteDatabase.execSQL(sqlCreate);
    }
}
