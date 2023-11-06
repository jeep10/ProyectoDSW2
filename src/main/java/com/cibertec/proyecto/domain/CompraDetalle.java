package com.cibertec.proyecto.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class CompraDetalle implements Serializable{

	private static final long serialVersionUID = 1L;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_compra")
    private Compra compra;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_zona")
    private ZonaEvento zonaEvento;

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public ZonaEvento getZonaEvento() {
		return zonaEvento;
	}

	public void setZonaEvento(ZonaEvento zonaEvento) {
		this.zonaEvento = zonaEvento;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
}
