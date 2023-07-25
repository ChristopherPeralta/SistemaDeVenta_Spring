package idat.edu.pe.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.edu.pe.entidad.Venta;
import idat.edu.pe.repositorio.VentaRepository;

@Service
public class VentaImpl implements VentaService{

	@Autowired
	public VentaRepository repositorio;
	
	@Override
	public List<Venta> listaVentas() {
		return repositorio.findAll();
	}

	@Override
	public void save(Venta COD) {
		repositorio.save(COD);
	}

	@Override
	public Venta findById(String COD) {
		return repositorio.findById(COD).orElse(null);
	}

	@Override
	public void deleteById(String COD) {
		repositorio.deleteById(COD);		
	}

}
