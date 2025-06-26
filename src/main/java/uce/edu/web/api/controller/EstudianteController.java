package uce.edu.web.api.controller;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.service.IEstudianteService;

//tambien suele llamarse RECURSOS
@Path("/estudiantes")
public class EstudianteController {
    //por cada objeto se crea su respectivo, repositori, modelo, service y controller
    //este controler y tiene y representa a la identidad estudainte
    //cada metodo tiene un path

    @Inject
    private IEstudianteService estudianteService;

    //Se las conoce como CAPACIDADES, a estos metodos, como ya aplicamos en el application.properties, quitamos por ahora
    //el consultarPorID
    @GET
    @Path("/{id}")
    public Estudiante consultarPorId(@PathParam("id")Integer id) {
        return this.estudianteService.buscarPorId(id); 
    }

    @GET
    @Path("")
    public List<Estudiante> consultarTodos() {
        return this.estudianteService.buscarTodos(); 
    }

    //El guardar recive un estudiante, el recurso a insertar debe ir en el BODY, el estudiante va a venir en el BODY
    @POST
    @Path("")
    public void guardar(@RequestBody Estudiante estudiante){
        
    }
    
    //Debo enviar el estudiante que voy a actualizar, pero tambien necesita un PathParam
    @PUT
    @Path("/{id}")
    public void actualizarPorId(@RequestBody Estudiante estudiante, @PathParam("id") Integer id){

    }

    @PATCH
    @Path("/{id}")
    public void actualizarParcialPorId(@RequestBody Estudiante estudiante, @PathParam("id") Integer id){

    }

    //Aqui no necesitamos al estudiante, solamente al identificador
    @DELETE
    @Path("/{id}")
    public void borrarPorId(@PathParam("id") Integer id){

    }

}
