package idat.edu.pe.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.edu.pe.entidad.Empleado;
import idat.edu.pe.entidad.Proveedor;
import idat.edu.pe.repositorio.ProveedorRepository;

@Service
public class ProveedorImpl implements ProveedorService{

	@Autowired
	private ProveedorRepository repositorio;

	@Override
	public List<Proveedor> listaProveedor() {
		return repositorio.findAll();
	}

	@Override
	public void save(Proveedor COD) {
		repositorio.save(COD);
	}

	@Override
	public Proveedor findById(String COD) {
		return repositorio.findById(COD).orElse(null);
	}

	@Override
	public void softDelete(String COD) {
		Proveedor proveedor = repositorio.findById(COD).orElse(null);
        if (proveedor != null) {
        	proveedor.setESTADO("Inactivo");
            repositorio.save(proveedor);
        }		
	}
	
	@Override
    public List<Proveedor> buscarProveedoresPorCriterio(String codigoRUCoNombre) {
        return repositorio.buscarPorCodigoRucNombre(codigoRUCoNombre);
    }

    @Override
    public List<Proveedor> buscarProveedoresPorCriterioYDistrito(String codigoRUCoNombre, String codigoDistrito) {
        return repositorio.buscarPorCodigoRUCoNombreYDistrito(codigoRUCoNombre, codigoDistrito);
    }

}
