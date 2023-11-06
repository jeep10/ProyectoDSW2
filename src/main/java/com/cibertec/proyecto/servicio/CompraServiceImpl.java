package com.cibertec.proyecto.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.proyecto.dao.CompraDao;
import com.cibertec.proyecto.domain.Compra;

@Service
public class CompraServiceImpl implements CompraService{

	@Autowired
    private CompraDao dao;

    @Override
    public List<Compra> listarCompra() {
        return dao.findAll();
    }

    @Override
    public void guardar(Compra compra) {
        dao.save(compra);
    }

    @Override
    public void eliminar(Compra compra) {
        dao.delete(compra);
    }

}
