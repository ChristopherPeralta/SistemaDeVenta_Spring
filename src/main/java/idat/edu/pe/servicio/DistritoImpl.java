package idat.edu.pe.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.edu.pe.entidad.Distrito;
import idat.edu.pe.repositorio.DistritoRepository;


@Service
public class DistritoImpl implements DistritoService {

	@Autowired
	private DistritoRepository repository;

	@Override
	public List<Distrito> listaDistrito() {
		return repository.findAll();
	}
	
	

	

}
