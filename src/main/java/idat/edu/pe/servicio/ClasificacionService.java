package idat.edu.pe.servicio;

import java.util.List;

import idat.edu.pe.entidad.Clasificacion;

public interface ClasificacionService {

	public abstract List<Clasificacion> listaClasificacion();
	
	void save(Clasificacion clasificacion);

	public Clasificacion findById(String COD);

	void softDelete(String COD);
}
