package com.cibertec.proyecto.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cibertec.proyecto.domain.Evento;

@Repository
public interface EventoDao extends JpaRepository<Evento, Integer>{

	@Query("SELECT e FROM Evento e WHERE e.disponibilidad.id_disponibilidad = 1 AND e.estado.id_estado !=4 AND e.estado.id_estado !=3")
    public List<Evento> findAllDis();

    @Query("SELECT e FROM Evento e WHERE e.nombre LIKE %?1% AND e.disponibilidad.id_disponibilidad = 1 AND e.estado.id_estado !=4")
    public List<Evento> findAllPorNombre(String nombre);

    @Query("SELECT e FROM Evento e WHERE e.nombre LIKE %?1% "
            + " OR e.estado.nomestado LIKE %?1% "
            + " OR e.disponibilidad.nomdis LIKE %?1%")
    public List<Evento> findAll(String nombre);
   
  
}
