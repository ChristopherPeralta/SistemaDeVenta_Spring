package idat.edu.pe.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.edu.pe.entidad.Clasificacion;
import idat.edu.pe.repositorio.ClasificacionRepository;

@Service
public class ClasificacionImpl implements ClasificacionService{

	@Autowired
	private ClasificacionRepository repositorio;

	@Override
	public List<Clasificacion> listaClasificacion() {
		return repositorio.findAll();
	}

	@Override
	public void save(Clasificacion COD) {
		repositorio.save(COD);		
	}

	@Override
	public Clasificacion findById(String COD) {
        return repositorio.findById(COD).orElse(null);
	}

	@Override
	public void softDelete(String COD) {
		Clasificacion clasificacion = repositorio.findById(COD).orElse(null);
        if (clasificacion != null) {
        	clasificacion.setESTADO("Inactivo");
            repositorio.save(clasificacion);
        }	}
	
	
}
