package idat.edu.pe.servicio;

import java.util.List;

import idat.edu.pe.entidad.Empleado;
import idat.edu.pe.entidad.Proveedor;

public interface ProveedorService {

	public List<Proveedor> listaProveedor();
	
	void save(Proveedor proveedor);

	public Proveedor findById(String COD);

	void softDelete(String COD);
	
	 List<Proveedor> buscarProveedoresPorCriterio(String codigoRUCoNombre);
	    
	 List<Proveedor> buscarProveedoresPorCriterioYDistrito(String codigoRUCoNombre, String codigoDistrito);
}
