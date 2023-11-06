package com.cibertec.proyecto.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cibertec.proyecto.domain.Estado;

@Service
public interface EstadoService {

	public abstract List<Estado> listaEstado();
	
}
