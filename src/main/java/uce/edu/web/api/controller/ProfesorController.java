package uce.edu.web.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.repository.modelo.Hijo;
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.IProfesorService;
import uce.edu.web.api.service.to.ProfesorTo;

@Path("/profesores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfesorController extends BaseControlador{

    @Inject
    private IProfesorService profesorService;

    @GET
    @Path("/consultar/{id}")
    @Operation(
            summary = "Consultar profesor por ID",
            description = "Esta capacidad permite consultar profesor por su identificador"
    )
    public Response consultarPorId(@PathParam("id") Integer id,@Context UriInfo uriInfo) {
        ProfesorTo prof = this.profesorService.buscarPorId(id, uriInfo);
        return Response.status(227).entity(prof).build();
    }

    @GET
    @Path("")

    @Operation(
            summary = "Consultar todos los profesor",
            description = "Esta capacidad permite consultar todos los profesores"
    )
    public Response consultarTodos() {
        return Response.status(Response.Status.OK).entity( this.profesorService.consultarTodos()).build();
    }

    @POST
    @Path("")
    @Operation(
            summary = "Guardar profesor",
            description = "Esta capacidad permite guardar un profesor"
    )
    public Response guardar(@RequestBody Profesor profesor) {
        profesorService.guardar(profesor);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(
            summary = "Actualizar profesor por ID",
            description = "Esta capacidad permite actualizar profesor por ID"
    )
    public Response actualizarPorId(@RequestBody Profesor profesor, @PathParam("id") Integer id) {
        profesor.setId(id);
        profesorService.actualizarPorId(profesor);
        return Response.noContent().build();
    }

  /*   @PATCH
    @Path("/{id}")
    @Operation(
            summary = "Actualizar profesor",
            description = "Esta capacidad permite actualizar profesor"
    )
    public Response  actualizacionParcialPorId(@RequestBody Profesor profesor, @PathParam("id") Integer id) {
        Profesor p = this.profesorService.buscarPorId(id);
    if (profesor.getNombre() != null) {
        p.setNombre(profesor.getNombre());
    }
    if (profesor.getApellido() != null) {
        p.setApellido(profesor.getApellido());
    }
    if (profesor.getEmail() != null) {
        p.setEmail(profesor.getEmail());
    }
    if (profesor.getTitulo() != null) {
        p.setTitulo(profesor.getTitulo());
    }
    
    profesorService.actualizarPorId(p);
    return Response.ok(p).build();

    }*/

    @DELETE
    @Path("/{id}")
    @Operation(
            summary = "Eliminar profesor",
            description = "Esta capacidad permite eliminar profesor"
    )
    public Response  eliminar(@PathParam("id") Integer id) {
        this.profesorService.borrarporId(id);
        return Response.noContent().build();
    }
        //http://localhost:8081/api/matricula/v1/profesores/1/hijos
    @GET
    @Path("/{id}/hijos")
    public List<Hijo> obtenerHijosId(@PathParam("id") Integer id){
        Hijo h1 = new Hijo();
        h1.setNombre("pepito");
        Hijo h2 = new Hijo();
        h2.setNombre("juanito");

        List<Hijo> hijos = new ArrayList<>();
        hijos.add(h1);
        hijos.add(h2);
        return hijos;

    }
}
