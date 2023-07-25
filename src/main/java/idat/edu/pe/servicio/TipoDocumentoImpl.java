package idat.edu.pe.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.edu.pe.entidad.TipoDocumento;
import idat.edu.pe.repositorio.TipoDocumentoRepository;

@Service
public class TipoDocumentoImpl implements TipoDocumentoService{

	@Autowired
	private TipoDocumentoRepository repository;
	
	@Override
	public List<TipoDocumento> listaTipoDocumento() {
		return repository.findAll();
	}
	

}
