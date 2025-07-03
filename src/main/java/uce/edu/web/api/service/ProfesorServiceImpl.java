package uce.edu.web.api.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.repository.IProfesorRepo;
import uce.edu.web.api.repository.modelo.Profesor;

@ApplicationScoped
public class ProfesorServiceImpl implements IProfesorService{

    @Inject
    private IProfesorRepo iProfesorRepo;

    @Override
    public Profesor buscarPorId(Integer id) {
        return this.iProfesorRepo.selecionarPorId(id);
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
