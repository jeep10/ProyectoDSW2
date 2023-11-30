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
    
    private String invitado;

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
	
	


	public String getInvitado() {
		return invitado;
	}

	public void setInvitado(String invitado) {
		this.invitado = invitado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	@Override
	public String toString() {
		return "Detalle [id= " + detalleCompra.getCompra().getId_compra() + " fecha= " + detalleCompra.getCompra().getFecha() + " total= " + detalleCompra.getCompra().getTotal() + " evento= " + detalleCompra.getCompra().getEvento().getNombre() + " zona= " + detalleCompra.getZonaEvento().getZona() + ", precio= " + precio + ", cantidad= " + cantidad + ", invitado=" + invitado + "]";
	}
    
	
	
    
}
