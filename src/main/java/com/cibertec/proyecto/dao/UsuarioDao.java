package com.cibertec.proyecto.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cibertec.proyecto.domain.Usuario;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Long>{
	
	Usuario findByUsername(String username);

	@Query("SELECT u FROM Usuario u WHERE u.username LIKE %?1%")
    public Usuario findAll(String username);

    @Query("SELECT u FROM Usuario u WHERE u.nombres LIKE %?1% "
            + " OR u.apellidos LIKE %?1% "
            + " OR u.username LIKE %?1% "
            + " OR u.dni LIKE %?1%")
    public List<Usuario> findAllUsers(String username);
	
}
