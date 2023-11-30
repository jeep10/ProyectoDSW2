package com.cibertec.proyecto.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cibertec.proyecto.domain.Evento;

@Service
public interface EventoService {

	public abstract List<Evento> listarEvento();

    public abstract void guardar(Evento evento);

    public abstract void eliminar(Evento evento);

    public abstract Evento econtrarEvento(Evento evento);

    public abstract List<Evento> listarPorDisponibilidad();

    public abstract List<Evento> listarPorNombre(String nombre);

    public abstract List<Evento> listarSoloPorNombre(String nombre);

	
}
