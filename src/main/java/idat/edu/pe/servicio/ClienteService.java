package idat.edu.pe.servicio;

import java.util.List;

import idat.edu.pe.entidad.Cliente;

public interface ClienteService {
	public List<Cliente> listaClientes();
	
	void save(Cliente cliente);

	public Cliente findById(String COD);

	void softDelete(String COD);
	
	List<Cliente> buscarPorCriterio(String codigoDNIoNombre);

    List<Cliente> buscarPorCodigoDNIoNombreYDistrito(String codigoDNIoNombre, String codigoDistrito);

}
