package com.cibertec.proyecto.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.proyecto.domain.Compra;

@Repository
public interface CompraDao extends JpaRepository<Compra, Integer>{

    
}
