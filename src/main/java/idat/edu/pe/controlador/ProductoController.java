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

import idat.edu.pe.entidad.Clasificacion;
import idat.edu.pe.entidad.Marca;
import idat.edu.pe.entidad.Producto;
import idat.edu.pe.servicio.ClasificacionService;
import idat.edu.pe.servicio.MarcaService;
import idat.edu.pe.servicio.ProductoService;

@Controller
public class ProductoController {
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private ClasificacionService clasificacionService;
	
	@Autowired
	private MarcaService marcaService;
	
	@RequestMapping("/producto")
	public String listaProductos(Model model) {
		List<Producto>listadoProductos = productoService.listaProductos();
	
		model.addAttribute("titulo", "Lista de Productos");
		model.addAttribute("productos", listadoProductos);
		
		return "productos.html";
		
	}
	
	@GetMapping("/producto/registrar")
	public String crearProductoFormulario(Model model) {
		List<Marca>listMarca=marcaService.listaMarca();
		List<Clasificacion>listClasificacion=clasificacionService.listaClasificacion();
		
		Producto producto = new Producto();
		model.addAttribute("titulo", "Nuevo Producto");
		model.addAttribute("producto", producto);
		model.addAttribute("marcas", listMarca);
		model.addAttribute("clasificaciones", listClasificacion);
		return "productoFRM.html";
	}
	
	@PostMapping("/producto/guardar")
	public String guardar(@ModelAttribute Producto producto, BindingResult result,Model model){	
		List<Marca>listMarca=marcaService.listaMarca();
		List<Clasificacion>listClasificacion=clasificacionService.listaClasificacion();
		

        if(result.hasErrors()){
        	model.addAttribute("titulo", "Registro de Producto");
    		model.addAttribute("producto", producto);
    		model.addAttribute("marcas", listMarca);
    		model.addAttribute("clasificaciones", listClasificacion);
            System.out.println("Hubo errores en el formulario");
            
            return "productoFRM.html";
        }
        productoService.save(producto);
        System.out.println("Producto guardado exitosamente");
        return "redirect:/producto";
    }
	
	@GetMapping("/producto/editar/{id}")
	public String editar(@PathVariable("id") String COD, Model model){

        Producto producto=productoService.findById(COD);
        List<Marca>listMarca=marcaService.listaMarca();
		List<Clasificacion>listClasificacion=clasificacionService.listaClasificacion();
		
        model.addAttribute("titulo", "Editar Producto");
        model.addAttribute("producto", producto);
		model.addAttribute("marcas", listMarca);
		model.addAttribute("clasificaciones", listClasificacion);

        return "productoFRM.html";
    }
	
	@GetMapping("/producto/eliminar/{id}")
    public String editar(@PathVariable("id") String COD){

		productoService.deleteById(COD);

		return "redirect:/producto";
    }

}
