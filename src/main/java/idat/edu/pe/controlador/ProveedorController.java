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
import idat.edu.pe.entidad.Proveedor;
import idat.edu.pe.servicio.DistritoService;
import idat.edu.pe.servicio.ProveedorService;

@Controller
public class ProveedorController {

	@Autowired
	private ProveedorService proveedorService;
	
	@Autowired
	private DistritoService distritoService;
	
	//CRUD PROVEEDOR
		@RequestMapping("/proveedor")
		public String listaProveedor(Model model) {
			List<Proveedor>listadoProveedor=proveedorService.listaProveedor();
			
			model.addAttribute("titulo", "Lista de Proveedor");
			model.addAttribute("proveedores", listadoProveedor);
			return "proveedores.html";
		}
		
		@GetMapping("/proveedor/registrar")
		public String crearProveedorFormulario(Model model) {
			List<Distrito>listDistrito=distritoService.listaDistrito();
			
			Proveedor proveedor = new Proveedor();
			model.addAttribute("titulo", "Registro de Proveedor");
			model.addAttribute("proveedor", proveedor);
			model.addAttribute("distritos", listDistrito);
			return "proveedorFRM.html";
		}
		
		@PostMapping("/proveedor/guardar")
		public String guardar(@ModelAttribute Proveedor proveedor, BindingResult result,Model model){	
			List<Distrito>listDistrito=distritoService.listaDistrito();

	        if(result.hasErrors()){
	        	model.addAttribute("titulo", "Registro de Cliente");
	            model.addAttribute("proveedor", proveedor);
	            model.addAttribute("distritos", listDistrito);
	            System.out.println("Hubo errores en el formulario");
	            
	            return "proveedorFRM.html";
	        }
	        proveedorService.save(proveedor);
	        System.out.println("Proveedor guardado exitosamente");
	        return "redirect:/proveedor";
	    }
		
		@GetMapping("/proveedor/editar/{id}")
		public String editar(@PathVariable("id") String COD_PROVEEDOR, Model model){

			Proveedor proveedor=proveedorService.findById(COD_PROVEEDOR);
	        List<Distrito>listDistrito=distritoService.listaDistrito();
	        
	        model.addAttribute("titulo", "Editar Proveedor");
	        model.addAttribute("proveedor", proveedor);
	        model.addAttribute("distritos", listDistrito);

	        return "proveedorFRM.html";
	    }
		
		@GetMapping("/proveedor/eliminar/{id}")
	    public String editar(@PathVariable("id") String COD_PROVEEDOR){

			proveedorService.deleteById(COD_PROVEEDOR);

			return "redirect:/proveedor";
	    }
}
