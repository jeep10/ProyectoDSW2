package com.cibertec.proyecto.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cibertec.proyecto.domain.Local;

@Repository
public interface LocalDao extends JpaRepository<Local, Integer>{

	@Query("SELECT l FROM Local l WHERE l.nombre LIKE %?1%")
    public List<Local> findAll(String nombre);
	
}
