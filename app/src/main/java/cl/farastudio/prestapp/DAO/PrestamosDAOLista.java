package cl.farastudio.prestapp.DAO;

import java.util.ArrayList;
import java.util.List;

import cl.farastudio.prestapp.DTO.Prestamo;

public class PrestamosDAOLista implements PrestamosDAO {

    private List<Prestamo> prestamos = new ArrayList<>();
    private static PrestamosDAOLista instancia;
    public static PrestamosDAOLista getInstance(){
        if(instancia==null){
            instancia=new PrestamosDAOLista();
        }
        return instancia;
    }

    private PrestamosDAOLista(){
        Prestamo p=new Prestamo();
        p.setId(25);
        p.setTipoPrestamo("Dinero");
        p.setNombre("Fabian Astorga");
        p.setNombreProducto(null);
        p.setMarca(null);
        p.setEstado(null);
        p.setAccesorios(null);
        p.setCantidadJuegos(0);
        p.setRam(null);
        p.setModelo(null);
        p.setHDD(null);
        p.setMonto(2500);
        p.setComision(3);
        p.setMontoFinal(7500);
        p.setPaginas(0);
        p.setFechaPrestamo("25/12/2020");
        p.setFechaEntrega("25/1/2021");
        p.setNota("No pagara nada xD");
        prestamos.add(p);
    }

    @Override
    public Prestamo add(Prestamo p) {
        prestamos.add(p);
        return p;
    }

    @Override
    public List<Prestamo> getAll() {
        return prestamos;
    }

}
