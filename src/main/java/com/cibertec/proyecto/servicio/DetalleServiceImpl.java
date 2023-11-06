package com.cibertec.proyecto.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.proyecto.dao.DetalleDao;
import com.cibertec.proyecto.domain.Detalle;

@Service
public class DetalleServiceImpl implements DetalleService {

	@Autowired
    private DetalleDao Dao;

    @Override
    public List<Detalle> listaDetalle() {
        return Dao.findAll();
    }

    @Override
    public void guardar(Detalle detalle) {
        Dao.save(detalle);
    }

    @Override
    public void eliminar(Detalle detalle) {
        Dao.delete(detalle);
    }

	
}
