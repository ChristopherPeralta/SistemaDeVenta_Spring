package idat.edu.pe.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.edu.pe.entidad.ProductoComprado;
import idat.edu.pe.repositorio.ProductoCompradoRepository;

@Service
public class ProductoCompradoService {

	@Autowired
	private ProductoCompradoRepository productoscompradosRepositorio;
	
	public List<ProductoComprado> listAll(){
		return productoscompradosRepositorio.findAll();
	}
	
	public void save(ProductoComprado productovendido) {
		productoscompradosRepositorio.save(productovendido);
	}
	
	public ProductoComprado get(String codigo) {
		return productoscompradosRepositorio.findById(codigo).get();
	}
	
	public void delete(String codigo) {
		productoscompradosRepositorio.deleteById(codigo);
	}
}
