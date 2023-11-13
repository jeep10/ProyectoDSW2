package com.cibertec.proyecto.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cibertec.proyecto.domain.Evento;

@Service
public interface EventoService {

	public List<Evento> listarEvento();

    public void guardar(Evento evento);

    public void eliminar(Evento evento);

    public Evento econtrarEvento(Evento evento);

    public List<Evento> listarPorDisponibilidad();

    public List<Evento> listarPorNombre(String nombre);

    public List<Evento> listarSoloPorNombre(String nombre);

	
}
