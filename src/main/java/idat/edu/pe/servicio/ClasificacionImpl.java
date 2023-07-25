package idat.edu.pe.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.edu.pe.entidad.Clasificacion;
import idat.edu.pe.repositorio.ClasificacionRepository;

@Service
public class ClasificacionImpl implements ClasificacionService{

	@Autowired
	private ClasificacionRepository repository;

	@Override
	public List<Clasificacion> listaClasificacion() {
		return repository.findAll();
	}
	
	
}
