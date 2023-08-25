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

import idat.edu.pe.entidad.Cliente;
import idat.edu.pe.entidad.Marca;
import idat.edu.pe.servicio.MarcaService;

@Controller
public class MarcaController {

	@Autowired
	private MarcaService marcaService;
	
	//CRUD 
			@RequestMapping("/marca")
			public String listaMarca(Model model) {
				List<Marca>listadoMarca=marcaService.listaMarca().stream()
		                .filter(marca -> "Activo".equals(marca.getESTADO()))
		                .collect(Collectors.toList());
				
				model.addAttribute("titulo", "Lista de Marca");
				model.addAttribute("marcas", listadoMarca);
				return "/marca.html";
			}
			
			@GetMapping("/marca/registrar")
			public String crearMarcaFormulario(Model model) {
				
				Marca marca = new Marca();
				marca.setESTADO("Activo");
				model.addAttribute("titulo", "Registro de Marca");
				model.addAttribute("marca", marca);
				return "marcaFRM.html";
			}
			
			@PostMapping("/marca/guardar")
			public String guardar(@ModelAttribute Marca marca, BindingResult result,Model model){	

		        if(result.hasErrors()){
		        	model.addAttribute("titulo", "Registro de Marca");
		            model.addAttribute("marca", marca);
		            System.out.println("Hubo errores en el formulario");
		            
		            return "marcaFRM.html";
		        }
		        marcaService.save(marca);
		        System.out.println("Marca guardado exitosamente");
		        return "redirect:/marca";
		    }
			
			@GetMapping("/marca/editar/{id}")
			public String editar(@PathVariable("id") String COD_MARCA, Model model){

				Marca marca=marcaService.findById(COD_MARCA);
		        
		        model.addAttribute("titulo", "Editar clasificacion");
		        model.addAttribute("marca", marca);

		        return "marcaFRM.html";
		    }
			
			@GetMapping("/marca/eliminar/{id}")
		    public String editar(@PathVariable("id") String COD_MARCA){

				marcaService.softDelete(COD_MARCA);

				return "redirect:/marca";
		    }
			
			@GetMapping("/marcadetalle/{id}")
			public String ver(@PathVariable("id") String COD_MARCA, Map<String, Object> model) {
				Marca marca = marcaService.findById(COD_MARCA);
				if(marca==null) {
					return "redirect:/marcaListar";			
				}
				model.put("marca",marca);
				model.put("titulo", "DETALLE DE MARCA: "+marca.getNOMBRE());
				return "marcaDetalle.html";		
			}
			
			
			
			
}
