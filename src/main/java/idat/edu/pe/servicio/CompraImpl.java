package idat.edu.pe.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.edu.pe.entidad.Compra;
import idat.edu.pe.entidad.Proveedor;
import idat.edu.pe.repositorio.CompraRepository;

@Service
public class CompraImpl implements CompraService{

	@Autowired
	public CompraRepository repositorio;
	
	@Override
	public List<Compra> listaCompras() {
		return repositorio.findAll();
	}

	@Override
	public void save(Compra COD) {
		repositorio.save(COD);
	}

	@Override
	public Compra findById(String COD) {
		return repositorio.findById(COD).orElse(null);
	}

	@Override
	public void softDelete(String COD) {
		Compra compra = repositorio.findById(COD).orElse(null);
		if (compra != null) {
			compra.setESTADO("Inactivo");
            repositorio.save(compra);
        }		
	}

	@Override
	public List<Compra> buscarComprasPorCodigoYProveedor(String codigoProveedor) {
		return repositorio.findComprasByCodigoYProveedor(codigoProveedor);
	}

	@Override
	public List<Compra> buscarComprasPorFecha(String fecha) {
		return repositorio.findByFECHA(fecha);
	}
	
	
	
	

}
