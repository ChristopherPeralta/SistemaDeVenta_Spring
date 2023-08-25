package idat.edu.pe.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import idat.edu.pe.entidad.Distrito;
import idat.edu.pe.entidad.Empleado;
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
			List<Proveedor>listadoProveedor=proveedorService.listaProveedor().stream()
	                .filter(proveedor -> "Activo".equals(proveedor.getESTADO()))
	                .collect(Collectors.toList());
			List<Distrito>listDistrito=distritoService.listaDistrito();
			
			model.addAttribute("titulo", "Lista de Proveedor");
			model.addAttribute("proveedores", listadoProveedor);
			model.addAttribute("distritos", listDistrito);
			return "proveedores.html";
		}
		
		@GetMapping("/proveedor/registrar")
		public String crearProveedorFormulario(Model model) {
			List<Distrito>listDistrito=distritoService.listaDistrito();
			
			Proveedor proveedor = new Proveedor();
			proveedor.setESTADO("Activo");
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

			proveedorService.softDelete(COD_PROVEEDOR);

			return "redirect:/proveedor";
	    }
		
		@GetMapping("/proveedordetalle/{id}")
		public String ver(@PathVariable("id") String COD_PROVEEDOR, Map<String, Object> model) {
			Proveedor proveedor = proveedorService.findById(COD_PROVEEDOR);
			if(proveedor==null) {
				return "redirect:/proveedorListar";			
			}
			model.put("proveedor",proveedor);
			model.put("titulo", "DETALLE DEL PROVEEDOR: "+proveedor.getNOMBRE());
			return "proveedorDetalle.html";		
		}
		
		@GetMapping("/proveedor/buscar")
	    public String buscarProveedores(
	            @RequestParam(name = "codigoRUCoNombre", required = false) String codigoRUCoNombre,
	            @RequestParam(name = "codigoDistrito", required = false) String codigoDistrito,
	            Model model) {
	        List<Proveedor> proveedorEncontrados = new ArrayList<>();
	        List<Distrito> listDistrito = distritoService.listaDistrito();

	        if (codigoRUCoNombre != null) {
	            if (codigoDistrito != null && !codigoDistrito.isEmpty()) {
	            	proveedorEncontrados = proveedorService.buscarProveedoresPorCriterioYDistrito(codigoRUCoNombre, codigoDistrito);
	            } else {
	            	proveedorEncontrados = proveedorService.buscarProveedoresPorCriterio(codigoRUCoNombre);
	            }
	        }

	        model.addAttribute("proveedores", proveedorEncontrados);
	        model.addAttribute("distritos", listDistrito);
	        model.addAttribute("titulo", "Resultado de búsqueda Proveedor");
	        return "proveedores.html";
	    }
}


	

