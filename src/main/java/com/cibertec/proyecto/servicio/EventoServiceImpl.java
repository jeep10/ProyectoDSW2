package com.cibertec.proyecto.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cibertec.proyecto.dao.EventoDao;
import com.cibertec.proyecto.domain.Evento;

@Service
public class EventoServiceImpl implements EventoService{

	@Autowired
    private EventoDao eventoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Evento> listarEvento() {
        return eventoDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Evento evento) {
        eventoDao.save(evento);
    }

    @Override
    @Transactional
    public void eliminar(Evento evento) {
        eventoDao.delete(evento);
    }

    @Override
    @Transactional(readOnly = true)
    public Evento econtrarEvento(Evento evento) {
        return eventoDao.findById(evento.getId_evento()).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Evento> listarPorDisponibilidad() {

        return eventoDao.findAllDis();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Evento> listarPorNombre(String nombre) {

        if (nombre != null) {
            return eventoDao.findAllPorNombre(nombre);

        }

        return eventoDao.findAllDis();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Evento> listarSoloPorNombre(String nombre) {
        if (nombre != null) {
            return eventoDao.findAll(nombre);

        }

        return eventoDao.findAll();
    }
    
}
