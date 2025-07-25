package uce.edu.web.api.service;

import java.util.List;

import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.service.to.EstudianteTo;

public interface IEstudianteService {
    //programamos el metodo
    public Estudiante buscarPorId(Integer id);

    public List<Estudiante> buscarTodos(String genero);

    public void actualizarPorId(Estudiante estudiante);

    public void actualizarParcialPorId(Estudiante estudiante);

    public void borrarPorId(Integer id);
    
    public void guardar(Estudiante estudiante);
}
