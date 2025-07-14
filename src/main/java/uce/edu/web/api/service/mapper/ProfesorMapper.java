package uce.edu.web.api.service.mapper;

import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.to.ProfesorTo;

public class ProfesorMapper {
    
    public static ProfesorTo toTo(Profesor profesor) {
        ProfesorTo profesorTo = new ProfesorTo();
            profesorTo.setId(profesor.getId());
            profesorTo.setNombre(profesor.getNombre());
            profesorTo.setApellido(profesor.getApellido());
            profesorTo.setFechaNacimiento(profesor.getFechaNacimiento());
            profesorTo.setNumeroCedula(profesor.getNumeroCedula());
            profesorTo.setGenero(profesor.getGenero());
            
        return profesorTo;
    }

    public static Profesor toEntity(ProfesorTo profesorTo) {
        Profesor profesor = new Profesor();
            profesor.setId(profesorTo.getId());
            profesor.setNombre(profesorTo.getNombre());
            profesor.setApellido(profesorTo.getApellido());
            profesor.setFechaNacimiento(profesorTo.getFechaNacimiento());
            profesor.setNumeroCedula(profesorTo.getNumeroCedula());
            profesor.setGenero(profesorTo.getGenero());
            
        return profesor;
    }
}
