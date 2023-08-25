package idat.edu.pe.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import idat.edu.pe.servicio.UsuarioServicio;
@Controller
public class RegistroControlador<Usuario> {

	@Autowired
	private UsuarioServicio servicio;
	
	@GetMapping("/login")
	public String iniciarSesion() {
		return "login";
	}
	
	@GetMapping("/menu")
    public String showMenuForm() {
        return "index";
    }
    
    // Método para manejar la solicitud de inicio de sesión
    @PostMapping("/menu")
    public String login(@RequestParam String usuario, @RequestParam String password, Model model) {
        idat.edu.pe.entidad.Usuario logeo = servicio.getLogeoByUsuario(usuario);
        
        if (logeo != null && logeo.getPassword().equals(password)) {
            // Inicio de sesión exitoso
            model.addAttribute("usuario", logeo.getNombre());

            return "index";
        } else {
            // Credenciales inválidas
            model.addAttribute("error", "Credenciales inválidas");
            return "login";
        }
    }
    
    
}

