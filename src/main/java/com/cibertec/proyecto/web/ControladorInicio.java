package com.cibertec.proyecto.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.proyecto.domain.Compra;
import com.cibertec.proyecto.domain.CompraDetalle;
import com.cibertec.proyecto.domain.Detalle;
import com.cibertec.proyecto.domain.Disponibilidad;
import com.cibertec.proyecto.domain.Estado;
import com.cibertec.proyecto.domain.Evento;
import com.cibertec.proyecto.domain.Local;
import com.cibertec.proyecto.domain.Usuario;
import com.cibertec.proyecto.domain.ZonaEvento;
import com.cibertec.proyecto.servicio.CompraService;
import com.cibertec.proyecto.servicio.DetalleService;
import com.cibertec.proyecto.servicio.DisponibilidadService;
import com.cibertec.proyecto.servicio.EstadoService;
import com.cibertec.proyecto.servicio.EventoService;
import com.cibertec.proyecto.servicio.FileService;
import com.cibertec.proyecto.servicio.LocalService;
import com.cibertec.proyecto.servicio.UserService;
import com.cibertec.proyecto.servicio.ZonaEventoService;
import com.cibertec.proyecto.util.QRCodeGenerator;
import com.google.zxing.WriterException;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

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
    
    @Autowired
    private ZonaEventoService zonaService;

    @Autowired
    private CompraService compraService;

    @Autowired
    private DetalleService detalleService;
    
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


    @GetMapping("/eliminarEvento/{id_evento}")
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
    
    
    /////Local
    @GetMapping("/listaLocal")
    public String listaLocal(Model model, @Param("nombre") String nombre) {

        var locales = localService.ecnontrarPorNombre(nombre);

        model.addAttribute("locales", locales);
        model.addAttribute("nombre", nombre);

        return "crudLocal";
    }
    
    @GetMapping("/agregarLocal")
    public String agregarLocal(Local local) {

        return "modificarLocal";
    }

    @PostMapping("/guardarLocal")
    public String guardarLocal(@Valid Local local, Errors errores) {

        if (errores.hasErrors()) {
            return "modificarLocal";
        }

        localService.guardar(local);
        return "redirect:/listaLocal";

    }

    @GetMapping("/editarLocal/{id_local}")
    public String editarLocal(Local local, Model model) {
        local = localService.econtrarLocal(local);
        model.addAttribute("local", local);
        return "modificarLocal";
    }

    @GetMapping("/eliminarLocal/{id_local}")
    public String eliminarLocal(Local local) {
        localService.eliminar(local);
        return "redirect:/listaLocal";
    }
    
    @GetMapping("/JSONlocal")
    @ResponseBody
    public List<Local> listaLocal() {

        return localService.listaLocal();
    }
    
    ///////////ZonaEventos
    @GetMapping("/listaZona")
    public String listaZona(Model model, @Param("zona") String zona) {

        var zonaEventos = zonaService.listarSoloPorNombreZona(zona);

        model.addAttribute("zonaEventos", zonaEventos);
        model.addAttribute("zona", zona);

        return "crudZona";
    }
    
    @GetMapping("/agregarZona")
    public String agregarZona(ZonaEvento zonaEvento, Model model) {

        List<Evento> eventos = eventoService.listarEvento();

        model.addAttribute("eventos", eventos);

        return "modificarZona";
    }

    @PostMapping("/guardarZona")
    public String guardarZona(@Valid ZonaEvento zonaEvento, Errors errores, Model model) {

        List<Evento> eventos = eventoService.listarEvento();
        int capacidadmax = zonaEvento.getEvento().getLocal().getAforo();

         String entradasVend = detalleService.traerEntradasVendidas(zonaEvento.getId_zona());
        int stocks = zonaEvento.getStock();
       
        int cantidadVentas = 0;
        
        if(entradasVend ==null){
            cantidadVentas = 0;
        }
        else{ cantidadVentas = Integer.parseInt(entradasVend);}
        
        String stocks1 = zonaService.traerAforo(zonaEvento.getEvento().getId_evento());
       

        if (stocks1 == null && entradasVend == null) {
            stocks = zonaEvento.getStock();
        } else if(stocks1 != null && entradasVend == null){
            stocks = Integer.parseInt(zonaService.traerAforo(zonaEvento.getEvento().getId_evento())) + zonaEvento.getStock();
        }
        else if(stocks1 !=null && entradasVend != null){
            stocks = Integer.parseInt(zonaService.traerAforo(zonaEvento.getEvento().getId_evento())) + zonaEvento.getStock() + Integer.parseInt(entradasVend);
        }
        
        System.out.println(stocks1 + " " + zonaEvento.getStock() + " " + entradasVend);

        if (errores.hasErrors() || stocks > capacidadmax || zonaEvento.getStock() < cantidadVentas) {
            model.addAttribute("eventos", eventos);
            return "modificarZona";

        }

        zonaService.guardar(zonaEvento);
        return "redirect:/listaZona";

    }
    
    @GetMapping("/editarZona/{id_zona}")
    public String editarZona(ZonaEvento zonaEvento, Model model) {
        zonaEvento = zonaService.econtrarZona(zonaEvento);
        List<Evento> eventos = eventoService.listarEvento();

        model.addAttribute("zonaEvento", zonaEvento);
        model.addAttribute("eventos", eventos);
        return "modificarZona";
    }

    @GetMapping("/eliminarZona/{id_zona}")
    public String eliminarZona(ZonaEvento zonaEvento) {
        zonaService.eliminar(zonaEvento);
        return "redirect:/listaZona";
    }

    
    //////////Comprar Boleto
    @GetMapping("/comprarBoleto/{id_evento}")
    public String comprarBoleto(Evento evento, Model model) {

        List<ZonaEvento> zonaEventos = zonaService.encontrarPorEvento(evento.getId_evento());
        evento = eventoService.econtrarEvento(evento);

        model.addAttribute("evento", evento);
        model.addAttribute("zonaEventos", zonaEventos);
        return "realizarCompra";
    }

    @PostMapping("/guardarCompra")
    public String guardarCompra(Evento evento, Usuario usuario, ZonaEvento zonaEvento, Detalle detalle, Errors errores, Model model) {

        String username;
        int cantidad;
        String zona;

        List<ZonaEvento> zonaEventos = zonaService.encontrarPorEvento(evento.getId_evento());
        evento = eventoService.econtrarEvento(evento);
        
        username = usuario.getUsername();
        cantidad = detalle.getCantidad();
        zona = zonaEvento.getZona();

        Usuario usuarios = userService.encontrarPornombredeusuario(username);
        ZonaEvento zonae = zonaService.encontrarPorIdeventoYZona(evento.getId_evento(), zona);

        Date date = Date.from(Instant.now());

        Compra compra = new Compra();
        compra.setUsuario(usuarios);
        compra.setFecha(date);
        compra.setEvento(evento);
        compra.setTotal(zonae.getPrecio() * cantidad);
        
        ZonaEvento ze = new ZonaEvento();
        ze.setId_zona(zonae.getId_zona());
        ze.setEvento(zonae.getEvento());
        ze.setPrecio(zonae.getPrecio());
        ze.setZona(zonae.getZona());
        ze.setStock(zonae.getStock()-cantidad);
        
        
        

        CompraDetalle compdeta = new CompraDetalle(); 
        
        compdeta.setCompra(compra);
        compdeta.setZonaEvento(zonae);

        Detalle deta = new Detalle();
        
        if(cantidad == 1) {
        
        deta.setDetalleCompra(compdeta);
        deta.setPrecio(zonae.getPrecio());
        deta.setCantidad(cantidad);
        deta.setInvitado("");
        }
        
        else if(cantidad==2) 
        {
        	deta.setDetalleCompra(compdeta);
            deta.setPrecio(zonae.getPrecio());
            deta.setCantidad(cantidad);
            deta.setInvitado(detalle.getInvitado());
        }
        
        String verificacion = detalleService.verificar(usuarios.getId_usuario(), evento.getNombre());
        
        int cantidadmax;
        
        if(verificacion != null){
            cantidadmax=  Integer.parseInt(verificacion) + cantidad;
        }
        else cantidadmax = cantidad;
        
        if (errores.hasErrors() || cantidadmax > 2 || cantidadmax > zonae.getStock()){
            
        model.addAttribute("zonaEventos", zonaEventos);
            return "realizarCompra";
        }

        compraService.guardar(compra);

        detalleService.guardar(deta);
        zonaService.guardar(ze);
        return "redirect:/";

    }

    @GetMapping("/listaCompraTotal")
    public String listaCompraTotal(Model model, @Param("nombre") String nombre) {

        var detalles = detalleService.listaDetallePorNombre(nombre);

        model.addAttribute("detalles", detalles);
        model.addAttribute("nombre", nombre);

        return "crudCompra";
    }
    
    @GetMapping("/listaCompraUsuario")
    public String listaCompraUsuario(Model model) {

        

        return "crudCompraUsuarios";
    }

    @GetMapping("/listaCompraUsuarios")
    public String listaCompraUsuarios(Model model, @Param("nombre") String nombre, Usuario usuario) {

        Usuario usuarios = userService.encontrarPornombredeusuario(usuario.getUsername());
        
        var detalles = detalleService.listaDetalleParaUsuario(usuarios.getId_usuario(), nombre);

        System.out.println("Usuario : " + usuario.getUsername());
        
        

        model.addAttribute("detalles", detalles);
        model.addAttribute("nombre", nombre);

        return "crudCompraUsuarios";
    }
    
    @GetMapping("/JSONCompra/{id_compra}")
    @ResponseBody
    public Compra listaCompra(@PathVariable("id_compra") int id_compra)  {

        return compraService.econtrarCompra(id_compra);
    }
    
    @GetMapping("/Genpdf/{id_compra}")
    public String Genpdf(@PathVariable ("id_compra") int id_compra, HttpServletRequest request, HttpServletResponse response) throws WriterException, IOException {

       Compra compra =  compraService.econtrarCompra(id_compra);
       
         String path = "./src/main/resources/qrcodes/QRCode"+id_compra+".png";
         
         List<Detalle> detalles = detalleService.encontrarPoridCompra(id_compra);
       
       
       String codeText = detalles.toString();
        
        QRCodeGenerator.generateQRCodeImage(codeText, 300, 300, path);
        
        compra.setQr("QRCode"+id_compra+".png");
        
        compraService.guardar(compra);
        
        try {
            //Data Source
            List<Detalle> detallespdf = detalleService.encontrarPoridCompra(id_compra);
            //Quizas tenga que estar en list
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(detallespdf);
            
            String fileDirectory = "./src/main/resources/reportes/reporteDetalleCompra.jasper";
          
            FileInputStream stream = new  FileInputStream(new File(fileDirectory));
            
          
            
            Map<String, Object> params = new HashMap<String,Object>();
            params.put("qr", "./src/main/resources/qrcodes/QRCode"+id_compra+".png");
            
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(stream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
            
            response.setContentType("application/x-pdf");
            response.addHeader("Content-disposition", "attachment; filename=GenerarEntrada.pdf");
            
           try (OutputStream outStream = response.getOutputStream()) {
               JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
           }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "crudCompraUsuarios";

    }


    




    
    
	
}
