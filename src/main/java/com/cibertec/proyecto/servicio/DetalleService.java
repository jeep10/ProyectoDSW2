package com.cibertec.proyecto.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cibertec.proyecto.domain.Detalle;

@Service
public interface DetalleService {

	public abstract List<Detalle> listaDetalle();

    public abstract void guardar(Detalle detalle);

    public abstract void eliminar(Detalle detalle);
}
