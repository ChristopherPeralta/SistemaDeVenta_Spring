package idat.edu.pe.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

import idat.edu.pe.entidad.Compra;
import idat.edu.pe.entidad.Empleado;
import idat.edu.pe.entidad.MetodoPago;
import idat.edu.pe.entidad.Producto;
import idat.edu.pe.entidad.ProductoComprado;
import idat.edu.pe.entidad.ProductoParaComprar;
import idat.edu.pe.entidad.Proveedor;
import idat.edu.pe.repositorio.CompraRepository;
import idat.edu.pe.repositorio.ProductoCompradoRepository;
import idat.edu.pe.repositorio.ProductoRepository;
import idat.edu.pe.servicio.EmpleadoService;
import idat.edu.pe.servicio.MetodoPagoService;
import idat.edu.pe.servicio.ProductoService;
import idat.edu.pe.servicio.ProveedorService;

@Controller
@RequestMapping(path = "/comprar")
public class ComprarController {

	@Autowired
    private ProductoRepository productosRepository;
    
    @Autowired
    private CompraRepository comprasRepository;
    
    @Autowired
    private ProductoCompradoRepository productosComprasRepository;
    
    @Autowired
    private EmpleadoService empleadoServicio;
    
    @Autowired
    private ProveedorService proveedorServicio;
    
    @Autowired
    private MetodoPagoService metodoServicio;
    
    @Autowired
    private ProductoService productoServicio;
    
    @PostMapping(value = "/quitar/{indice}")
    public String quitarDelCarrito(@PathVariable int indice, HttpServletRequest request) {
        ArrayList<ProductoParaComprar> carrito = this.obtenerCarrito(request);
        if (carrito != null && carrito.size() > 0 && carrito.get(indice) != null) {
            carrito.remove(indice);
            this.guardarCarrito(carrito, request);
        }
        return "redirect:/comprar/";
    }
    
    private void guardarCarrito(ArrayList<ProductoParaComprar> carrito, HttpServletRequest request) {
        request.getSession().setAttribute("carrito", carrito);
    }
    
    private ArrayList<ProductoParaComprar> obtenerCarrito(HttpServletRequest request) {
        @SuppressWarnings("unchecked")
		ArrayList<ProductoParaComprar> carrito = (ArrayList<ProductoParaComprar>) request.getSession().getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
        }
        return carrito;
    }
    
    private void limpiarCarrito(HttpServletRequest request) {
        this.guardarCarrito(new ArrayList<>(), request);
    }
    
    @GetMapping(value = "/limpiar")
    public String cancelarComprar(HttpServletRequest request, RedirectAttributes redirectAttrs) {
        this.limpiarCarrito(request);
        redirectAttrs
                .addFlashAttribute("mensaje", "Compra cancelada")
                .addFlashAttribute("clase", "info");
        return "redirect:/comprar/";
    }
    
    @PostMapping(value = "/terminar")
    public String terminarCompra(Compra compra, HttpServletRequest request, RedirectAttributes redirectAttrs) {
        ArrayList<ProductoParaComprar> carrito = this.obtenerCarrito(request);
        

        if (carrito == null || carrito.size() <= 0) {
            return "redirect:/comprar/";
        }
        Compra c = comprasRepository.save(compra);

        for (ProductoParaComprar productoParaComprar : carrito) {
        	
            Producto p = productosRepository.findFirstByCOD(productoParaComprar.getCOD());
            if (p == null) continue; 
            p.sumarStock(productoParaComprar.getCantidad());
            productosRepository.save(p);
            
            ProductoComprado productoComprado = new ProductoComprado(
            		productoParaComprar.getDESCRIPCION(),
            		productoParaComprar.getCantidad(),
            		productoParaComprar.getSTOCK(),
            		c);
            productosComprasRepository.save(productoComprado);
        }

        this.limpiarCarrito(request);
        redirectAttrs
                .addFlashAttribute("mensaje", "Compra realizada correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/comprar/";
    }
    
    @GetMapping(value = "/")
    public String interfazVender(Model model, HttpServletRequest request) {
    	List<MetodoPago> listaPagos = metodoServicio.listaMetodoPago();
    	List<Empleado> listaEmpleados = empleadoServicio.listaEmpleados().stream()
                .filter(empleado -> "Activo".equals(empleado.getESTADO()))
                .collect(Collectors.toList());
    	List<Proveedor> listaProveedores = proveedorServicio.listaProveedor().stream()
                .filter(proveedor -> "Activo".equals(proveedor.getESTADO()))
                .collect(Collectors.toList());
    	List<Producto> listaProducto = productoServicio.listaProductos().stream()
                .filter(producto -> "Activo".equals(producto.getESTADO()))
                .collect(Collectors.toList());
    	
    	model.addAttribute("compra", new Compra());
        model.addAttribute("producto", new Producto());
        float total = 0;
        ArrayList<ProductoParaComprar> carrito = this.obtenerCarrito(request);
        for (ProductoParaComprar p: carrito) total += p.getTotal();
        model.addAttribute("titulo", "REGISTRAR COMPRA");
        model.addAttribute("total", total);
        model.addAttribute("productos", listaProducto);
        model.addAttribute("pagos", listaPagos);
        model.addAttribute("empleados", listaEmpleados);
        model.addAttribute("proveedores", listaProveedores);
        return "comprar.html";
    }
    
    @PostMapping(value = "/agregar")
    public String agregarAlCarrito(@ModelAttribute Producto producto, HttpServletRequest request, 
    		RedirectAttributes redirectAttrs, Model model) {
        ArrayList<ProductoParaComprar> carrito = this.obtenerCarrito(request);
        Producto productoBuscadoPorCodigo = productosRepository.findFirstByCOD(producto.getCOD());

        boolean encontrado = false;
        for (ProductoParaComprar productoParaComprar : carrito) {
            if (productoParaComprar.getCOD().equals(productoBuscadoPorCodigo.getCOD())) {
            	productoParaComprar.aumentarCantidad();
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            carrito.add(new ProductoParaComprar(
            		productoBuscadoPorCodigo.getCOD(), 
            		productoBuscadoPorCodigo.getDESCRIPCION(),
            		productoBuscadoPorCodigo.getPRECIO_COMPRA(),
            		productoBuscadoPorCodigo.getSTOCK(), 
            		1));
        }
        this.guardarCarrito(carrito, request);
        return "redirect:/comprar/";
    }
    
    
	
}
