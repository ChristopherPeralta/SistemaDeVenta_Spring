package idat.edu.pe.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import idat.edu.pe.entidad.Distrito;
import idat.edu.pe.entidad.Empleado;
import idat.edu.pe.servicio.DistritoService;
import idat.edu.pe.servicio.EmpleadoService;


@Controller
public class EmpleadoController{
	
	@Autowired
	private EmpleadoService empleadoService;
	
	@Autowired
	private DistritoService distritoService;
	
	//CRUD EMPLEADO
	@RequestMapping("/empleado")
	public String listaEmpleados(Model model) {
		List<Empleado>listadoEmpleados=empleadoService.listaEmpleados();
		
		model.addAttribute("titulo", "Lista de Empleados");
		model.addAttribute("empleados", listadoEmpleados);
		return "empleados.html";
	}
	
	@GetMapping("/empleado/registrar")
	public String crearEmpleadoFormulario(Model model) {
		List<Distrito>listDistrito=distritoService.listaDistrito();
		
		Empleado empleado = new Empleado();
		model.addAttribute("titulo", "Registro de Empleado");
		model.addAttribute("empleado", empleado);
		model.addAttribute("distritos", listDistrito);
		return "empleadoFRM.html";
	}
	
	@PostMapping("/empleado/guardar")
	public String guardar(@ModelAttribute Empleado empleado, BindingResult result,Model model){	
		List<Distrito>listDistrito=distritoService.listaDistrito();

        if(result.hasErrors()){
        	model.addAttribute("titulo", "Registro de Empleado");
            model.addAttribute("empleado", empleado);
            model.addAttribute("distritos", listDistrito);
            System.out.println("Hubo errores en el formulario");
            
            return "registrar_empleado";
        }
        empleadoService.save(empleado);
        System.out.println("Empleado guardado exitosamente");
        return "redirect:/empleado";
    }
	
	@GetMapping("/empleado/editar/{id}")
	public String editar(@PathVariable("id") String COD_EMPLEADO, Model model){

        Empleado empleado=empleadoService.findById(COD_EMPLEADO);
        List<Distrito>listDistrito=distritoService.listaDistrito();
        
        model.addAttribute("titulo", "Editar Empleado");
        model.addAttribute("empleado", empleado);
        model.addAttribute("distritos", listDistrito);

        return "empleadoFRM.html";
    }
	
	@GetMapping("/empleado/eliminar/{id}")
    public String editar(@PathVariable("id") String COD_EMPLEADO){

		empleadoService.deleteById(COD_EMPLEADO);

		return "redirect:/empleado";
    }
	
}
