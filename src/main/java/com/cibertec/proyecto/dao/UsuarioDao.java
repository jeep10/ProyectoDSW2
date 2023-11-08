package com.cibertec.proyecto.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.proyecto.domain.Usuario;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Long>{

}
