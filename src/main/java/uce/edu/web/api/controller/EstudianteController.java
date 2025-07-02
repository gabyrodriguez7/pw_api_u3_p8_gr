package uce.edu.web.api.controller;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.service.IEstudianteService;

@Path("/estudiantes")
public class EstudianteController {

    @Inject
    private IEstudianteService estudianteService;

    @GET
    @Path("/consultar/{id}")
    public Estudiante consultarPorId(@PathParam("id") Integer id) {
        return this.estudianteService.buscarPorID(id);
    }

    //Se filtra por:
    //?genero=F&provincia=pichincha
    @GET
    @Path("")
        @Operation(
        summary = "Consultar estudiante",
        description= "Esta capacidad permite consultar estudiante"
    )
    public List<Estudiante> consultarTodos(@QueryParam("genero") String genero,@QueryParam("provincia") String provincia) {
        System.out.println(provincia);
        return this.estudianteService.buscarTodos(genero);
    }

    @POST
    @Path("")
    @Operation(
        summary = "Guardar estudiante",
        description= "Esta capacidad permite guardar un estudiante"
    )
    public void guardar(@RequestBody Estudiante estudiante) {
        this.estudianteService.guardar(estudiante);
    }

    @PUT
    @Path("/{id}")
    public void actualizarPorId(@RequestBody Estudiante estudiante, @PathParam("id") Integer id) {
        estudiante.setId(id);
        this.estudianteService.actualizarPorId(estudiante);
    }

    @PATCH
    @Path("/{id}")
    public void actualizarParcialPorId(@RequestBody Estudiante estudiante, @PathParam("id") Integer id) {

        estudiante.setId(id);
        Estudiante e = this.estudianteService.buscarPorID(id);
        if (estudiante.getApellido() != null) {
            e.setApellido(estudiante.getApellido());
        } else if (estudiante.getNombre() != null) {
            e.setNombre(estudiante.getNombre());
        } else if (estudiante.getFechaNacimiento() != null) {
            e.setFechaNacimiento(estudiante.getFechaNacimiento());
        }
        this.estudianteService.actualizarPorId(e);

    }

    @DELETE
    @Path("/{id}")
    public void borrarPorId(@PathParam("id") Integer id) {
        this.estudianteService.borrarPorID(id);
    }
}
