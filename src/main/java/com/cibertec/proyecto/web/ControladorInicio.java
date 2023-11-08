package com.cibertec.proyecto.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.proyecto.domain.Disponibilidad;
import com.cibertec.proyecto.domain.Estado;
import com.cibertec.proyecto.domain.Evento;
import com.cibertec.proyecto.domain.Local;
import com.cibertec.proyecto.domain.Usuario;
import com.cibertec.proyecto.domain.ZonaEvento;
import com.cibertec.proyecto.servicio.DisponibilidadService;
import com.cibertec.proyecto.servicio.EstadoService;
import com.cibertec.proyecto.servicio.EventoService;
import com.cibertec.proyecto.servicio.LocalService;
import com.cibertec.proyecto.servicio.UsuarioService;
import com.cibertec.proyecto.servicio.ZonaEventoService;

@RestController
@RequestMapping("/cibertec")
public class ControladorInicio {

    @Autowired
    private DisponibilidadService disService;

    @Autowired
    private EstadoService estService;

    @Autowired
    private LocalService localService;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private ZonaEventoService zonaService;
    
    @Autowired
    private UsuarioService usuarioService;
    

    /////////////Evento
    @GetMapping("/listaEvento")
    public List<Evento> listaEvento() {

        return eventoService.listarEvento();
    }


    @PostMapping("/guardarEvento")
    public void guardarEvento(@RequestBody Evento evento){

        eventoService.guardar(evento);
    }
    
    @PutMapping("/actualizar/{id}")
	public void actualizarEvento(@PathVariable Integer id, @RequestBody Evento evento) {
		eventoService.actualizar(id, evento);
	}


    @DeleteMapping("/eliminarEvento/{id_evento}")
    public void eliminar(@PathVariable Integer id_evento) {
        eventoService.eliminar(id_evento);
    }


    /////Disponibilidad
    @GetMapping("/listaDisponibilidad")
    @ResponseBody
    public List<Disponibilidad> listaDis() {

        return disService.listaDisponibilidad();
    }

    /////Estado
    @GetMapping("/listaEstado")
    @ResponseBody
    public List<Estado> listaEstado() {

        return estService.listaEstado();
    }

    /////Local
    @GetMapping("/listaLocal")
    @ResponseBody
    public List<Local> listaLocal() {

        return localService.listaLocal();
    }
    
    @PostMapping("/guardarLocal")
    public void guardarLocal(@RequestBody Local local) {

    	localService.guardar(local);

    }
    
    @PutMapping("/editarLocal/{id_local}")
    public void editarLocal(@PathVariable Integer id, @RequestBody Local local) {
        localService.actualizar(id,local);
    }


    @DeleteMapping("/eliminarLocal/{id_local}")
    public void eliminarLocal(Local local) {
        localService.eliminar(local);
    }


    ///////////ZonaEventos
    @GetMapping("/listaZona")
    public List<ZonaEvento> listaZona() {

        return zonaService.listazona();
    }
    
    @PostMapping("/guardarZona")
    public void guardarZona(@RequestBody  ZonaEvento zonaEvento) {
    	
        zonaService.guardar(zonaEvento);
       
    }
    
    @PutMapping("/editarZona/{id_zona}")
    public void editarZona(@PathVariable Integer id, @RequestBody ZonaEvento zona) {
        zonaService.actualizar(id,zona);
    }
    

    @DeleteMapping("/eliminarZona/{id_zona}")
    public void eliminarZona(ZonaEvento zonaEvento) {
        zonaService.eliminar(zonaEvento);
       
    }
    
    //////Usuario
    @GetMapping("/listaUsuario")
    public List<Usuario> listaUsuario() {

        return usuarioService.listaUsuario();
    }
    
    @PostMapping("/guardarUsuario")
    public void guardarUsuario(@RequestBody  Usuario usuario) {
    	
        usuarioService.guardar(usuario);
        
    }
    
    @PutMapping("/editarUsuario/{id_usuario}")
    public void editarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuarioService.actualizar(id,usuario);
    }
    
    @DeleteMapping("/eliminarUsuario/{id_usuario}")
    public void eliminarUsuario(Usuario usuario) {
        usuarioService.eliminar(usuario);
    }




    
    
	
}
