package idat.edu.pe.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.edu.pe.entidad.Cliente;
import idat.edu.pe.entidad.Producto;
import idat.edu.pe.repositorio.ProductoRepository;

@Service
public class ProductoImpl implements ProductoService {

	@Autowired
	private ProductoRepository repositorio;

	@Override
	public List<Producto> listaProductos() {
		return repositorio.findAll();
	}

	@Override
	public void save(Producto COD) {
		repositorio.save(COD);
	}

	@Override
	public Producto findById(String COD) {
		return repositorio.findById(COD).orElse(null);
	}

	@Override
	public void softDelete(String COD) {
		Producto producto = repositorio.findById(COD).orElse(null);
        if (producto != null) {
        	producto.setESTADO("Inactivo");
            repositorio.save(producto);
        }
	}
	
	 @Override
    public List<Producto> buscarPorCriterio(String codigoNombre) {
        return repositorio.buscarPorCodigoNombre(codigoNombre);
    }
    
     @Override
    public List<Producto> buscarPorCodigoNombreYClasificacion(String codigoNombre, String codigoClasificacion) {
        return repositorio.buscarPorCodigoNombreYClasificacion(codigoNombre, codigoClasificacion);
    }
	 
}
