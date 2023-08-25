package idat.edu.pe.repositorio;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import idat.edu.pe.entidad.Empleado;


@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, String> {
	
	@Query("SELECT e FROM Empleado e WHERE "
	           + "(e.COD_EMPLEADO LIKE %:codigoDNIoNombre% OR e.DNI LIKE %:codigoDNIoNombre% OR e.NOMBRE LIKE %:codigoDNIoNombre%)")
	List<Empleado> buscarPorCodigoDNIoNombre(@Param("codigoDNIoNombre") String codigoDNIoNombre);
	
	@Query("SELECT e FROM Empleado e WHERE "
		       + "(e.COD_EMPLEADO LIKE %:codigoDNIoNombre% OR e.DNI LIKE %:codigoDNIoNombre% OR e.NOMBRE LIKE %:codigoDNIoNombre%)"
		       + "AND (:codigoDistrito IS NULL OR e.COD_DISTRITO.COD_DISTRITO = :codigoDistrito)")
		List<Empleado> buscarPorCodigoDNIoNombreYDistrito(@Param("codigoDNIoNombre") String codigoDNIoNombre, @Param("codigoDistrito") String codigoDistrito);
	
}






	

