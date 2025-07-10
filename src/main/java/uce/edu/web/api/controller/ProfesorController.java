package uce.edu.web.api.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

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
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.IProfesorService;
import uce.edu.web.api.service.mapper.ProfesorMapper;
import uce.edu.web.api.service.to.ProfesorTo;

@Path("/profesores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfesorController{

    @Inject
    private IProfesorService profesorService;

    @GET
    @Path("/{id}")
    @Operation(
            summary = "Consultar profesor por ID",
            description = "Esta capacidad permite consultar profesor por su identificador"
    )
    public Response consultarPorId(@PathParam("id") Integer id,@Context UriInfo uriInfo) {
        ProfesorTo prof =ProfesorMapper.toTo(this.profesorService.buscarPorId(id));
        prof.buildURI(uriInfo);
        return Response.status(227).entity(prof).build();
    }

    @GET
    @Path("")
    @Operation(
            summary = "Consultar todos los profesor",
            description = "Esta capacidad permite consultar todos los profesores"
    )
    public Response consultarTodos(@Context UriInfo uriInfo) {
        List<Profesor> profesores = this.profesorService.consultarTodos();
        List<ProfesorTo> profesoresTo = new ArrayList<>();
        
        for (Profesor profesor : profesores) {
            ProfesorTo profesorTo = ProfesorMapper.toTo(profesor);
            profesorTo.buildURI(uriInfo);
            profesoresTo.add(profesorTo);
        }
        
        return Response.ok(profesoresTo).build();
    }

    @POST
    @Operation(
            summary = "Guardar profesor",
            description = "Esta capacidad permite guardar un nuevo profesor"
    )
    public Response guardar(@RequestBody Profesor profesor, @Context UriInfo uriInfo) {
        this.profesorService.guardar(profesor);
        
        URI createdUri = uriInfo.getBaseUriBuilder()
                .path(ProfesorController.class)
                .path(ProfesorController.class, "consultarPorId")
                .build(profesor.getId());

        return Response.created(createdUri)
                       .entity(ProfesorMapper.toTo(profesor))
                       .build();
    }

    @PUT
    @Path("/{id}")
    @Operation(
            summary = "Actualizar profesor completo por ID",
            description = "Esta capacidad permite actualizar todos los campos de un profesor"
    )
    public Response actualizarPorId(@RequestBody Profesor profesor, @PathParam("id") Integer id) {
        profesor.setId(id);
        this.profesorService.actualizarPorId(profesor);
        return Response.noContent().build();
    }

    @PATCH
    @Path("/{id}")
    @Operation(
            summary = "Actualización parcial de profesor",
            description = "Esta capacidad permite actualizar campos específicos de un profesor"
    )
    public Response actualizarParcialPorId(
            @RequestBody Profesor profesor, 
            @PathParam("id") Integer id,
            @Context UriInfo uriInfo) {
        
        Profesor p = this.profesorService.buscarPorId(id);
        
        if (profesor.getNombre() != null) p.setNombre(profesor.getNombre());
        if (profesor.getApellido() != null) p.setApellido(profesor.getApellido());
        if (profesor.getEmail() != null) p.setEmail(profesor.getEmail());
        if (profesor.getTitulo() != null) p.setTitulo(profesor.getTitulo());
        
        this.profesorService.actualizarPorId(p);
        
        ProfesorTo updatedProfesorTo = ProfesorMapper.toTo(p);
        updatedProfesorTo.buildURI(uriInfo);
        
        return Response.ok(updatedProfesorTo).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(
            summary = "Eliminar profesor",
            description = "Esta capacidad permite eliminar un profesor por su ID"
    )
    public Response borrarPorId(@PathParam("id") Integer id) {
        this.profesorService.borrarporId(id);
        return Response.noContent().build();
    }

}
