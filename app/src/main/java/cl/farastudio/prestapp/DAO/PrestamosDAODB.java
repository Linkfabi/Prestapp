package cl.farastudio.prestapp.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cl.farastudio.prestapp.DTO.Prestamo;
import cl.farastudio.prestapp.helpers.PrestamosDBOpenHelper;

public class PrestamosDAODB implements PrestamosDAO {

    private PrestamosDBOpenHelper db;

    public PrestamosDAODB(Context contexto) {
        this.db = new PrestamosDBOpenHelper(contexto, "prestamo", null, 1);
    }

    @Override
    public Prestamo add(Prestamo p) {
        SQLiteDatabase writer = this.db.getWritableDatabase();
        String sql = String.format("INSERT INTO prestamo(tipoPrestamo, nombre, nombreProducto, marca, estado, accesorios, cantidadJuegos" +
                ", ram, modelo, hdd, monto, comision, montoFinal, paginas, fechaPrestamo, fechaEntrega, nota) " +
                "VALUES('"+p.getTipoPrestamo()+"','"+p.getNombre()+"','"+p.getNombreProducto()+"','"+p.getMarca()+"','"+p.getEstado()+"','"+p.getAccesorios()+"',"+p.getCantidadJuegos()+",'"+p.getRam()+"','"+p.getModelo()+"','"+p.getHDD()+"',"+p.getMonto()+","+p.getComision()+","+p.getMontoFinal()+","+p.getPaginas()+",'"+p.getFechaPrestamo()+"','"+p.getFechaEntrega()+"','"+p.getNota()+"')");
        writer.execSQL(sql);
        writer.close();
        return p;
    }

    @Override
    public List<Prestamo> getAll() {
        List<Prestamo> prestamos = new ArrayList<>();
        SQLiteDatabase reader = this.db.getReadableDatabase();
        try {
            if (reader != null) {
                Cursor c = reader.rawQuery("SELECT * " +
                        "FROM prestamo", null);
                if (c.moveToFirst()) {
                    do {
                        Prestamo p = new Prestamo();
                        p.setId(c.getInt(0));
                        p.setTipoPrestamo(c.getString(1));
                        p.setNombre(c.getString(2));
                        p.setNombreProducto(c.getString(3));
                        p.setMarca(c.getString(4));
                        p.setEstado(c.getString(5));
                        p.setAccesorios(c.getString(6));
                        p.setCantidadJuegos(c.getInt(7));
                        p.setRam(c.getString(8));
                        p.setModelo(c.getString(9));
                        p.setHDD(c.getString(10));
                        p.setMonto(c.getFloat(11));
                        p.setComision(c.getFloat(12));
                        p.setMontoFinal(c.getFloat(13));
                        p.setPaginas(c.getInt(14));
                        p.setFechaPrestamo(c.getString(15));
                        p.setFechaEntrega(c.getString(16));
                        p.setNota(c.getString(17));
                        prestamos.add(p);
                    } while (c.moveToNext());
                }
                reader.close();
            }
        } catch (Exception ex) {
            prestamos = null;
        }
        return prestamos;
    }

}
