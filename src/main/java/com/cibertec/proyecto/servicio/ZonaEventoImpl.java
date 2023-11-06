package com.cibertec.proyecto.servicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.proyecto.dao.ZonaEventoDao;
import com.cibertec.proyecto.domain.ZonaEvento;

@Service
public class ZonaEventoImpl implements ZonaEventoService{

	@Autowired
    private ZonaEventoDao Dao;

    @Override
    public List<ZonaEvento> listazona() {
        return Dao.findAll();
    }

    @Override
    public void guardar(ZonaEvento zonaEvento) {
        Dao.save(zonaEvento);
    }

    @Override
    public void eliminar(ZonaEvento zonaEvento) {
        Dao.delete(zonaEvento);
    }

	@Override
	public void actualizar(Integer id, ZonaEvento zona) {
		ZonaEvento objeto = Dao.findById(id).get();
		if(objeto != null) {
			objeto.setEvento(zona.getEvento());
			objeto.setZona(zona.getZona());
			objeto.setPrecio(zona.getPrecio());
			objeto.setStock(zona.getStock());
			Dao.save(objeto);
		}
	}


    
}
