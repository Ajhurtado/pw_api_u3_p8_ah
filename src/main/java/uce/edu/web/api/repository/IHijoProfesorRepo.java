package uce.edu.web.api.repository;

import java.util.List;

import uce.edu.web.api.repository.modelo.HijoProfesor;

public interface IHijoProfesorRepo {

    public List<HijoProfesor> buscarPorProfesorId(Integer id);
    
}
