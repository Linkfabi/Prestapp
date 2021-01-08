package cl.farastudio.prestapp.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cl.farastudio.prestapp.DTO.Prestamo;
import cl.farastudio.prestapp.helpers.PrestamoDBOpenHelper;

public class PrestamosDAODB implements PrestamosDAO {

    private PrestamoDBOpenHelper db;

    public PrestamosDAODB(Context contexto) {
        this.db = new PrestamoDBOpenHelper(contexto, "prestamo", null, 1);
    }

    @Override
    public Prestamo add(Prestamo p) {
        SQLiteDatabase writer = this.db.getWritableDatabase();
        String sql = String.format("INSERT INTO paciente(tipoPrestamo, nombre, nombreProducto, marca, estado, accesorios, cantidadJuegos" +
                ", ram, modelo, hdd, monto, comision, montoFinal, paginas, fechaPrestamo, fechaEntrega, nota) " +
                "VALUES('"+p.getTipoPrestamo()+"','"+p.getNombre()+"','"+p.getNombreProducto()+"','"+p.getMarca()+"','"+p.getEstado()+"','"+p.getEstado()+"',"+p.getAccesorios()+",'"+p.getCantidadJuegos()+"',"+p.getRam()+"','"+p.getModelo()+"','"+p.getHDD()+"',"+p.getMonto()+",'"+p.getComision()+"','"+p.getMontoFinal()+"','"+p.getPaginas()+"','"+p.getFechaPrestamo()+"',"+p.getFechaEntrega()+",'"+p.getNota()+")");
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
                        p.setNombre(c.getString(1));
                        p.setNombreProducto(c.getString(2));
                        p.setMarca(c.getString(3));
                        p.setEstado(c.getString(4));
                        p.setAccesorios(c.getString(5));
                        p.setCantidadJuegos(c.getInt(6));
                        p.setRam(c.getString(7));
                        p.setModelo(c.getString(8));
                        p.setHDD(c.getString(9));
                        p.setMonto(c.getFloat(10));
                        p.setComision(c.getFloat(11));
                        p.setMontoFinal(c.getFloat(12));
                        p.setPaginas(c.getInt(13));
                        p.setFechaPrestamo(c.getString(14));
                        p.setFechaEntrega(c.getString(15));
                        p.setNota(c.getString(16));
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
