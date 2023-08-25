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

import idat.edu.pe.entidad.Cliente;
import idat.edu.pe.entidad.Distrito;
import idat.edu.pe.entidad.Empleado;
import idat.edu.pe.entidad.TipoDocumento;
import idat.edu.pe.servicio.ClienteService;
import idat.edu.pe.servicio.DistritoService;
import idat.edu.pe.servicio.TipoDocumentoService;

@Controller
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private DistritoService distritoService;
	
	@Autowired
	private TipoDocumentoService tipoDocumentoService;	
	
	
	@RequestMapping("/cliente")
	public String listaClientes(Model model) {
		List<Cliente> clientesActivos = clienteService.listaClientes().stream()
                .filter(cliente -> "Activo".equals(cliente.getESTADO()))
                .collect(Collectors.toList());
		List<Distrito>listDistrito=distritoService.listaDistrito();
		List<TipoDocumento>listTipoDocumento=tipoDocumentoService.listaTipoDocumento();

		
			model.addAttribute("titulo", "Lista de Clientes");
			model.addAttribute("clientes", clientesActivos);
			model.addAttribute("distritos", listDistrito);
			model.addAttribute("tipoDocumentos", listTipoDocumento);


		return "clientes.html";
	}
	
	@GetMapping("/cliente/registrar")
	public String crearClienteFormulario(Model model) {
		List<Distrito>listDistrito=distritoService.listaDistrito();
		List<TipoDocumento>listTipoDocumento=tipoDocumentoService.listaTipoDocumento();
		
		Cliente cliente = new Cliente();
		cliente.setESTADO("Activo");
		model.addAttribute("titulo", "Nuevo Cliente");
		model.addAttribute("cliente", cliente);
		model.addAttribute("distritos", listDistrito);
		model.addAttribute("tipoDocumentos", listTipoDocumento);
		return "clienteFRM.html";
	}
	
	@PostMapping("/cliente/guardar")
	public String guardar(@ModelAttribute Cliente cliente, BindingResult result,Model model){	
		List<Distrito>listDistrito=distritoService.listaDistrito();
		List<TipoDocumento>listTipoDocumento=tipoDocumentoService.listaTipoDocumento();


        if(result.hasErrors()){
        	model.addAttribute("titulo", "Registro de Cliente");
            model.addAttribute("cliente", cliente);
            model.addAttribute("distritos", listDistrito);
            model.addAttribute("tipoDocumentos", listTipoDocumento);
            System.out.println("Hubo errores en el formulario");
            
            return "clienteFRM.html";
        }
        clienteService.save(cliente);
        System.out.println("Cliente guardado exitosamente");
        return "redirect:/cliente";
    }
	
	@GetMapping("/cliente/editar/{id}")
	public String editar(@PathVariable("id") String COD_CLIENTE, Model model){

        Cliente cliente=clienteService.findById(COD_CLIENTE);
        List<Distrito>listDistrito=distritoService.listaDistrito();
        List<TipoDocumento>listTipoDocumento=tipoDocumentoService.listaTipoDocumento();
        
        model.addAttribute("titulo", "Editar Cliente");
        model.addAttribute("cliente", cliente);
        model.addAttribute("distritos", listDistrito);
        model.addAttribute("tipoDocumentos", listTipoDocumento);

        return "clienteFRM.html";
    }
	
	@GetMapping("/cliente/eliminar/{id}")
    public String editar(@PathVariable("id") String COD_CLIENTE){

		clienteService.softDelete(COD_CLIENTE);

		return "redirect:/cliente";
    }
	
	@GetMapping("/clientedetalle/{id}")
	public String ver(@PathVariable("id") String COD_CLIENTE, Map<String, Object> model) {
			Cliente cliente = clienteService.findById(COD_CLIENTE);
		if(cliente==null) {
			return "redirect:/clienteListar";			
		}
		model.put("cliente",cliente);
		model.put("titulo", "DETALLE DEL CLIENTE: "+cliente.getNOMBRE());
		return "clienteDetalle.html";		
	}
	
	@GetMapping("/cliente/buscar")
	public String buscar(
            @RequestParam(name = "codigoDNIoNombre", required = false) String codigoDNIoNombre,
            @RequestParam(name = "codigoDistrito", required = false) String codigoDistrito,
            @RequestParam(name = "codigoTipoDocumento", required = false) String codigoTipoDocumento,
            Model model) {
        List<Cliente> encontrados = new ArrayList<>();
        List<Distrito> listDistrito = distritoService.listaDistrito();
		List<TipoDocumento>listTipoDocumento=tipoDocumentoService.listaTipoDocumento();


        if (codigoDNIoNombre != null) {
            if (codigoDistrito != null && !codigoDistrito.isEmpty()) {
                // Filtrar por distrito y criterio
                encontrados = clienteService.buscarPorCodigoDNIoNombreYDistrito(
                        codigoDNIoNombre, codigoDistrito);
            } else {
                // Filtrar solo por criterio
                encontrados = clienteService.buscarPorCriterio(codigoDNIoNombre);
            }

            if (codigoTipoDocumento != null && !codigoTipoDocumento.isEmpty()) {
                // Filtrar adicionalmente por tipo de documento si está presente
                encontrados = encontrados.stream()
                        .filter(cliente -> cliente.getCOD_TIPO_DOCUMENTO().getCOD_TIPO_DOCUMENTO().equals(codigoTipoDocumento))
                        .collect(Collectors.toList());
            }
        }

        model.addAttribute("clientes", encontrados);
        model.addAttribute("distritos", listDistrito);
        model.addAttribute("tipoDocumentos", listTipoDocumento);
        model.addAttribute("titulo", "Resultado de búsqueda de clientes");
        return "clientes.html";
    }

}
