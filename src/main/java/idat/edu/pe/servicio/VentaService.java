package idat.edu.pe.servicio;


import java.util.List;


import idat.edu.pe.entidad.Venta;


public interface VentaService {

	public List<Venta> listaVentas();
	
	void save(Venta venta);

	public Venta findById(String COD);

	void deleteById(String COD);
	
   
}
