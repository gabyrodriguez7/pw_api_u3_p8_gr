package uce.edu.web.api.service;


import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.repository.IProfesorRepo;
import uce.edu.web.api.repository.modelo.Profesor;

@ApplicationScoped
public class IProfesorServiceImpl implements IProfesorService {
    @Inject
    public IProfesorRepo profesorRepo;

    @Override
    public List<Profesor> consultarTodos() {
        return this.profesorRepo.seleccionarTodos();
    }
    
    @Override
    public Profesor guardar(Profesor profesor) {
       return this.profesorRepo.insertar(profesor);
    }

    @Override
    public void actualizarPorId(Profesor profesor) {
        this.profesorRepo.actualizarPorId(profesor);
    }

    @Override
    public void actualizarParcialPorId(Profesor profesor) {
        this.profesorRepo.actualizarParcialPorId(profesor);
    }

    @Override
    public void borrarporId(Integer id) {
        this.profesorRepo.borrarPorId(id);
    }

    @Override
    public Profesor buscarPorId(Integer id) {
      return this.profesorRepo.seleccionarPorId(id);
    }

}
