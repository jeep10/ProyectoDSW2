package com.cibertec.proyecto.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.proyecto.domain.ZonaEvento;

@Repository
public interface ZonaEventoDao extends JpaRepository<ZonaEvento, Integer> {
    
}
