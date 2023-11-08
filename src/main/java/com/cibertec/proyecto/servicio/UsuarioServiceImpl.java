package com.cibertec.proyecto.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.proyecto.dao.UsuarioDao;
import com.cibertec.proyecto.domain.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
    private UsuarioDao Dao;
	
	@Override
	public List<Usuario> listaUsuario() {
		return Dao.findAll();
	}

	@Override
	public void guardar(Usuario usuario) {
		Dao.save(usuario);
	}

	@Override
	public void actualizar(Long id, Usuario usuario) {
		Usuario objeto = Dao.findById(id).get();
		if(objeto != null) {
			objeto.setUsername(usuario.getUsername());
			Dao.save(objeto);
			
		}
		
	}

	@Override
	public void eliminar(Usuario usuario) {
		Dao.delete(usuario);
	}

}
