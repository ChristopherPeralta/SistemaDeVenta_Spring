package idat.edu.pe.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import idat.edu.pe.entidad.Cliente;
import idat.edu.pe.entidad.Empleado;
import idat.edu.pe.entidad.MetodoPago;
import idat.edu.pe.entidad.Producto;
import idat.edu.pe.entidad.Venta;
import idat.edu.pe.servicio.ClienteService;
import idat.edu.pe.servicio.EmpleadoService;
import idat.edu.pe.servicio.MetodoPagoService;
import idat.edu.pe.servicio.ProductoService;
import idat.edu.pe.servicio.VentaService;

@Controller
public class VentaController {

	@Autowired
	private VentaService ventaService;
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private MetodoPagoService metodoPagoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EmpleadoService empleadoService;
	
	
	
	@RequestMapping("/venta")
	public String listaVentas(Model model) {
		List<Venta>listadoVentas=ventaService.listaVentas();
		
			model.addAttribute("titulo", "Lista de Ventas");
			model.addAttribute("ventas", listadoVentas);

		return "ventas.html";
	}
	
	
	    
	
	@GetMapping("/venta/registrar")
	public String crearVentaFormulario(Model model) {
		List<Producto>listProducto=productoService.listaProductos();
		List<MetodoPago>listMetodoPago=metodoPagoService.listaMetodoPago();
		List<Cliente>listCliente=clienteService.listaClientes();
		List<Empleado>listEmpleado=empleadoService.listaEmpleados();
		
		
		Venta venta = new Venta();
		model.addAttribute("titulo", "Nuevo Venta");
		model.addAttribute("venta", venta);
		model.addAttribute("productos", listProducto);
		model.addAttribute("metodoPagos", listMetodoPago);
		model.addAttribute("clientes", listCliente);
		model.addAttribute("empleados", listEmpleado);
	
		
		Producto producto = new Producto();
		model.addAttribute("producto", producto);
		return "ventasFRM.html";
	}
}
