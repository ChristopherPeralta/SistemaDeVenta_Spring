package idat.edu.pe.servicio;

import java.util.List;

import idat.edu.pe.entidad.Compra;
import idat.edu.pe.entidad.Proveedor;

public interface CompraService {
	
public List<Compra> listaCompras();
	
	void save(Compra compra);

	public Compra findById(String COD);

	void softDelete(String COD);

	List<Compra> buscarComprasPorCodigoYProveedor(String codigoProveedor);

    List<Compra> buscarComprasPorFecha(String fecha);
}
