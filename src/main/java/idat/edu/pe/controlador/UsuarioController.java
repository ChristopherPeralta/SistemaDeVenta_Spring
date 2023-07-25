package idat.edu.pe.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import idat.edu.pe.entidad.Cargo;
import idat.edu.pe.entidad.Empleado;
import idat.edu.pe.entidad.Usuario;
import idat.edu.pe.servicio.CargoService;
import idat.edu.pe.servicio.EmpleadoService;
import idat.edu.pe.servicio.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private EmpleadoService empleadoService;
	
	@Autowired
	private CargoService cargoService;
	
	@GetMapping("/usuario/registrar")
    public String crearProveedorFormulario(Model model) {
		List<Empleado>listEmpleado=empleadoService.listaEmpleados();
		List<Cargo>listCargo=cargoService.listaCargo();
		
		Usuario usuario = new Usuario();
		model.addAttribute("titulo", "Registro de Usuario");
		model.addAttribute("usuario", usuario);
		model.addAttribute("empleados", listEmpleado);
        model.addAttribute("cargos", listCargo);   
    	return "loginRegistrarUsuario";
    }
	
	@PostMapping("/usuario/guardar")
	public String guardar(@ModelAttribute Usuario usuario, BindingResult result,Model model){	
		List<Empleado>listEmpleado=empleadoService.listaEmpleados();
		List<Cargo>listCargo=cargoService.listaCargo();


        if(result.hasErrors()){
        	model.addAttribute("titulo", "Registro de Usuario");
            model.addAttribute("usuario", usuario);
            model.addAttribute("empleados", listEmpleado);
            model.addAttribute("cargos", listCargo);            
            System.out.println("Hubo errores en el formulario");
            
            return "loginRegistrarUsuarioFRM.html";
        }
        usuarioService.save(usuario);
        System.out.println("Usuario guardado exitosamente");
        return "redirect:/login";
    }
	
}
