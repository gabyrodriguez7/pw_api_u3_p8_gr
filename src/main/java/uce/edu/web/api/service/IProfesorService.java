package uce.edu.web.api.service;

import java.util.List;

import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.to.ProfesorTo;

public interface IProfesorService {
    public ProfesorTo buscarPorId(Integer id, UriInfo uriInfo);
    public List<Profesor> consultarTodos();
    public void guardar(Profesor profesor);
    public void actualizarPorId(Profesor profesor);
    public void actualizarParcialPorId(Profesor profesor);
    public void borrarporId(Integer id);
}
