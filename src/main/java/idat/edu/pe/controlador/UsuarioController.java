package idat.edu.pe.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import idat.edu.pe.entidad.Usuario;
import idat.edu.pe.servicio.UsuarioServicio;

@Controller
public class UsuarioController {
	
	@Autowired
	private	UsuarioServicio usuarioServicio;
	
	@RequestMapping("/UsuarioLista")
    public String mostrarListadoUsuarios(Model model) {
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        
        model.addAttribute("titulo", "Lista de Usuarios");
        return "Usuario.html"; 
	
	}
	
    
	@GetMapping("/usuario/buscar")
    public String buscarUsuarios(
            @RequestParam(name = "codigoNombreApellido", required = false) String codigoNombreApellido,
            Model model) {
		List<Usuario> usuariosEncontrados = new ArrayList<>();

        if (codigoNombreApellido != null && !codigoNombreApellido.isEmpty()) {
        	usuariosEncontrados = usuarioServicio.buscarPorCodigoNombreApellido(codigoNombreApellido);
            model.addAttribute("usuarios", usuariosEncontrados);
            model.addAttribute("titulo", "Resultado de búsqueda Usuario");
            
        }
        
        
        return "Usuario.html";
        

    }
}