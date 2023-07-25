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

import idat.edu.pe.entidad.Cliente;
import idat.edu.pe.entidad.Distrito;
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
		List<Cliente>listadoClientes=clienteService.listaClientes();
		
			model.addAttribute("titulo", "Lista de Clientes");
			model.addAttribute("clientes", listadoClientes);

		return "clientes.html";
	}
	
	@GetMapping("/cliente/registrar")
	public String crearClienteFormulario(Model model) {
		List<Distrito>listDistrito=distritoService.listaDistrito();
		List<TipoDocumento>listTipoDocumento=tipoDocumentoService.listaTipoDocumento();
		
		Cliente cliente = new Cliente();
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

		clienteService.deleteById(COD_CLIENTE);

		return "redirect:/cliente";
    }
}
