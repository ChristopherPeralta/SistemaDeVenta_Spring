package idat.edu.pe.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import idat.edu.pe.entidad.Compra;
import idat.edu.pe.entidad.Proveedor;
import idat.edu.pe.servicio.CompraService;
import idat.edu.pe.servicio.ProductoService;
import idat.edu.pe.servicio.ProveedorService;

@Controller
public class CompraController {

	@Autowired
	private CompraService compraService;
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private ProveedorService proveedorService;
	
	
	@RequestMapping("/compra")
	public String listaCompras(Model model) {
		List<Compra>listadoCompras=compraService.listaCompras();
		
			model.addAttribute("titulo", "Lista de Compras");
			model.addAttribute("productos", productoService.listaProductos());
			model.addAttribute("compras", listadoCompras);

		return "compras.html";
	}
	
	@GetMapping("/compra/buscar")
	public String buscarCompras(
	        @RequestParam(name = "codigoProveedor", required = false) String codigoProveedor,
	        @RequestParam(name = "fecha", required = false) String fecha,
	        Model model) {
	    List<Compra> comprasEncontrados = new ArrayList<>();
	    List<Proveedor> listProveedor = proveedorService.listaProveedor();

	    if (codigoProveedor != null) {
	        if (fecha != null && !fecha.isEmpty()) {
	            // Buscar compras por código de proveedor y fecha
	            comprasEncontrados = compraService.buscarComprasPorCodigoYProveedor(codigoProveedor);
	        } else {
	            // Buscar compras solo por código de proveedor
	            comprasEncontrados = compraService.buscarComprasPorCodigoYProveedor(codigoProveedor);
	        }
	    } else if (fecha != null && !fecha.isEmpty()) {
	        // Buscar compras solo por fecha
	        comprasEncontrados = compraService.buscarComprasPorFecha(fecha);
	    }

	    model.addAttribute("compras", comprasEncontrados);
	    model.addAttribute("proveedores", listProveedor); // Corregir el nombre de la lista de proveedores
	    model.addAttribute("titulo", "Resultado de búsqueda Compra");
	    
	    // Devolver la vista adecuada (reemplaza "nombre_de_la_vista" con el nombre de tu vista)
	    return "compras.html";
 }
}
