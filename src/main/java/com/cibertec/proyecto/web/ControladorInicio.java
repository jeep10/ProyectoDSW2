package com.cibertec.proyecto.web;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.proyecto.domain.Disponibilidad;
import com.cibertec.proyecto.domain.Estado;
import com.cibertec.proyecto.domain.Evento;
import com.cibertec.proyecto.domain.Local;
import com.cibertec.proyecto.domain.Usuario;
import com.cibertec.proyecto.servicio.DisponibilidadService;
import com.cibertec.proyecto.servicio.EstadoService;
import com.cibertec.proyecto.servicio.EventoService;
import com.cibertec.proyecto.servicio.FileService;
import com.cibertec.proyecto.servicio.LocalService;
import com.cibertec.proyecto.servicio.UserService;

@Controller
@SuppressWarnings("InitializerMayBeStatic")
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
    private FileService fileService;
    
    @Autowired
    private UserService userService;
    
    ////Inicio
    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user, @Param("nombre") String nombre) {

        var eventos = eventoService.listarPorNombre(nombre);

        model.addAttribute("eventos", eventos);
        model.addAttribute("nombre", nombre);

        return "index";

    }
    

    /////////////Evento
    @GetMapping("/listaEvento")
    public String listaEvento(Model model, @AuthenticationPrincipal User user, @Param("nombre") String nombre) {

        var eventos = eventoService.listarSoloPorNombre(nombre);

        model.addAttribute("eventos", eventos);
        model.addAttribute("nombre", nombre);

        return "crudEvento";

    }
    
    @GetMapping(value = "/uploads/{filename}")
    public ResponseEntity<Resource> goImage(@PathVariable String filename) {
        Resource resource = null;
        try {
            resource = fileService.load(filename);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


    @PostMapping("/guardarEvento")
    public String guardarEvento(@Valid Evento evento, Errors errores, BindingResult result, Model model,
            @RequestParam("file") MultipartFile image, RedirectAttributes flash) throws IOException {

        List<Estado> estados = estService.listaEstado();
        List<Local> locales = localService.listaLocal();
        List<Disponibilidad> dis = disService.listaDisponibilidad();

        if (errores.hasErrors()) {
            System.out.println(result.getFieldError());
            model.addAttribute("estados", estados);
            model.addAttribute("locales", locales);
            model.addAttribute("disponibilidades", dis);
            return "modificarEvento";
        } else {
            if (!image.isEmpty()) {
                if (evento.getId_evento() > 0 && evento.getImagen() != null && evento.getImagen().length() > 0) {
                    fileService.delete(evento.getImagen());
                }
                String uniqueFileName = fileService.copy(image);
                evento.setImagen(uniqueFileName);
            }

            eventoService.guardar(evento);

        }
        return "redirect:/listaEvento";
    }
    
    @GetMapping("/agregarEvento")
    public String agregarEvento(Evento evento, Model model) {
        List<Estado> estados = estService.listaEstado();
        List<Local> locales = localService.listaLocal();
        List<Disponibilidad> dis = disService.listaDisponibilidad();

        model.addAttribute("estados", estados);
        model.addAttribute("locales", locales);
        model.addAttribute("disponibilidades", dis);
        return "modificarEvento";
    }
    
    @GetMapping("/editarEvento/{id_evento}")
    public String editarEvento(Evento evento, Model model) {
        evento = eventoService.econtrarEvento(evento);
        List<Estado> estados = estService.listaEstado();
        List<Local> locales = localService.listaLocal();
        List<Disponibilidad> dis = disService.listaDisponibilidad();

        model.addAttribute("evento", evento);
        model.addAttribute("estados", estados);
        model.addAttribute("locales", locales);
        model.addAttribute("disponibilidades", dis);
        return "modificarEvento";
    }


    @DeleteMapping("/eliminarEvento/{id_evento}")
    public String eliminar(Evento evento) {
        eventoService.eliminar(evento);
        return "redirect:/";
    }
    
    ///////////////////Usuario
    @GetMapping("/signin")
    public String signin(Usuario usuario) {

        return "registrar";
    }

    @PostMapping("/registrarse")
    public String registrarse(@Valid Usuario usuario, Errors errores) {

        if (errores.hasErrors()) {
            return "registrar";
        }

        userService.save(usuario);
        return "redirect:/";

    }

    @GetMapping("/login")
    public String login() {

        return "login";

    }

    @GetMapping("/listaUsuario")
    public String listaUsuario(Model model, @Param("username") String username) {

        var usuarios = userService.encontrarPornombredeusuarioTodos(username);

        model.addAttribute("usuarios", usuarios);
        model.addAttribute("username", username);

        return "crudUsuario";
    }    
    
   

    /////Disponibilidad
    @GetMapping("/JSONdisponibilidad")
    @ResponseBody
    public List<Disponibilidad> listaDis() {

        return disService.listaDisponibilidad();
    }

    /////Estado
    @GetMapping("/JSONestado")
    @ResponseBody
    public List<Estado> listaEstado() {

        return estService.listaEstado();
    }


    




    
    
	
}
