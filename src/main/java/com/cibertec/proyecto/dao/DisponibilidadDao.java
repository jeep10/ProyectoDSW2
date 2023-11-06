package com.cibertec.proyecto.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.proyecto.domain.Disponibilidad;

@Repository
public interface DisponibilidadDao extends JpaRepository<Disponibilidad, Integer>{

}
