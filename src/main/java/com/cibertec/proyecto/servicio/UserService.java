package com.cibertec.proyecto.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cibertec.proyecto.domain.Usuario;

@Service
public interface UserService {

	public Usuario save(Usuario usuario);

    public abstract List<Usuario> listarUser();

    public abstract void eliminar(Usuario evento);

    public abstract Usuario econtrarUser(Usuario evento);

    public Usuario encontrarPornombredeusuario(String username);

    public List<Usuario> encontrarPornombredeusuarioTodos(String username);
}
