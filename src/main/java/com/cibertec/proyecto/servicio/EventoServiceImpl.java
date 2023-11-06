package com.cibertec.proyecto.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.proyecto.dao.EventoDao;
import com.cibertec.proyecto.domain.Evento;

@Service
public class EventoServiceImpl implements EventoService{

	@Autowired
    private EventoDao eventoDao;

    @Override
    public List<Evento> listarEvento() {
        return eventoDao.findAll();
    }

    @Override
    public void guardar(Evento evento) {
        eventoDao.save(evento);
    }

    @Override
    public void eliminar(Integer id) {
    	Optional<Evento> objeto = eventoDao.findById(id);
		if(objeto.isPresent()) {
			eventoDao.delete(objeto.get());
		}	
    }


	@Override
	public void actualizar(Integer id, Evento evento) {
		Evento objeto = eventoDao.findById(id).get();
		if(objeto != null) {
			objeto.setNombre(evento.getNombre());
			objeto.setFecha(evento.getFecha());
			eventoDao.save(objeto);
		}
		
	}
    
}
