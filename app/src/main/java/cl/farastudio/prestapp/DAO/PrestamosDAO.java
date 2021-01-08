package cl.farastudio.prestapp.DAO;

import java.util.List;

import cl.farastudio.prestapp.DTO.Prestamo;

public interface PrestamosDAO {

    Prestamo add(Prestamo p);

    List<Prestamo> getAll();
}
