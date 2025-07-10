package uce.edu.web.api.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.repository.modelo.Hijo;
import uce.edu.web.api.service.IEstudianteService;
import uce.edu.web.api.service.IHijoService;
import uce.edu.web.api.service.mapper.EstudianteMapper;
import uce.edu.web.api.service.to.EstudianteTo;

@Path("/estudiantes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EstudianteController {

    @Inject
    private IEstudianteService estudianteService;

    @Inject
    private IHijoService hijoService;

    @GET
    @Path("/{id}")
    @Operation(
            summary = "Consultar estudiante por ID",
            description = "Esta capacidad permite consultar estudiante por su identificador"
    )
    public Response consultarPorId(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        EstudianteTo estu = EstudianteMapper.toTo(this.estudianteService.buscarPorID(id));
        estu.buildURI(uriInfo);
        return Response.status(227).entity(estu).build();
    }
    
    @GET
    @Path("")
    @Operation(
            summary = "Consultar todos los estudiante",
            description = "Esta capacidad permite consultar todos los estudiante"
    )
    public Response consultarTodos(@QueryParam("genero") String genero, @QueryParam("provincia") String provincia, @Context UriInfo uriInfo) {
        List<Estudiante> estudiantes = this.estudianteService.buscarTodos();
        List<EstudianteTo> estudiantesTo = estudiantes.stream()
                .map(estudiante -> {
                    EstudianteTo to = EstudianteMapper.toTo(estudiante);
                    to.buildURI(uriInfo);
                    return to;
                })
                .collect(Collectors.toList());

        return Response.status(Response.Status.OK).entity(estudiantesTo).build();
    }

    //Se filtra por:
    //?genero=F&provincia=pichincha solo si es tipo SOAP -> XML & RESTful ->JSON
    /*  @GET
    @Path("")
    @Operation(
            summary = "Consultar todos los estudiante",
            description = "Esta capacidad permite consultar todos los estudiante"
    )
    public Response consultarTodos2(@QueryParam("genero") String genero, @QueryParam("provincia") String provincia) {
        List<Estudiante> estudiantes = this.estudianteService.buscarTodos(genero);
        List<EstudianteTo> estudiantesTo = estudiantes.stream()
                .map(estudiante -> {
                    EstudianteTo to = EstudianteMapper.toTo(estudiante);
                    return to;
                })
                .collect(Collectors.toList());

        return Response.status(Response.Status.OK).entity(estudiantesTo).build();
    }*/
    @POST
    @Path("")
    @Operation(
            summary = "Guardar estudiante",
            description = "Esta capacidad permite guardar un estudiante"
    )
    public Response guardar(@RequestBody Estudiante estudiante, @Context UriInfo uriInfo) {
        this.estudianteService.guardar(estudiante);

        URI createdUri = uriInfo.getBaseUriBuilder()
                .path(EstudianteController.class)
                .path(EstudianteController.class, "consultarPorId")
                .build(estudiante.getId());

        return Response.created(createdUri)
                .entity(EstudianteMapper.toTo(estudiante))
                .build();
    }

    @PUT
    @Path("/{id}")
    @Operation(
            summary = "Actualizar  estudiante por ID",
            description = "Esta capacidad permite actualizar estudiante por ID"
    )
    public Response actualizarPorId(@RequestBody Estudiante estudiante, @PathParam("id") Integer id) {
        estudiante.setId(id);
        this.estudianteService.actualizarPorId(estudiante);
        return Response.noContent().build();
    }

    @PATCH
    @Path("/{id}")
    @Operation(
            summary = "Actualizar  estudiante",
            description = "Esta capacidad permite actualizar estudiante"
    )
    public Response actualizarParcialPorId(@RequestBody Estudiante estudiante, @PathParam("id") Integer id, @Context UriInfo uriInfo) {

        estudiante.setId(id);
        Estudiante e = this.estudianteService.buscarPorID(id);
        if (estudiante.getApellido() != null) {
            e.setApellido(estudiante.getApellido());
        }
        if (estudiante.getNombre() != null) {
            e.setNombre(estudiante.getNombre());
        }
        if (estudiante.getFechaNacimiento() != null) {
            e.setFechaNacimiento(estudiante.getFechaNacimiento());
        }
        this.estudianteService.actualizarPorId(e);
        EstudianteTo updatedEstudianteTo = EstudianteMapper.toTo(e);
        updatedEstudianteTo.buildURI(uriInfo);
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

    //http://localhost:8081/api/matricula/v1/estudiantes/1/hijos
    @GET
    @Path("/{id}/hijos")
    public List<Hijo> obtenerHijosId(@PathParam("id") Integer id) {

        return this.hijoService.buscarPorEstudianteId(id);
        /*Hijo h1 = new Hijo();
        h1.setNombre("pepito");
        Hijo h2 = new Hijo();
        h2.setNombre("juanito");

        List<Hijo> hijos = new ArrayList<>();
        hijos.add(h1);
        hijos.add(h2);
        return hijos;*/

    }
}
