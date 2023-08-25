package idat.edu.pe.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
/*import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;*/
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import idat.edu.pe.entidad.Usuario;
import idat.edu.pe.servicio.UsuarioService;

@Controller
public class LoginController {
	/*
	@Autowired
	private SessionRegistry sessionRegistry;*/
	
    @Autowired
    private UsuarioService usuarioService;

    // Método para mostrar el formulario de inicio de sesión
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
    
    @GetMapping("/menu")
    public String showMenuForm() {
        return "menu";
    }
    
    // Método para manejar la solicitud de inicio de sesión
    @PostMapping("/menu")
    public String login(@RequestParam String usuario, @RequestParam String contraseña, Model model) {
        Usuario logeo = usuarioService.getLogeoByUsuario(usuario);
        
        if (logeo != null && logeo.getContraseña().equals(contraseña)) {
            // Inicio de sesión exitoso
            model.addAttribute("usuario", logeo.getUsuario());

            return "menu.html";
        } else {
            // Credenciales inválidas
            model.addAttribute("error", "Credenciales inválidas");
            return "login.html";
        }
    }
    /*
    @GetMapping("/session")
    public ResponseEntity<?> getDetailsSession(){
    	
    	String sessionId ="";
    	User userObject = null;
    	
    	List<Object> sessions = sessionRegistry.getAllPrincipals();
    	
    	for(Object session :sessions) {
    		if(session instanceof User) {
    			userObject = (User) session;
    		}
    		
    		List<SessionInformation> sessionInformations = sessionRegistry.getAllSessions(session, false);
    		
    		for(SessionInformation sessionInformation : sessionInformations) {
    			sessionId = sessionInformation .getSessionId();
    		}
    	}
    	
    	Map<String, Object> response = new HashMap<>();
    	response.put("response", "Hello World");
    	response.put("sessionId", sessionId);
    	response.put("sessionUser", userObject);
    	
    	
    	return ResponseEntity.ok(response);
    }
    */
    
}