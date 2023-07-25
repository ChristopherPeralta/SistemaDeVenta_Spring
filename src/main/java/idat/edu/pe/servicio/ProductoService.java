package idat.edu.pe.servicio;

import java.util.List;

import idat.edu.pe.entidad.Producto;

public interface ProductoService {

	public List<Producto> listaProductos();
	
	void save(Producto producto);

	public Producto findById(String COD);

	void deleteById(String COD);
	
}
