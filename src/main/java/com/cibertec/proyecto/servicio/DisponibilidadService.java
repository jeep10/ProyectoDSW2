package com.cibertec.proyecto.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cibertec.proyecto.domain.Disponibilidad;

@Service
public interface DisponibilidadService {

	public abstract List<Disponibilidad> listaDisponibilidad();
	
}
