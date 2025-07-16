package uce.edu.web.api.service.to;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import jakarta.ws.rs.core.UriInfo;

public class EstudianteTo {

    private Integer id;
    private String nombre;
    private String apellido;
    private LocalDateTime fechaNacimiento;
    private String genero;
    public Map<String, String> _links = new HashMap<>();

    //SET & GET
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDateTime getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Map<String, String> get_links() {
        return _links;
    }

    public void set_links(Map<String, String> _links) {
        this._links = _links;
    }

    public void buildURI(UriInfo uriInfo) {
        // Enlace para crear un nuevo estudiante (POST)
        URI createUri = uriInfo.getBaseUriBuilder()
                .path("estudiantes")
                .build();
        _links.put("crear", createUri.toString());

        // Enlace para la colección de hijos de este estudiante
        URI hijosUri = uriInfo.getBaseUriBuilder()
                .path("estudiantes")
                .path(this.id.toString())
                .path("hijos")
                .build();
        _links.put("hijos", hijosUri.toString());

        // Enlace para actualizar este recurso (PUT)
        URI updateUri = uriInfo.getBaseUriBuilder()
                .path("estudiantes")
                .path(this.id.toString())
                .build();
        _links.put("actualizar", updateUri.toString());

        // Enlace para eliminar este recurso (DELETE)
        URI deleteUri = uriInfo.getBaseUriBuilder()
                .path("estudiantes")
                .path(this.id.toString())
                .build();
        _links.put("eliminar", deleteUri.toString());

        // Enlace para la colección completa de estudiantes
        URI allEstudiantesUri = uriInfo.getBaseUriBuilder()
                .path("estudiantes")
                .build();
        _links.put("todos", allEstudiantesUri.toString());
    } 

}
