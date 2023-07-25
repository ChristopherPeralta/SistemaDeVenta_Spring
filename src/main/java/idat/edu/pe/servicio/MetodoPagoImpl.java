package idat.edu.pe.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.edu.pe.entidad.MetodoPago;
import idat.edu.pe.repositorio.MetodoPagoRepository;

@Service
public class MetodoPagoImpl implements MetodoPagoService{

	@Autowired
	private MetodoPagoRepository repository;
	
	@Override
	public List<MetodoPago> listaMetodoPago() {
		return repository.findAll();
	}
	
	

}
