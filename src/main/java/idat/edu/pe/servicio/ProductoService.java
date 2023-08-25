package idat.edu.pe.servicio;

import java.util.List;

import idat.edu.pe.entidad.Cliente;
import idat.edu.pe.entidad.Producto;

public interface ProductoService {

 List<Producto> listaProductos();
	
	void save(Producto producto);

	public Producto findById(String COD);

	void softDelete(String COD);
	
	List<Producto> buscarPorCriterio(String codigoNombre);
	
    List<Producto> buscarPorCodigoNombreYClasificacion(String codigoNombre, String codigoClasificacion);
}
