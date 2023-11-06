package com.cibertec.proyecto.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cibertec.proyecto.domain.Evento;

@Service
public interface EventoService {

	public  List<Evento> listarEvento();

    public  void guardar(Evento evento);
    
    public void actualizar(Integer id, Evento evento);

    public  void eliminar(Integer id);

	
}
