package idat.edu.pe.servicio;

import java.util.List;

import idat.edu.pe.entidad.Marca;

public interface MarcaService {

	public abstract List<Marca> listaMarca();
	
	void save(Marca marca);

	public Marca findById(String COD);

	void softDelete(String COD);

}
