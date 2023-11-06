package com.cibertec.proyecto.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "disponibilidad")
public class Disponibilidad {
	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_disponibilidad;

    private String nomdis;

	public int getId_disponibilidad() {
		return id_disponibilidad;
	}

	public void setId_disponibilidad(int id_disponibilidad) {
		this.id_disponibilidad = id_disponibilidad;
	}

	public String getNomdis() {
		return nomdis;
	}

	public void setNomdis(String nomdis) {
		this.nomdis = nomdis;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
    

}
