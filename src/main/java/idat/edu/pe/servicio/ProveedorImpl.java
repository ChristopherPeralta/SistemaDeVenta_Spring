package idat.edu.pe.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.edu.pe.entidad.Proveedor;
import idat.edu.pe.repositorio.ProveedorRepository;

@Service
public class ProveedorImpl implements ProveedorService{

	@Autowired
	private ProveedorRepository repositorio;

	@Override
	public List<Proveedor> listaProveedor() {
		return repositorio.findAll();
	}

	@Override
	public void save(Proveedor COD) {
		repositorio.save(COD);
	}

	@Override
	public Proveedor findById(String COD) {
		return repositorio.findById(COD).orElse(null);
	}

	@Override
	public void deleteById(String COD) {
		repositorio.deleteById(COD);		
	}
	
}
