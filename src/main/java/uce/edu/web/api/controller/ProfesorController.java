package uce.edu.web.api.controller;

import java.util.List;

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
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.IProfesorService;

@Path("/profesores")
public class ProfesorController {
    
    @Inject
    private IProfesorService iProfesorService;

    //Ya quitamos la parte de consultarPor/, ya que ahora estamos en el Nivel 1
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response consultarPorId(@PathParam("id") Integer id){
        return Response.status(228).entity(this.iProfesorService.buscarPorId(id)).build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public Response consultarTodos(@QueryParam("genero") String genero, @QueryParam("provincia") String provincia){
        System.out.println(provincia);
        return Response.status(Response.Status.OK).entity(this.iProfesorService.buscarTodos(genero)).build();

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public Response guardar(@RequestBody Profesor profesor){
        this.iProfesorService.guardar(profesor);
        return Response.status(228).entity(profesor).build();
        //este metodo guarda un profesor, el cual viene en el BODY
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response actualizarPorId(@RequestBody Profesor profesor, @PathParam("id") Integer id){
        profesor.setId(id);
        this.iProfesorService.actualizarPorId(profesor);
        return Response.status(228).entity(profesor).build();
    }

    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response actualizarParcialPorId(@RequestBody Profesor profesor, @PathParam("id") Integer id){
        profesor.setId(id);
        Profesor p = this.iProfesorService.buscarPorId(id);
        if(profesor.getApellido()!=null){
            p.setApellido(profesor.getApellido());
        }
        this.iProfesorService.actualizarParcialPorId(p);
        return Response.status(228).entity(p).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response borrarPorId( @PathParam("id") Integer id){
        this.iProfesorService.borrarPorId(id);
        return Response.status(228).build();
    }


}
