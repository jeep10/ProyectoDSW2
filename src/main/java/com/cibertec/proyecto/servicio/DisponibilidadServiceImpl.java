package com.cibertec.proyecto.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.proyecto.dao.DisponibilidadDao;
import com.cibertec.proyecto.domain.Disponibilidad;

@Service
public class DisponibilidadServiceImpl implements DisponibilidadService{

	@Autowired
    private DisponibilidadDao Dao;

    @Override
    public List<Disponibilidad> listaDisponibilidad() {
        return Dao.findAll();
    }
	
}
