package uce.edu.web.api.controller;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.service.IEstudianteService;

@Path("/estudiantes")
public class EstudianteController {

    @Inject
    private IEstudianteService estudianteService;

    @GET
    @Path("/consultar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Consultar estudiante por ID",
            description = "Esta capacidad permite consultar estudiante por su identificador"
    )
    public Response consultarPorId(@PathParam("id") Integer id) {
        return Response.status(227).entity(this.estudianteService.buscarPorID(id)).build();
    }

    //Se filtra por:
    //?genero=F&provincia=pichincha
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Consultar todos los estudiante",
            description = "Esta capacidad permite consultar todos los estudiante"
    )
    public Response consultarTodos(@QueryParam("genero") String genero, @QueryParam("provincia") String provincia) {
        System.out.println(provincia);
        return Response.status(Response.Status.OK).entity(this.estudianteService.buscarTodos(genero)).build();
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Guardar estudiante",
            description = "Esta capacidad permite guardar un estudiante"
    )
    public Response  guardar(@RequestBody Estudiante estudiante) {
        this.estudianteService.guardar(estudiante);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Actualizar  estudiante por ID",
            description = "Esta capacidad permite actualizar estudiante por ID"
    )
    public Response  actualizarPorId(@RequestBody Estudiante estudiante, @PathParam("id") Integer id) {
        estudiante.setId(id);
        this.estudianteService.actualizarPorId(estudiante);
        return Response.noContent().build();
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Actualizar  estudiante",
            description = "Esta capacidad permite actualizar estudiante"
    )
    public Response actualizarParcialPorId(@RequestBody Estudiante estudiante, @PathParam("id") Integer id) {

        estudiante.setId(id);
        Estudiante e = this.estudianteService.buscarPorID(id);
        if (estudiante.getApellido() != null) e.setApellido(estudiante.getApellido());
        if (estudiante.getNombre() != null) e.setNombre(estudiante.getNombre());
        if (estudiante.getFechaNacimiento() != null) e.setFechaNacimiento(estudiante.getFechaNacimiento());
        this.estudianteService.actualizarPorId(e);
        return Response.ok(e).build();

    }

    @DELETE
    @Path("/{id}")
    @Operation(
            summary = "Eliminar  estudiante",
            description = "Esta capacidad permite eliminar estudiante"
    )
    public Response borrarPorId(@PathParam("id") Integer id) {
        this.estudianteService.borrarPorID(id);
        return Response.noContent().build();
    }
}
