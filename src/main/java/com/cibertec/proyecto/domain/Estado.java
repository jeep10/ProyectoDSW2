package com.cibertec.proyecto.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Estado")
public class Estado {

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_estado;

    private String nomestado;

	public int getId_estado() {
		return id_estado;
	}

	public void setId_estado(int id_estado) {
		this.id_estado = id_estado;
	}

	public String getNomestado() {
		return nomestado;
	}

	public void setNomestado(String nomestado) {
		this.nomestado = nomestado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
    
    
}
