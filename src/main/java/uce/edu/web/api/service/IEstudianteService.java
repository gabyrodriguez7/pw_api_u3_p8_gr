package uce.edu.web.api.service;

import java.util.List;

import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.service.to.EstudianteTo;

public interface IEstudianteService {

    public EstudianteTo buscarPorID (Integer id, UriInfo uriInfo);
    public List<Estudiante> buscarTodos(String genero);
    public void actualizarPorId(Estudiante estudiante);
    public void actualizarParcialPorID(Estudiante estudiante);
    public void borrarPorID(Integer id);
    public void guardar(Estudiante estudiante);

}
