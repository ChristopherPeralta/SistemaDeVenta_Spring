package idat.edu.pe.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.edu.pe.entidad.Cliente;
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
	public void deleteById(String COD) {
		repositorio.deleteById(COD);
	}
	
	

}
