package idat.edu.pe.servicio;

import java.util.List;

import idat.edu.pe.entidad.Proveedor;

public interface ProveedorService {

public List<Proveedor> listaProveedor();
	
	void save(Proveedor proveedor);

	public Proveedor findById(String COD);

	void deleteById(String COD);
}
