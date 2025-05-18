package com.mycompany.peluqueriacanina.persistencia;

import java.io.Serializable;
import com.mycompany.peluqueriacanina.logica.Duenio;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class DuenioJpaController implements Serializable {
    
    public DuenioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    private EntityManagerFactory emf = null;

    public DuenioJpaController() {
        this.emf = Persistence.createEntityManagerFactory("PeluCaninaPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void crearDuenio(Duenio duenio) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(duenio);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void editarDuenio(Duenio duenio) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(duenio);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void eliminarDuenio(int id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Duenio duenio = em.find(Duenio.class, id);
            if (duenio != null) {
                em.remove(duenio);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Duenio buscarDuenio(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Duenio.class, id);
        } finally {
            em.close();
        }
    }

    public List<Duenio> listarDuenios() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT d FROM Duenio d", Duenio.class).getResultList();
        } finally {
            em.close();
        }
    }
}
