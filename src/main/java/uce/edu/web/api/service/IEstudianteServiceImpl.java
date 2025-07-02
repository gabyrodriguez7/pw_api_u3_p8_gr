package uce.edu.web.api.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.repository.IEstudianteRepo;
import uce.edu.web.api.repository.modelo.Estudiante;

@ApplicationScoped
public class IEstudianteServiceImpl implements IEstudianteService {

    @Inject
    private IEstudianteRepo estudianteRepo;

    @Override
    public Estudiante buscarPorID(Integer id) {
        return this.estudianteRepo.seleccionarPorId(id);
    }

    @Override
    public List<Estudiante> buscarTodos(String genero) {
        return this.estudianteRepo.seleccionarTodos(genero);
    }

    @Override
    public void actualizarPorId(Estudiante estudiante) {
     this.estudianteRepo.actualizarParcialPorID(estudiante);
    }

    @Override
    public void actualizarParcialPorID(Estudiante estudiante) {
      this.estudianteRepo.actualizarParcialPorID(estudiante);
    }

    @Override
    public void borrarPorID(Integer id) {
      this.estudianteRepo.borrarPorID(id);
    }

    @Override
    public void guardar(Estudiante estudiante) {
       this.estudianteRepo.insertar(estudiante);
    }

}
