package com.cibertec.proyecto.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.proyecto.dao.EstadoDao;
import com.cibertec.proyecto.domain.Estado;

@Service
public class EstadoServiceImpl implements EstadoService{

	@Autowired
    private EstadoDao Dao;

    @Override
    public List<Estado> listaEstado() {
        return Dao.findAll();
    }
    
}
