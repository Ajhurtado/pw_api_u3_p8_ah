package uce.edu.web.api.controller;

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
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.repository.modelo.Hijo;
import uce.edu.web.api.service.HijoServiceImpl;
import uce.edu.web.api.service.IEstudianteService;
import uce.edu.web.api.service.IHijoService;
import uce.edu.web.api.service.mapper.EstudianteMapper;
import uce.edu.web.api.service.to.EstudianteTo;

//tambien suele llamarse RECURSOS
@Path("/estudiantes")
@Produces(MediaType.APPLICATION_JSON) 
@Consumes(MediaType.APPLICATION_JSON)
public class EstudianteController {
    //por cada objeto se crea su respectivo, repositori, modelo, service y controller
    //este controler y tiene y representa a la identidad estudainte
    //cada metodo tiene un path

    @Inject
    private IEstudianteService estudianteService;
    @Inject
    private IHijoService hijoService;

    //Se las conoce como CAPACIDADES, a estos metodos, como ya aplicamos en el application.properties, quitamos por ahora
    //el consultarPorID
    //PathParam es para consultar un recurso mediante un IDENFITICADOR
    //Para que me envie la propia api ponemos la anotacion Context
    @GET
    @Path("/{id}")
    public Response consultarPorId(@PathParam("id")Integer id, @Context UriInfo uriInfo) {
        //Response es una clase que nos permite retornar un codigo de estado, un mensaje y el objeto
        
        EstudianteTo estu =  EstudianteMapper.toTo(this.estudianteService.buscarPorId(id)); 
        estu.buildURI(uriInfo);
        
        return Response.status(227).entity(estu).build();  
    }

    //En este colocamos un QueryParam, que es un parametro de consulta, que se coloca en la URL
    //url?genero=m
    //url?genero=F&provincia=pichincha
    //Como se malogro el body, con el Response, aqui viene el MediaType, que es el tipo de contenido que se va a retornar
    //Cuando respondo un objeto usamos Produces jakarta.ws
    @GET
    @Path("")
    @Operation(summary = "Consulta todos los estudiantes", description = "Esta CAPACIDAD permite retornar una lista de todos los estudiantes registrados")
    public Response consultarTodos(@QueryParam("genero") String genero, @QueryParam("provincia") String provincia) {
        System.out.println(provincia);
        return Response.status(Response.Status.OK).entity(this.estudianteService.buscarTodos(genero)).build();
    }

    //El guardar recive un estudiante, el recurso a insertar debe ir en el BODY, el estudiante va a venir en el BODY
    //Especfico en que va a llegar, en este caso MediaType de Jackarta, que es el tipo de contenido que se va a enviar
    @POST
    @Path("")

    public Response guardar(@RequestBody Estudiante estudiante){
        this.estudianteService.guardar(estudiante);
       return Response.status(227).entity(estudiante).build();
    }
    
    //Debo enviar el estudiante que voy a actualizar, pero tambien necesita un PathParam
    @PUT
    @Path("/{id}")
    public Response actualizarPorId(@RequestBody Estudiante estudiante, @PathParam("id") Integer id){
        estudiante.setId(id);
        this.estudianteService.actualizarPorId(estudiante);
        return Response.status(Response.Status.OK).entity(estudiante).build(); 
    }

 /*    @PATCH
    @Path("/{id}")
    public Response actualizarParcialPorId(@RequestBody Estudiante estudiante, @PathParam("id") Integer id){
        estudiante.setId(id);
        Estudiante e = this.estudianteService.buscarPorId(id);
        if(estudiante.getApellido()!=null){
            e.setApellido(estudiante.getApellido());
        }
        this.estudianteService.actualizarParcialPorId(e);
        return Response.status(Response.Status.OK).entity(e).build();
    } */

    //Aqui no necesitamos al estudiante, solamente al identificador
    @DELETE
    @Path("/{id}")
    public Response borrarPorId(@PathParam("id") Integer id){
        this.estudianteService.borrarPorId(id);
        return Response.status(227).build(); 

    }

    //htttp://........./estudiantes/{id}/hijos GET
    @GET
    //path autodescripcitvo
    @Path("/{id}/hijos")
    public List<Hijo> obtenerHijosPorId(@PathParam("id") Integer id) {
    // Este método debería retornar una lista de hijos del estudiante con el ID proporcionado
        return this.hijoService.buscarPorEstudianteId(id);
    }

}
