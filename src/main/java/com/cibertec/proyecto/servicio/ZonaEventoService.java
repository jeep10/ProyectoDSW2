package com.cibertec.proyecto.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cibertec.proyecto.domain.ZonaEvento;

@Service
public interface ZonaEventoService {

	public abstract List<ZonaEvento> listazona();

    public abstract void guardar(ZonaEvento zonaEvento);

    public abstract void eliminar(ZonaEvento zonaEvento);
    
    public abstract ZonaEvento econtrarZona(ZonaEvento zonaEvento);

    public abstract String traerAforo(int id);

    public List<ZonaEvento> listarSoloPorNombreZona(String zona);

    public List<ZonaEvento> encontrarPorEvento(int id_evento);

    public ZonaEvento encontrarPorIdeventoYZona(int id_evento, String zona);

    
}
