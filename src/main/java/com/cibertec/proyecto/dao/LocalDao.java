package com.cibertec.proyecto.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.proyecto.domain.Local;

@Repository
public interface LocalDao extends JpaRepository<Local, Integer>{

	
}
