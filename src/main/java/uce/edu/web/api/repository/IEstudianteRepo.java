package uce.edu.web.api.repository;

import java.util.List;

import uce.edu.web.api.repository.modelo.Estudiante;

public interface IEstudianteRepo {
    public Estudiante seleccionarPorId(Integer id);
    //public List<Estudiante> seleccionarTodos(String genero);
    public List<Estudiante> seleccionarTodos();
    public void actualizarPorId(Estudiante estudiante);
    public void actualizarParcialPorID(Estudiante estudiante);
    public void borrarPorID(Integer id);
    public Estudiante insertar(Estudiante estudiante);   
}
 