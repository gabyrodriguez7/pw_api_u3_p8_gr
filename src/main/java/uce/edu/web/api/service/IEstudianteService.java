package uce.edu.web.api.service;

import java.util.List;

import uce.edu.web.api.repository.modelo.Estudiante;

public interface IEstudianteService {

    public Estudiante buscarPorID (Integer id);
    //public List<Estudiante> buscarTodos(String genero);
    public List<Estudiante> buscarTodos();
    public void actualizarPorId(Estudiante estudiante);
    public void actualizarParcialPorID(Estudiante estudiante);
    public void borrarPorID(Integer id);
    public void guardar(Estudiante estudiante);

}
