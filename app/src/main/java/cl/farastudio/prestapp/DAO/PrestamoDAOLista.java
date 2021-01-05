package cl.farastudio.prestapp.DAO;

import java.util.ArrayList;
import java.util.List;

import cl.farastudio.prestapp.DTO.Prestamo;

public class PrestamoDAOLista implements PrestamoDAO{

    private List<Prestamo> prestamo = new ArrayList<>();
    private static PrestamoDAOLista instancia;
    public static PrestamoDAOLista getInstance(){
        if(instancia==null){
            instancia=new PrestamoDAOLista();
        }
        return instancia;
    }

    private PrestamoDAOLista(){
        Prestamo p=new Prestamo();
        /*p.setId(0);
        p.setRut("19655234-0");
        p.setNombre("Luis");
        p.setApellido("Rodriguez");
        p.setFecha("15/11/2020");
        p.setArea_Trabajo("Otros");
        p.setSintoma(true);
        p.setTemperatura(42);
        p.setTos(false);
        p.setArterial(70);*/
        prestamo.add(p);
    }

    @Override
    public Prestamo add(Prestamo p) {
        prestamo.add(p);
        return p;
    }

    @Override
    public List<Prestamo> getAll() {
        return prestamo;
    }

}
