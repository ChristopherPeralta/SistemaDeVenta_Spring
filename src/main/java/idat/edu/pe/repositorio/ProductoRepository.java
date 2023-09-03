package idat.edu.pe.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import idat.edu.pe.entidad.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, String> {

	Producto findFirstByCOD(String COD);

	@Query("SELECT p FROM Producto p WHERE "
	            + "(p.COD LIKE %:codigoNombre% OR p.DESCRIPCION LIKE %:codigoNombre%) "
	            + "AND (:codigoClasificacion IS NULL OR p.COD_CLASIFICACION.COD_CLASIFICACION = :codigoClasificacion)"
				+ "AND (p.ESTADO='Activo')")
	    List<Producto> buscarPorCodigoNombreYClasificacion(@Param("codigoNombre") String codigoNombre, @Param("codigoClasificacion") String codigoClasificacion);
	  
	@Query("SELECT p FROM Producto p WHERE "
	            + "(p.COD LIKE %:codigoNombre% OR p.DESCRIPCION LIKE %:codigoNombre%) "
				+ "AND (p.ESTADO='Activo')")
	    List<Producto> buscarPorCodigoNombre(@Param("codigoNombre") String codigoNombre);

}
