package idat.edu.pe.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import idat.edu.pe.entidad.Empleado;
import idat.edu.pe.entidad.Proveedor;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, String>{

	// Búsqueda por código, RUC y nombre
    @Query("SELECT p FROM Proveedor p WHERE "
	           + "(p.COD_PROVEEDOR LIKE %:codigoRUCoNombre% OR p.RUC LIKE %:codigoRUCoNombre% OR p.NOMBRE LIKE %:codigoRUCoNombre%)"
			   + "AND (p.ESTADO='Activo')")
    List<Proveedor> buscarPorCodigoRucNombre(@Param("codigoRUCoNombre") String codigoRUCoNombre);

    // Búsqueda por distrito
    @Query("SELECT p FROM Proveedor p WHERE "
	           	+ "(p.COD_PROVEEDOR LIKE %:codigoRUCoNombre% OR p.RUC LIKE %:codigoRUCoNombre% OR p.NOMBRE LIKE %:codigoRUCoNombre%)"
    			+ "AND (:codigoDistrito IS NULL OR p.COD_DISTRITO.COD_DISTRITO = :codigoDistrito)"
 			    + "AND (p.ESTADO='Activo')")
    			
	List<Proveedor> buscarPorCodigoRUCoNombreYDistrito(@Param("codigoRUCoNombre") String codigoRUCoNombre, @Param("codigoDistrito") String codigoDistrito);
}
