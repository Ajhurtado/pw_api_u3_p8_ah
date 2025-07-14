package uce.edu.web.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
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
import uce.edu.web.api.repository.modelo.HijoProfesor;
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.IHijoProfesorService;
import uce.edu.web.api.service.IProfesorService;
import uce.edu.web.api.service.mapper.ProfesorMapper;
import uce.edu.web.api.service.to.ProfesorTo;

@Path("/profesores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfesorController {
    
    @Inject
    private IProfesorService iProfesorService;

    @Inject
    private IHijoProfesorService iHijoProfesorService;

    //Ya quitamos la parte de consultarPor/, ya que ahora estamos en el Nivel 1
    @GET
    @Path("/{id}")
    public Response consultarPorId(@PathParam("id") Integer id,@Context UriInfo uriInfo) {
        ProfesorTo profe = ProfesorMapper.toTo(this.iProfesorService.buscarPorId(id));
        profe.buildURI(uriInfo);

        return Response.status(228).entity(profe).build();

    }

    @GET
    @Path("")
    public Response consultarTodos(@QueryParam("genero") String genero, @QueryParam("provincia") String provincia,@Context UriInfo uriInfo){
        System.out.println(provincia);
        List<Profesor> profesores = this.iProfesorService.buscarTodos(genero);
        List<ProfesorTo> profEstudianteTos = new ArrayList<>();
        for (Profesor prof : profesores) {
            ProfesorTo profeTo = ProfesorMapper.toTo(prof);
            profeTo.buildURI(uriInfo);
            profEstudianteTos.add(profeTo);
        }
        return Response.status(Response.Status.OK).entity(this.iProfesorService.buscarTodos(genero)).build();

    }

    @POST
    @Path("")
    public Response guardar(@RequestBody Profesor profesor, @Context UriInfo uriInfo){
        this.iProfesorService.guardar(profesor);
        ProfesorTo profesorTo = ProfesorMapper.toTo(profesor);
        profesorTo.buildURI(uriInfo);
        return Response.status(228).entity(profesor).build();
        //este metodo guarda un profesor, el cual viene en el BODY
    }

    @PUT
    @Path("/{id}")
    public Response actualizarPorId(@RequestBody Profesor profesor, @PathParam("id") Integer id, @Context UriInfo uriInfo) {
        profesor.setId(id);
        this.iProfesorService.actualizarPorId(profesor);
        ProfesorTo profesorActualizar = ProfesorMapper.toTo(profesor);
        profesorActualizar.buildURI(uriInfo);
        return Response.status(228).entity(profesorActualizar).build();
    }

    /* @PATCH
    @Path("/{id}")
    public Response actualizarParcialPorId(@RequestBody Profesor profesor, @PathParam("id") Integer id){
        profesor.setId(id);
        Profesor p = this.iProfesorService.buscarPorId(id);
        if(profesor.getApellido()!=null){
            p.setApellido(profesor.getApellido());
        }
        this.iProfesorService.actualizarParcialPorId(p);
        return Response.status(228).entity(p).build();
    } */

    @DELETE
    @Path("/{id}")
    public Response borrarPorId( @PathParam("id") Integer id){
        this.iProfesorService.borrarPorId(id);
        return Response.status(228).build();
    }

    @GET
    @Path("/{id}/hijos")
    public List<HijoProfesor> obtenerHijosPorId(@PathParam("id") Integer id) {
        return this.iHijoProfesorService.buscarPorProfesorId(id);
    }

}
