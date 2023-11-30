package com.cibertec.proyecto.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cibertec.proyecto.domain.ZonaEvento;

@Repository
public interface ZonaEventoDao extends JpaRepository<ZonaEvento, Integer> {
    
	@Query(value = "{call agregarZona(:idevento, :zona, :precio, :stock)}", nativeQuery = true)
    public void saveZona(@Param("idevento") int idevento, @Param("zona") String zona,
            @Param("precio") double precio, @Param("stock") int stock);

    @Query("SELECT SUM(z.stock) FROM ZonaEvento z "
            + " WHERE z.evento.id_evento LIKE ?1")
    public String traerAforo(int id);

    @Query("SELECT z FROM ZonaEvento z WHERE z.evento.nombre LIKE %?1% OR z.zona LIKE %?1%")
    public List<ZonaEvento> findAll(String zona);

    @Query("SELECT z FROM ZonaEvento z "
            + " WHERE z.evento.id_evento LIKE ?1")
    public List<ZonaEvento> findAllById_evento(int id_evento);

    @Query("SELECT z FROM ZonaEvento z "
            + " WHERE z.evento.id_evento LIKE ?1 AND z.zona = ?2")
    public ZonaEvento findbyIdeventoZona(int id_evento, String zona);
	
}
