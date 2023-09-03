package idat.edu.pe.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import idat.edu.pe.entidad.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, String>{
	
	@Query("SELECT c FROM Compra c WHERE" 
	           + "(c.COD_COMPRA LIKE %:codigoProveedor% OR c.COD_PROVEEDOR.COD_PROVEEDOR LIKE %:codigoProveedor%)"
			   + "AND (c.ESTADO='Activo')")
	    List<Compra> findComprasByCodigoYProveedor(
	        @Param("codigoProveedor") String codigoProveedor);

	    // Método para buscar por fecha
	    List<Compra> findByFECHA(String fecha);
}


