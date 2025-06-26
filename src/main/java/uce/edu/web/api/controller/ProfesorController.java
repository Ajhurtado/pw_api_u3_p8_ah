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
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.IProfesorService;

@Path("/profesores")
public class ProfesorController {
    
    @Inject
    private IProfesorService iProfesorService;

    //Ya quitamos la parte de consultarPor/, ya que ahora estamos en el Nivel 1
    @GET
    @Path("/{id}")
    public Profesor consultarPorId(@PathParam("id") Integer id){
        return this.iProfesorService.buscarPorId(id);
    }

    @GET
    @Path("")
    public List<Profesor> consultarTodos(){
        return this.iProfesorService.buscarTodos();

    }

    @POST
    @Path("")
    public void guardar(@RequestBody Profesor profesor){

    }

    @PUT
    @Path("")
    public void actualizarPorId(@RequestBody Profesor profesor, @PathParam("id") Integer id){

    }

    @PATCH
    @Path("")
    public void actualizarParcialPorId(@RequestBody Profesor profesor, @PathParam("id") Integer id){

    }

    @DELETE
    @Path("")
    public void borrarPorId( @PathParam("id") Integer id){

    }


}
