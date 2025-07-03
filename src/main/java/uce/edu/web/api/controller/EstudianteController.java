package uce.edu.web.api.controller;

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
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
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
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response consultarPorId(@PathParam("id")Integer id) {
        //Response es una clase que nos permite retornar un codigo de estado, un mensaje y el objeto
        return Response.status(227).entity(this.estudianteService.buscarPorId(id)).build();  
    }

    //En este colocamos un QueryParam, que es un parametro de consulta, que se coloca en la URL
    //url?genero=m
    //url?genero=F&provincia=pichincha
    //Como se malogro el body, con el Response, aqui viene el MediaType, que es el tipo de contenido que se va a retornar
    //Cuando respondo un objeto usamos Produces jakarta.ws
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Consulta todos los estudiantes", description = "Esta CAPACIDAD permite retornar una lista de todos los estudiantes registrados")
    public Response consultarTodos(@QueryParam("genero") String genero, @QueryParam("provincia") String provincia) {
        System.out.println(provincia);
        return Response.status(Response.Status.OK).entity(this.estudianteService.buscarTodos(genero)).build();
    }

    //El guardar recive un estudiante, el recurso a insertar debe ir en el BODY, el estudiante va a venir en el BODY
    //Especfico en que va a llegar, en este caso MediaType de Jackarta, que es el tipo de contenido que se va a enviar
    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardar(@RequestBody Estudiante estudiante){
        this.estudianteService.guardar(estudiante);
       return Response.status(227).entity(estudiante).build();
    }
    
    //Debo enviar el estudiante que voy a actualizar, pero tambien necesita un PathParam
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarPorId(@RequestBody Estudiante estudiante, @PathParam("id") Integer id){
        estudiante.setId(id);
        this.estudianteService.actualizarPorId(estudiante);
        return Response.status(Response.Status.OK).entity(estudiante).build(); 
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarParcialPorId(@RequestBody Estudiante estudiante, @PathParam("id") Integer id){
        estudiante.setId(id);
        Estudiante e = this.estudianteService.buscarPorId(id);
        if(estudiante.getApellido()!=null){
            e.setApellido(estudiante.getApellido());
        }
        this.estudianteService.actualizarParcialPorId(e);
        return Response.status(Response.Status.OK).entity(e).build();
    }

    //Aqui no necesitamos al estudiante, solamente al identificador
    @DELETE
    @Path("/{id}")
    public Response borrarPorId(@PathParam("id") Integer id){
        this.estudianteService.borrarPorId(id);
        return Response.status(227).build(); 

    }

}
