package com.mycompany.peluqueriacanina.persistencia;

import com.mycompany.peluqueriacanina.logica.Duenio;
import com.mycompany.peluqueriacanina.logica.Mascota;
import com.mycompany.peluqueriacanina.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ControladoraPersistencia {
 
    DuenioJpaController duenioJpa = new DuenioJpaController();
    MascotaJpaController mascotaJpa = new MascotaJpaController();

    public void guardar(Duenio duenio, Mascota masco) {
        
        duenioJpa.crearDuenio(duenio);
        
        mascotaJpa.crearMascota(masco);
    }

    public List<Mascota> traerMascotas() {
        
        return mascotaJpa.findMascotaEntities();
    }

    public void borrarMascota(int num_cliente) {
        mascotaJpa.destroy(num_cliente);
        
    }

    public Mascota traerMascota(int num_cliente) {
        return mascotaJpa.findMascota(num_cliente);
    }

    public void modificarMascota(Mascota masco) {
        mascotaJpa.editarMascota(masco);
    }

    public Duenio traerDuenio(int id_duenio) {
        return duenioJpa.buscarDuenio(id_duenio);
    }

    public void modificarDuenio(Duenio dueno) {
        duenioJpa.editarDuenio(dueno);
    }
}
