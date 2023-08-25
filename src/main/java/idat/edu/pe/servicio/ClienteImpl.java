 package idat.edu.pe.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.edu.pe.entidad.Cliente;
import idat.edu.pe.entidad.Empleado;
import idat.edu.pe.repositorio.ClienteRepository;

@Service
public class ClienteImpl implements ClienteService{

	@Autowired
	private ClienteRepository repositorio;
	
	@Override
	public List<Cliente> listaClientes() {
		return repositorio.findAll();
	}

	@Override
	public void save(Cliente COD) {
		repositorio.save(COD);
	}

	@Override
	public Cliente findById(String COD) {
		return repositorio.findById(COD).orElse(null);
	}

	@Override
	public void softDelete(String COD) {
		Cliente cliente = repositorio.findById(COD).orElse(null);
        if (cliente != null) {
        	cliente.setESTADO("Inactivo");
            repositorio.save(cliente);
        }
	}
	
	@Override
    public List<Cliente> buscarPorCriterio(String codigoDNIoNombre) {
        return repositorio.buscarPorCodigoDNIoNombre(codigoDNIoNombre);
    }

    @Override
    public List<Cliente> buscarPorCodigoDNIoNombreYDistrito(String codigoDNIoNombre, String codigoDistrito) {
        return repositorio.buscarPorCodigoDNIoNombreYDistrito(codigoDNIoNombre, codigoDistrito);
    }

}
