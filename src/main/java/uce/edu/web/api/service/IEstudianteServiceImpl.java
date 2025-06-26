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
    public List<Estudiante> buscarTodos() {
        return this.estudianteRepo.seleccionarTodos();
    }

}
