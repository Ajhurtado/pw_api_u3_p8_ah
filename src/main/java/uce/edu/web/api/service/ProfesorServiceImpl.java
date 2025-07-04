package uce.edu.web.api.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.repository.IProfesorRepo;
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.to.ProfesorTo;

@ApplicationScoped
public class ProfesorServiceImpl implements IProfesorService{

    @Inject
    private IProfesorRepo iProfesorRepo;

    @Override
    public ProfesorTo buscarPorId(Integer id, UriInfo uriInfo) {
        Profesor p1 = this.iProfesorRepo.selecionarPorId(id);
        ProfesorTo pr = new ProfesorTo(p1.getId(), p1.getNombre(), p1.getApellido(), 
            p1.getFechaNacimiento(), p1.getNumeroCedula(), p1.getGenero(), uriInfo);
        return pr;
    }

    @Override
    public List<Profesor> buscarTodos(String genero) {
        return this.iProfesorRepo.selecionarTodos(genero);
    }

    @Override
    public void actualizarPorId(Profesor profesor) {
        this.iProfesorRepo.actualizarPorId(profesor);
    }

    @Override
    public void actualizarParcialPorId(Profesor profesor) {
        this.iProfesorRepo.actualizarParcialPorId(profesor);
    }

    @Override
    public void borrarPorId(Integer id) {
        this.iProfesorRepo.borrarPorId(id);
    }

    @Override
    public void guardar(Profesor profesor) {
        this.iProfesorRepo.insertar(profesor);
    }
    
}
