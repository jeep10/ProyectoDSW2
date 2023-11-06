package com.cibertec.proyecto.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.proyecto.domain.Evento;

@Repository
public interface EventoDao extends JpaRepository<Evento, Integer>{



   
  
}