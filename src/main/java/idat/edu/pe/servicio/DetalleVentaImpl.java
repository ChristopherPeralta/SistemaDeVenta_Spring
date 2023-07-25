package idat.edu.pe.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.edu.pe.entidad.DetalleVenta;
import idat.edu.pe.repositorio.DetalleVentaRepository;

@Service
public class DetalleVentaImpl implements DetalleVentaService{

	@Autowired
	public DetalleVentaRepository repositorio;

	@Override
	public List<DetalleVenta> listaDetalleVentas() {
		return repositorio.findAll();
	}

	@Override
	public void save(DetalleVenta COD) {
		repositorio.save(COD);
	}

	@Override
	public DetalleVenta findById(String COD) {
		return repositorio.findById(COD).orElse(null);
	}

	@Override
	public void deleteById(String COD) {
		repositorio.deleteById(COD);		
	}
	

	


}
