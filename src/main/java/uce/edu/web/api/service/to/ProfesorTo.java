package uce.edu.web.api.service.to;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.controller.ProfesorController;

public class ProfesorTo {
    private Integer id;
    private String nombre;
    private String apellido;
    private LocalDateTime fechaNacimiento;
    private String numeroCedula;
    private String genero;

    public Map<String, String> _links = new HashMap<>();
    
    public void buildURI(UriInfo uriInfo) {
        URI todosHijos = uriInfo.getBaseUriBuilder()
            .path(ProfesorController.class)
            .path(ProfesorController.class, "consultarPorId")
            .build(id);
        _links.put("hijos", todosHijos.toString());

        URI consultarId = uriInfo.getBaseUriBuilder()
            .path(ProfesorController.class)
            .path(ProfesorController.class, "consultarPorId")
            .build(id);
        _links.put("obtenerPorId", consultarId.toString());

        URI consultarTodos = uriInfo.getBaseUriBuilder()
            .path(ProfesorController.class)
            .path(ProfesorController.class, "consultarTodos")
            .build();
        _links.put("obtenerTodos", consultarTodos.toString());

        URI actualizarId = uriInfo.getBaseUriBuilder()
            .path(ProfesorController.class)
            .path(ProfesorController.class, "actualizarPorId")
            .build(id);
        _links.put("actualizarPorId", actualizarId.toString());

        URI borrarId = uriInfo.getBaseUriBuilder()
            .path(ProfesorController.class)
            .path(ProfesorController.class, "borrarPorId")
            .build(id);
        _links.put("borrar", borrarId.toString());
    }
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

    public String getNumeroCedula() {
        return numeroCedula;
    }

    public void setNumeroCedula(String numeroCedula) {
        this.numeroCedula = numeroCedula;
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
    
    
    

}
