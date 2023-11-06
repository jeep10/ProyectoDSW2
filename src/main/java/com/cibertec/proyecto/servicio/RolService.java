package com.cibertec.proyecto.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cibertec.proyecto.domain.Rol;

@Service
public interface RolService {

	public abstract List<Rol> listaRol();
}
