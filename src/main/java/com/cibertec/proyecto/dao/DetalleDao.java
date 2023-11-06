package com.cibertec.proyecto.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.proyecto.domain.CompraDetalle;
import com.cibertec.proyecto.domain.Detalle;

@Repository
public interface DetalleDao extends JpaRepository<Detalle, CompraDetalle>{
    
}
