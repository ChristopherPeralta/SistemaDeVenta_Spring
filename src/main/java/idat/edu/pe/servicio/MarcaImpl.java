package idat.edu.pe.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.edu.pe.entidad.Marca;
import idat.edu.pe.repositorio.MarcaRepository;

@Service
public class MarcaImpl implements MarcaService{

	@Autowired
	private MarcaRepository repositorio;

	@Override
	public List<Marca> listaMarca() {
		return repositorio.findAll();
	}

	@Override
	public void save(Marca COD) {
		repositorio.save(COD);		
	}

	@Override
	public Marca findById(String COD) {
		return repositorio.findById(COD).orElse(null);
	}

	@Override
	public void deleteById(String COD) {
		repositorio.deleteById(COD);
		
	}
	
	
}
