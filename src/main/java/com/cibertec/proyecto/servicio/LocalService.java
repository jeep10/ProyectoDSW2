package com.cibertec.proyecto.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cibertec.proyecto.domain.Local;

@Service
public interface LocalService {

	public abstract List<Local> listaLocal();

    public abstract void guardar(Local local);

    public abstract void eliminar(Local local);

    public abstract Local econtrarLocal(Local local);

    public abstract List<Local> ecnontrarPorNombre(String nombre);
    
}
