package com.cibertec.proyecto.servicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cibertec.proyecto.dao.ZonaEventoDao;
import com.cibertec.proyecto.domain.ZonaEvento;

@Service
public class ZonaEventoImpl implements ZonaEventoService{

	@Autowired
    private ZonaEventoDao Dao;

    @Override
    @Transactional(readOnly = true)
    public List<ZonaEvento> listazona() {
        return Dao.findAll();
    }

    @Override
    @Transactional
    public void guardar(ZonaEvento zonaEvento) {
        Dao.save(zonaEvento);
    }

    @Override
    @Transactional
    public void eliminar(ZonaEvento zonaEvento) {
        Dao.delete(zonaEvento);
    }

    @Override
    @Transactional(readOnly = true)
    public ZonaEvento econtrarZona(ZonaEvento zonaEvento) {
        return Dao.findById(zonaEvento.getId_zona()).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public String traerAforo(int id) {
        return Dao.traerAforo(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ZonaEvento> listarSoloPorNombreZona(String zona) {
        if (zona != null) {
            return Dao.findAll(zona);
        }

        return Dao.findAll();

    }

    @Override
    @Transactional(readOnly = true)
    public List<ZonaEvento> encontrarPorEvento(int id_evento) {
        return Dao.findAllById_evento(id_evento);
    }

    @Override
    public ZonaEvento encontrarPorIdeventoYZona(int id_evento, String zona) {
        return Dao.findbyIdeventoZona(id_evento, zona);
    }


    
}
