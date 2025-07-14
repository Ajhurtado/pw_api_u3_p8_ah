package uce.edu.web.api.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.modelo.HijoProfesor;

@ApplicationScoped
@Transactional
public class IHijoProfesorRepoImpl implements IHijoProfesorRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<HijoProfesor> buscarPorProfesorId(Integer id) {
        TypedQuery<HijoProfesor> myQuery = this.entityManager
                .createQuery("SELECT hp FROM HijoProfesor hpr WHERE hp.profesor.id =:id", HijoProfesor.class);
        myQuery.setParameter("id", id);
        return myQuery.getResultList();
        }
 
}
