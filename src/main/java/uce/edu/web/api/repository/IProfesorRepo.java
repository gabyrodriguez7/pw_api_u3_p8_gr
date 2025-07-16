package uce.edu.web.api.repository;

import java.util.List;

import uce.edu.web.api.repository.modelo.Profesor;

public interface IProfesorRepo {
    public Profesor seleccionarPorId(Integer id);
    public List<Profesor> seleccionarTodos();
    public Profesor insertar(Profesor profesor);
    public void actualizarPorId(Profesor profesor);
    public void actualizarParcialPorId(Profesor profesor);
    public void borrarPorId(Integer id);

}
