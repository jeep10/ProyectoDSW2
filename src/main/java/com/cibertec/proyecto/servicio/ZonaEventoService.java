package com.cibertec.proyecto.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cibertec.proyecto.domain.ZonaEvento;

@Service
public interface ZonaEventoService {

	public abstract List<ZonaEvento> listazona();

    public abstract void guardar(ZonaEvento zonaEvento);
    
    public void actualizar(Integer id, ZonaEvento zona);

    public abstract void eliminar(ZonaEvento zonaEvento);

    
}
