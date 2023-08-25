package idat.edu.pe.controlador;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lowagie.text.DocumentException;

import idat.edu.pe.entidad.Venta;
import idat.edu.pe.reporte.VentaExporterPDF;
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
			model.addAttribute("productos", productoService.listaProductos());
			model.addAttribute("ventas", listadoVentas);

		return "ventas.html";
	}
	
	@GetMapping("/exportarPDF")

    public void exportarListadoDeEmpleadosEnPDF(HttpServletResponse response) throws DocumentException, IOException {

        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormatter.format(new Date());
        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Venta_" + fechaActual + ".pdf";
        response.setHeader(cabecera, valor);
        List<Venta> listaVenta = ventaService.listaVentas();
        VentaExporterPDF exporter = new VentaExporterPDF(listaVenta);
        exporter.exportar(response);

    }
	
	
	    
}
