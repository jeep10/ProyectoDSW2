package com.cibertec.proyecto.domain;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "detalle")
public class Detalle implements Serializable{

	private static final long serialVersionUID = 1L;

    @EmbeddedId
    private CompraDetalle detalleCompra;

    @NotNull
    private double precio;

    @NotNull
    private int cantidad;

	public CompraDetalle getDetalleCompra() {
		return detalleCompra;
	}

	public void setDetalleCompra(CompraDetalle detalleCompra) {
		this.detalleCompra = detalleCompra;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
}
