package idat.edu.pe.controlador;

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

import idat.edu.pe.entidad.Clasificacion;
import idat.edu.pe.servicio.ClasificacionService;

@Controller
public class ClasificacionController {
	@Autowired
	private ClasificacionService clasificacionService;
	
	//CRUD 
		@RequestMapping("/clasificacion")
		public String listaClasificacion(Model model) {
			List<Clasificacion>listadoClasificacion=clasificacionService.listaClasificacion().stream()
	                .filter(clasificacion -> "Activo".equals(clasificacion.getESTADO()))
	                .collect(Collectors.toList());
			
			model.addAttribute("titulo", "Lista de Clasificacion");
			model.addAttribute("clasificaciones", listadoClasificacion);
			return "clasificacion.html";
		}
		
		@GetMapping("/clasificacion/registrar")
		public String crearClasificacionFormulario(Model model) {
			
			Clasificacion clasificacion = new Clasificacion();
			clasificacion.setESTADO("Activo");
			model.addAttribute("titulo", "Registro de Clasificacion");
			model.addAttribute("clasificacion", clasificacion);
			return "clasificacionFRM.html";
		}
		
		@PostMapping("/clasificacion/guardar")
		public String guardar(@ModelAttribute Clasificacion clasificacion, BindingResult result,Model model){	

	        if(result.hasErrors()){
	        	model.addAttribute("titulo", "Registro de Clasificacion");
	            model.addAttribute("clasificacion", clasificacion);
	            System.out.println("Hubo errores en el formulario");
	            
	            return "clasificacionFRM.html";
	        }
	        clasificacionService.save(clasificacion);
	        System.out.println("Clasificacion guardado exitosamente");
	        return "redirect:/clasificacion";
	    }
		
		@GetMapping("/clasificacion/editar/{id}")
		public String editar(@PathVariable("id") String COD_CLASIFICACION, Model model){

			Clasificacion clasificacion=clasificacionService.findById(COD_CLASIFICACION);
	        
	        model.addAttribute("titulo", "Editar clasificacion");
	        model.addAttribute("clasificacion", clasificacion);

	        return "clasificacionFRM.html";
	    }
		
		@GetMapping("/clasificacion/eliminar/{id}")
	    public String editar(@PathVariable("id") String COD_CLASIFICACION){

			clasificacionService.softDelete(COD_CLASIFICACION);

			return "redirect:/clasificacion";
	    }
		
		@GetMapping("/clasificaciondetalle/{id}")
		public String ver(@PathVariable("id") String COD_CATEGORIA, Map<String, Object> model) {
			Clasificacion clasificacion = clasificacionService.findById(COD_CATEGORIA);
			if(clasificacion==null) {
				return "redirect:/clasificacionListar";			
			}
			model.put("clasificacion",clasificacion);
			model.put("titulo", "DETALLE DE CATEGORIA: "+clasificacion.getNOMBRE());
			return "clasificacionDetalle.html";		
		}
}
