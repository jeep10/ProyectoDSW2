package com.cibertec.proyecto.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.proyecto.dao.LocalDao;
import com.cibertec.proyecto.domain.Local;

@Service
public class LocalServiceImpl implements LocalService {

	@Autowired
    private LocalDao Dao;

    @Override
    public List<Local> listaLocal() {
        return Dao.findAll();
    }

    @Override
    public void guardar(Local local) {
        Dao.save(local);
    }

    @Override
    public void eliminar(Local local) {
        Dao.delete(local);
    }

	@Override
	public void actualizar(Integer id, Local local) {
		Local objeto = Dao.findById(id).get();
		if(objeto != null) {
			objeto.setNombre(local.getNombre());
			objeto.setAforo(local.getAforo());
			Dao.save(objeto);
		}
	}
}
