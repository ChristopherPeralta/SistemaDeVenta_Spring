package idat.edu.pe.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.edu.pe.entidad.ProductoVendido;
import idat.edu.pe.repositorio.ProductoVendidoRepository;

@Service
public class ProductoVendidoService {

	@Autowired
	private ProductoVendidoRepository productosvendidosRepositorio;
	
	public List<ProductoVendido> listAll(){
		return productosvendidosRepositorio.findAll();
	}
	
	public void save(ProductoVendido productovendido) {
		productosvendidosRepositorio.save(productovendido);
	}
	
	public ProductoVendido get(String codigo) {
		return productosvendidosRepositorio.findById(codigo).get();
	}
	
	public void delete(String codigo) {
		productosvendidosRepositorio.deleteById(codigo);
	}
}
