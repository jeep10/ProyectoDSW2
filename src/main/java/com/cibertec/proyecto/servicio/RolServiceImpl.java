package com.cibertec.proyecto.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.proyecto.domain.Rol;

@Service
public class RolServiceImpl implements RolService {

	@Autowired
    private com.cibertec.proyecto.dao.RolDao RolDao;

    @Override
    public List<Rol> listaRol() {
        return RolDao.findAll();
    }
}
