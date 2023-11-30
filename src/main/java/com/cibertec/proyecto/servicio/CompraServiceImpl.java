package com.cibertec.proyecto.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cibertec.proyecto.dao.CompraDao;
import com.cibertec.proyecto.domain.Compra;

@Service
public class CompraServiceImpl implements CompraService{

	@Autowired
    private CompraDao dao;

    @Override
    @Transactional(readOnly = true)
    public List<Compra> listarCompra() {
        return dao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Compra compra) {
        dao.save(compra);
    }

    @Override
    @Transactional
    public void eliminar(Compra compra) {
        dao.delete(compra);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Compra econtrarCompra(Compra compra) {
        return dao.findById(compra.getId_compra()).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Compra> listarPorId(int id_usuario) {
        if (id_usuario != 0) {
            return dao.findAll(id_usuario);

        }
        return dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Compra listarUltimo() {
        return dao.findUltimoCompra();
    }

    @Override
    @Transactional(readOnly = true)
    public Compra econtrarCompra(int id_compra) {
        return dao.findById(id_compra).orElse(null);
    }

}
