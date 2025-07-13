package uce.edu.web.api.service.to;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.controller.EstudianteController;

public class EstudianteTo {
    
    private Integer id;
    private String nombre;
    private String apellido;
    private LocalDateTime fechaNacimiento;
    private String genero;
    //para los links de hijos que va a tener el estudiante
    //cambiamos de public a private para generar los getters y setters
    private Map<String, String> _links = new HashMap<>();
  
    public void buildURI(UriInfo uriInfo){
        //como poner las URLS de los links
        //base uri nos da toda la base de la URL
        URI todosHijos = uriInfo.getBaseUriBuilder()
            .path(EstudianteController.class)
            .path(EstudianteController.class, "obtenerHijosPorId")
            .build(id);

        _links.put("hijos", todosHijos.toString());

        URI consultarId = uriInfo.getBaseUriBuilder()
            .path(EstudianteController.class)
            .path(EstudianteController.class, "consultarPorId")
            .build(id);

        _links.put("obtenerPorId", consultarId.toString());

        URI consultarTodos = uriInfo.getBaseUriBuilder()
            .path(EstudianteController.class)
            .path(EstudianteController.class, "consultarTodos")
            .build();
        _links.put("obtenerTodos", consultarTodos.toString());

        URI actualizarId = uriInfo.getBaseUriBuilder()
            .path(EstudianteController.class)
            .path(EstudianteController.class, "actualizarPorId")
            .build(id);
        _links.put("actualizarPorId", actualizarId.toString());

        URI borrarId = uriInfo.getBaseUriBuilder()
            .path(EstudianteController.class)
            .path(EstudianteController.class, "borrarPorId")
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


      //Ya eliminamos los constructores
    /*public EstudianteTo() {
       
    }*/

    //si no ponemos el contrusctor este APARECE POR DEFECTO
    /*  public EstudianteTo(Integer id, String nombre, String apellido, LocalDateTime fechaNacimiento, String genero, UriInfo uriInfo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;

        //como poner las URLS de los links
        //base uri nos da toda la base de la URL
        URI todosHijos = uriInfo.getBaseUriBuilder()
            .path(EstudianteController.class)
            .path(EstudianteController.class, "obtenerHijosPorId")
            .build(id);

        _links.put("hijos", todosHijos.toString());    
    }*/
}
