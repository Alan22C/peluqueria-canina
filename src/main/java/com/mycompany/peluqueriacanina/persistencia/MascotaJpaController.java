package com.mycompany.peluqueriacanina.persistencia;

import java.io.Serializable;
import com.mycompany.peluqueriacanina.logica.Mascota;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class MascotaJpaController implements Serializable {
    
    public MascotaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
     private EntityManagerFactory emf = null;

    public MascotaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("PeluCaninaPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void crearMascota(Mascota mascota) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(mascota);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void editarMascota(Mascota mascota) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(mascota);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void eliminarMascota(int id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Mascota mascota = em.find(Mascota.class, id);
            if (mascota != null) {
                em.remove(mascota);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
        Mascota findMascota(int num_cliente) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mascota.class, num_cliente);
        } finally {
            em.close();
        }
    }

    /*public Mascota buscarMascota(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mascota.class, id);
        } finally {
            em.close();
        }
    }*/

    public List<Mascota> listarMascotas() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT m FROM Mascota m", Mascota.class).getResultList();
        } finally {
            em.close();
        }
    }

    List<Mascota> findMascotaEntities() {
        return listarMascotas();
    }

    void destroy(int num_cliente) {
        eliminarMascota(num_cliente);
    }
}
