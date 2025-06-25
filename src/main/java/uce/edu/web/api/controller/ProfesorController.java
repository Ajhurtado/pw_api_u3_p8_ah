package uce.edu.web.api.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.IProfesorService;

@Path("/profesores")
public class ProfesorController {
    
    @Inject
    private IProfesorService iProfesorService;

    @GET
    @Path("/consultarPor/{id}")
    public Profesor consultarPorId(@PathParam("id") Integer id){
        return this.iProfesorService.buscarPorId(id);
    }
}
