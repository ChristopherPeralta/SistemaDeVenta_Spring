package idat.edu.pe.servicio;

import java.util.List;

import idat.edu.pe.entidad.DetalleVenta;

public interface DetalleVentaService {

public List<DetalleVenta> listaDetalleVentas();
	
	void save(DetalleVenta detalleVenta);

	public DetalleVenta findById(String COD);

	void deleteById(String COD);
}
