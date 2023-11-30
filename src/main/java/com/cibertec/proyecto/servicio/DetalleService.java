package com.cibertec.proyecto.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cibertec.proyecto.domain.Detalle;

@Service
public interface DetalleService {

	public abstract List<Detalle> listaDetalle();

    public abstract void guardar(Detalle detalle);

    public abstract void eliminar(Detalle detalle);
    
    public abstract List<Detalle> listaDetallePorNombre(String nombre);

    public abstract List<Detalle> listaDetalleParaUsuario(long id, String nombre);

    public String verificar(long id, String nombre);

    public abstract String traerEntradasVendidas(int id);

    public abstract List<Detalle> encontrarPoridCompra(int id);
}
