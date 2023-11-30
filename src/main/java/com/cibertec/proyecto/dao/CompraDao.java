package com.cibertec.proyecto.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cibertec.proyecto.domain.Compra;

@Repository
public interface CompraDao extends JpaRepository<Compra, Integer>{

	@Query("SELECT c FROM Compra c WHERE c.usuario.id_usuario LIKE ?1")
    public List<Compra> findAll(int id_usuario);

    @Query(value = "SELECT * FROM Compra c ORDER BY c.id_compra DESC limit 1", nativeQuery = true)
    public Compra findUltimoCompra();
}
