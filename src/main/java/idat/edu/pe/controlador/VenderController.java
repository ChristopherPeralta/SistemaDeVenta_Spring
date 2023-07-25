package idat.edu.pe.controlador;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import idat.edu.pe.entidad.Cliente;
import idat.edu.pe.entidad.Empleado;
import idat.edu.pe.entidad.MetodoPago;
import idat.edu.pe.entidad.Producto;
import idat.edu.pe.entidad.ProductoParaVender;
import idat.edu.pe.entidad.ProductoVendido;
import idat.edu.pe.entidad.Venta;
import idat.edu.pe.repositorio.ProductoRepository;
import idat.edu.pe.repositorio.ProductoVendidoRepository;
import idat.edu.pe.repositorio.VentaRepository;
import idat.edu.pe.servicio.ClienteService;
import idat.edu.pe.servicio.EmpleadoService;
import idat.edu.pe.servicio.MetodoPagoService;

@Controller
@RequestMapping(path = "/vender")
public class VenderController {

	@Autowired
    private ProductoRepository productosRepository;
    
    @Autowired
    private VentaRepository ventasRepository;
    
    @Autowired
    private ProductoVendidoRepository productosVendidosRepository;
    
    @Autowired
    private EmpleadoService empleadoServicio;
    
    @Autowired
    private ClienteService clienteServicio;
    
    @Autowired
    private MetodoPagoService metodoServicio;
	
    @PostMapping(value = "/quitar/{indice}")
    public String quitarDelCarrito(@PathVariable int indice, HttpServletRequest request) {
        ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
        if (carrito != null && carrito.size() > 0 && carrito.get(indice) != null) {
            carrito.remove(indice);
            this.guardarCarrito(carrito, request);
        }
        return "redirect:/vender/";
    }
    
    private void limpiarCarrito(HttpServletRequest request) {
        this.guardarCarrito(new ArrayList<>(), request);
    }
	
    @GetMapping(value = "/limpiar")
    public String cancelarVenta(HttpServletRequest request, RedirectAttributes redirectAttrs) {
        this.limpiarCarrito(request);
        redirectAttrs
                .addFlashAttribute("mensaje", "Venta cancelada")
                .addFlashAttribute("clase", "info");
        return "redirect:/vender/";
    }
    
    @PostMapping(value = "/terminar")
    public String terminarVenta(Venta venta, HttpServletRequest request, RedirectAttributes redirectAttrs) {
        ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
        

        if (carrito == null || carrito.size() <= 0) {
            return "redirect:/vender/";
        }
        Venta v = ventasRepository.save(venta);

        for (ProductoParaVender productoParaVender : carrito) {

            Producto p = productosRepository.findFirstByCOD(productoParaVender.getCOD());
            if (p == null) continue; 
            //p.restarStock(productoParaVender.getCantidad());
            productosRepository.save(p);
            ProductoVendido productoVendido = new ProductoVendido(
            		productoParaVender.getDESCRIPCION(),
            		productoParaVender.getSTOCK(), 
            		productoParaVender.getPRECIO_VENTA(),
            		v);
            productosVendidosRepository.save(productoVendido);
        }

        this.limpiarCarrito(request);
        redirectAttrs
                .addFlashAttribute("mensaje", "Venta realizada correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/vender/";
    }
	
    @GetMapping(value = "/")
    public String interfazVender(Model model, HttpServletRequest request) {
    	List<MetodoPago> listaPagos = metodoServicio.listaMetodoPago();
    	List<Empleado> listaEmpleados = empleadoServicio.listaEmpleados();
    	List<Cliente> listaClientes = clienteServicio.listaClientes();
    	model.addAttribute("venta", new Venta());
        model.addAttribute("producto", new Producto());
        float total = 0;
        ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
        for (ProductoParaVender p: carrito) total += p.getTotal();
        model.addAttribute("titulo", "REGISTRAR VENTA");
        model.addAttribute("total", total);
        model.addAttribute("productos", productosRepository.findAll());
        model.addAttribute("pagos", listaPagos);
        model.addAttribute("empleados", listaEmpleados);
        model.addAttribute("clientes", listaClientes);
        return "vender.html";
    }
	
	
    private ArrayList<ProductoParaVender> obtenerCarrito(HttpServletRequest request) {
        @SuppressWarnings("unchecked")
		ArrayList<ProductoParaVender> carrito = (ArrayList<ProductoParaVender>) request.getSession().getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
        }
        return carrito;
    }

    private void guardarCarrito(ArrayList<ProductoParaVender> carrito, HttpServletRequest request) {
        request.getSession().setAttribute("carrito", carrito);
    }
    
    @PostMapping(value = "/agregar")
    public String agregarAlCarrito(@ModelAttribute Producto producto, HttpServletRequest request, 
    		RedirectAttributes redirectAttrs, Model model) {
        ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
        Producto productoBuscadoPorCodigo = productosRepository.findFirstByCOD(producto.getCOD());

        if (productoBuscadoPorCodigo.sinStock()) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "El producto está agotado")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/vender/";
        }
        boolean encontrado = false;
        for (ProductoParaVender productoParaVenderActual : carrito) {
            if (productoParaVenderActual.getCOD().equals(productoBuscadoPorCodigo.getCOD())) {
                productoParaVenderActual.aumentarCantidad();
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            carrito.add(new ProductoParaVender(
            		productoBuscadoPorCodigo.getCOD(), 
            		productoBuscadoPorCodigo.getDESCRIPCION(), 
            		productoBuscadoPorCodigo.getPRECIO_VENTA(),  
            		productoBuscadoPorCodigo.getSTOCK(), 
            		1));
        }
        this.guardarCarrito(carrito, request);
        return "redirect:/vender/";
    }
	
}
