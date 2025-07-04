package uce.edu.web.api.service.to;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.controller.ProfesorController;


public class ProfesorTo {
    Integer id;
    String nombre;
    String apellido;
    String email;
    String titulo;
    public Map<String,String> _links = new HashMap<>();

    public ProfesorTo(String apellido, String email, Integer id, String nombre, String titulo, UriInfo uriInfo) {
        this.apellido = apellido;
        this.email = email;
        this.id = id;
        this.nombre = nombre;
        this.titulo = titulo;
        URI todosHijos = uriInfo.getBaseUriBuilder().path(ProfesorController.class).path(ProfesorController.class,"obtenerHijosId").build(id);
        _links.put("hijos", todosHijos.toString());
    }
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


}
