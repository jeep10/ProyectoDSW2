package com.cibertec.proyecto.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cibertec.proyecto.domain.CompraDetalle;
import com.cibertec.proyecto.domain.Detalle;

@Repository
public interface DetalleDao extends JpaRepository<Detalle, CompraDetalle>{
    
	@Query("SELECT d FROM Detalle d WHERE d.detalleCompra.compra.usuario.nombres LIKE %?1% OR d.detalleCompra.compra.evento.nombre LIKE %?1%")
    public List<Detalle> findAll(String nombre);

    @Query("SELECT d FROM Detalle d WHERE d.detalleCompra.compra.usuario.id_usuario = ?1")
    public List<Detalle> findAllPorUsuario(long id);

    @Query("SELECT d FROM Detalle d WHERE d.detalleCompra.compra.usuario.id_usuario = ?1 AND d.detalleCompra.compra.evento.nombre LIKE %?2%")
    public List<Detalle> findAllParaUsuario(long id, String nombre);

    @Query("SELECT SUM(d.cantidad) FROM Detalle d WHERE d.detalleCompra.compra.usuario.id_usuario = ?1 AND d.detalleCompra.compra.evento.nombre LIKE %?2%")
    public String verificarCompra(long id, String nombre);

    @Query("SELECT SUM(d.cantidad) FROM Detalle d  WHERE d.detalleCompra.zonaEvento.id_zona LIKE ?1 ")
    public String traerCantidadEntradasVendidas(int id);

    @Query("SELECT d FROM Detalle d  WHERE d.detalleCompra.compra.id_compra LIKE ?1 ")
    public List<Detalle> findByIdCompra(int id);
	
}
