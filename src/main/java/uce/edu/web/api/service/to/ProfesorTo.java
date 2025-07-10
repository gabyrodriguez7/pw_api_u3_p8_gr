package uce.edu.web.api.service.to;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.controller.ProfesorController;

public class ProfesorTo {

    private Integer id;
    private String nombre;
    private String apellido;
    private String email;
    private String titulo;
    private Map<String, String> _links = new HashMap<>();

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Map<String, String> getLinks() {
        return _links;
    }

    public void setLinks(Map<String, String> _links) {
        this._links = _links;
    }

   public void buildURI(UriInfo uriInfo) {
    // Enlace para obtener este profesor (GET)
    URI selfUri = uriInfo.getBaseUriBuilder()
            .path(ProfesorController.class)
            .path(ProfesorController.class, "consultarPorId")
            .build(this.id);
    _links.put("profesor", selfUri.toString());

    // Enlace para crear un nuevo profesor (POST)
    URI createUri = uriInfo.getBaseUriBuilder()
            .path(ProfesorController.class)
            .build();
    _links.put("crear", createUri.toString());

    // Enlace para actualizar este profesor (PUT)
    URI updateUri = uriInfo.getBaseUriBuilder()
            .path(ProfesorController.class)
            .path(ProfesorController.class, "consultarPorId")
            .build(this.id);
    _links.put("actualizar", updateUri.toString());

    // Enlace para eliminar este profesor (DELETE)
    URI deleteUri = uriInfo.getBaseUriBuilder()
            .path(ProfesorController.class)
            .path(ProfesorController.class, "borrarPorId")
            .build(this.id);
    _links.put("eliminar", deleteUri.toString());

    // Enlace para obtener todos los profesores (GET)
    URI allProfesoresUri = uriInfo.getBaseUriBuilder()
            .path(ProfesorController.class)
            .build();
    _links.put("todos", allProfesoresUri.toString());
}
}
