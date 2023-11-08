package com.cibertec.proyecto.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cibertec.proyecto.domain.Usuario;

@Service
public interface UsuarioService {

	public abstract List<Usuario> listaUsuario();

    public abstract void guardar(Usuario usuario);
    
    public void actualizar(Long id, Usuario usuario);

    public abstract void eliminar(Usuario usuario);
	
}
