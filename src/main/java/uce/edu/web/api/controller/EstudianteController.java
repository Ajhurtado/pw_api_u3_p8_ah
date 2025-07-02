package uce.edu.web.api.controller;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
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
    //PathParam es para consultar un recurso mediante un IDENFITICADOR
    @GET
    @Path("/{id}")
    public Estudiante consultarPorId(@PathParam("id")Integer id) {
        return this.estudianteService.buscarPorId(id); 
    }

    //En este colocamos un QueryParam, que es un parametro de consulta, que se coloca en la URL
    //url?genero=m
    //url?genero=F&provincia=pichincha
    @GET
    @Path("")
    @Operation(summary = "Consulta todos los estudiantes", description = "Esta CAPACIDAD permite retornar una lista de todos los estudiantes registrados")
    public List<Estudiante> consultarTodos(@QueryParam("genero") String genero, @QueryParam("provincia") String provincia) {
        System.out.println(provincia);
        return this.estudianteService.buscarTodos(genero); 
    }

    //El guardar recive un estudiante, el recurso a insertar debe ir en el BODY, el estudiante va a venir en el BODY
    @POST
    @Path("")
    public void guardar(@RequestBody Estudiante estudiante){
        this.estudianteService.guardar(estudiante);
    }
    
    //Debo enviar el estudiante que voy a actualizar, pero tambien necesita un PathParam
    @PUT
    @Path("/{id}")
    public void actualizarPorId(@RequestBody Estudiante estudiante, @PathParam("id") Integer id){
        estudiante.setId(id);
        this.estudianteService.actualizarPorId(estudiante);
    }

    @PATCH
    @Path("/{id}")
    public void actualizarParcialPorId(@RequestBody Estudiante estudiante, @PathParam("id") Integer id){
        estudiante.setId(id);
        Estudiante e = this.estudianteService.buscarPorId(id);
        if(estudiante.getApellido()!=null){
            e.setApellido(estudiante.getApellido());
        }
        this.estudianteService.actualizarParcialPorId(e);
    }

    //Aqui no necesitamos al estudiante, solamente al identificador
    @DELETE
    @Path("/{id}")
    public void borrarPorId(@PathParam("id") Integer id){
        this.estudianteService.borrarPorId(id);

    }

}
