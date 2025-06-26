package uce.edu.web.api.service;

import java.util.List;

import uce.edu.web.api.repository.modelo.Estudiante;

public interface IEstudianteService {
    //programamos el metodo
    public Estudiante buscarPorId(Integer id);

    public List<Estudiante> buscarTodos();
}
