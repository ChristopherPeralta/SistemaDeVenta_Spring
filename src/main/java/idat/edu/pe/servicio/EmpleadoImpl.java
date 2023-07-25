package idat.edu.pe.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.edu.pe.entidad.Empleado;
import idat.edu.pe.repositorio.EmpleadoRepository;


@Service
public class EmpleadoImpl implements EmpleadoService{

	@Autowired
	private EmpleadoRepository repositorio;
	
	
	@Override
	public List<Empleado> listaEmpleados() {
		return repositorio.findAll();
	}

	@Override
    public void save(Empleado COD) {
		repositorio.save(COD);
    }

    @Override
    public Empleado findById(String COD) {
        return repositorio.findById(COD).orElse(null);
    }

    @Override
    public void deleteById(String COD) {
    	repositorio.deleteById(COD);
    }
}
	

	
