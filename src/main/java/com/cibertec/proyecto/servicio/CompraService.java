package com.cibertec.proyecto.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cibertec.proyecto.domain.Compra;

@Service
public interface CompraService {
	
	public abstract List<Compra> listarCompra();

    public abstract void guardar(Compra compra);

    public abstract void eliminar(Compra compra);

    public abstract Compra econtrarCompra(Compra compra);

    public abstract List<Compra> listarPorId(int id_usuario);

    public abstract Compra listarUltimo();

    public abstract Compra econtrarCompra(int id_compra);

    

}
