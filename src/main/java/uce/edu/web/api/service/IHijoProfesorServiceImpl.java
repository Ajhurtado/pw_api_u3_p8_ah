package uce.edu.web.api.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.repository.IHijoProfesorRepo;
import uce.edu.web.api.repository.modelo.HijoProfesor;

@ApplicationScoped
public class IHijoProfesorServiceImpl implements IHijoProfesorService {

    @Inject
    private IHijoProfesorRepo iHijoProfesorRepo;

    @Override
    public List<HijoProfesor> buscarPorProfesorId(Integer id) {
        return this.iHijoProfesorRepo.buscarPorProfesorId(id);
    }
    
   
}
