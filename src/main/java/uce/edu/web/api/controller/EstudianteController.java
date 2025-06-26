package uce.edu.web.api.controller;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.service.IEstudianteService;

@Path("/estudiantes")
public class EstudianteController {
    @Inject
    private IEstudianteService estudianteService;
    @GET
    @Path("/consultar/{id}")
    public Estudiante consultarPorId(@PathParam("id") Integer id){
        return this.estudianteService.buscarPorID(id);
    }

    @GET
    @Path("")
    public List<Estudiante> consultarTodos(){  
        return this.estudianteService.buscarTodos();
    }
    @POST
    @Path("")
    public void guardar(@RequestBody Estudiante estudiante){

    }
    @PUT
    @Path("/{id}")
    public void actualizar(@RequestBody Estudiante estudiante,@PathParam("id") Integer id){

    }

    @PATCH
    @Path("/{id}")
    public void actualizarParcialPorId(@RequestBody Estudiante estudiante,@PathParam("id") Integer id){

    }
    @DELETE
    @Path("/{id}")
    public void borrarPorId(@PathParam("id") Integer id){

    }
}
