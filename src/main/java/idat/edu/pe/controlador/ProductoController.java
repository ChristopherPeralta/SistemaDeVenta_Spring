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

import idat.edu.pe.entidad.Clasificacion;
import idat.edu.pe.entidad.Cliente;
import idat.edu.pe.entidad.Distrito;
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
		List<Producto>listadoProductos = productoService.listaProductos().stream()
                .filter(producto -> "Activo".equals(producto.getESTADO()))
                .collect(Collectors.toList());
		List<Marca>listMarca=marcaService.listaMarca();
		List<Clasificacion>listClasificacion=clasificacionService.listaClasificacion();
	
		model.addAttribute("titulo", "Lista de Productos");
		model.addAttribute("productos", listadoProductos);
		model.addAttribute("clasificaciones", listClasificacion);
		model.addAttribute("marcas", listMarca);
		
		return "productos.html";
	}
	
	@GetMapping("/producto/registrar")
	public String crearProductoFormulario(Model model) {
		List<Marca>listMarca=marcaService.listaMarca();
		List<Clasificacion>listClasificacion=clasificacionService.listaClasificacion();
		
		Producto producto = new Producto();
		producto.setESTADO("Activo");
		producto.setSTOCK(0.0f);
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

		productoService.softDelete(COD);

		return "redirect:/producto";
    }
	
	@GetMapping("/productodetalle/{id}")
	public String ver(@PathVariable("id") String COD, Map<String, Object> model) {
		Producto producto = productoService.findById(COD);
		if(producto==null) {
			return "redirect:/productoListar";			
		}
		model.put("producto",producto);
		model.put("titulo", "DETALLE DEL PRODUCTO: "+producto.getDESCRIPCION());
		return "productoDetalle.html";		
	}
	
	@GetMapping("/producto/buscar")
	public String buscar(
            @RequestParam(name = "codigoNombre", required = false) String codigoNombre,
            @RequestParam(name = "codigoClasificacion", required = false) String codigoClasificacion,
            @RequestParam(name = "codigoMarca", required = false) String codigoMarca,
            Model model) {
        List<Producto> encontrados = new ArrayList<>();
        List<Clasificacion>listClasificacion=clasificacionService.listaClasificacion();
		List<Marca>listMarca=marcaService.listaMarca();

        if (codigoNombre != null) {
            if (codigoClasificacion != null && !codigoClasificacion.isEmpty()) {
                // Filtrar por producto y clasificacion
                encontrados = productoService.buscarPorCodigoNombreYClasificacion(
                		codigoNombre, codigoClasificacion);
            } else {
                // Filtrar solo por criterio
                encontrados = productoService.buscarPorCriterio(codigoNombre);
            }

            if (codigoMarca != null && !codigoMarca.isEmpty()) {
                // Filtrar adicionalmente por tipo de marca si está presente
                encontrados = encontrados.stream()
                        .filter(producto -> producto.getCOD_MARCA().getCOD_MARCA().equals(codigoMarca))
                        .collect(Collectors.toList());
            }
        }

        model.addAttribute("productos", encontrados);
        model.addAttribute("clasificaciones", listClasificacion);
		model.addAttribute("marcas", listMarca);
        model.addAttribute("titulo", "Resultado de búsqueda");
        return "productos.html";
    }
}
