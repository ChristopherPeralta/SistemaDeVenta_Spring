package idat.edu.pe.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import idat.edu.pe.entidad.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String>{
	 // Búsqueda por código DNI o Nombre
    @Query("SELECT e FROM Cliente e WHERE "
            + "(e.COD_CLIENTE LIKE %:codigoDNIoNombre% OR e.NUMERO_DOCUMENTO LIKE %:codigoDNIoNombre% OR e.NOMBRE LIKE %:codigoDNIoNombre%)")
    List<Cliente> buscarPorCodigoDNIoNombre(@Param("codigoDNIoNombre") String codigoDNIoNombre);

    // Búsqueda por código DNI o Nombre y Distrito
    @Query("SELECT e FROM Cliente e WHERE "
            + "(e.COD_CLIENTE LIKE %:codigoDNIoNombre% OR e.NUMERO_DOCUMENTO LIKE %:codigoDNIoNombre% OR e.NOMBRE LIKE %:codigoDNIoNombre%) "
            + "AND (:codigoDistrito IS NULL OR e.COD_DISTRITO.COD_DISTRITO = :codigoDistrito)")
    List<Cliente> buscarPorCodigoDNIoNombreYDistrito(@Param("codigoDNIoNombre") String codigoDNIoNombre, @Param("codigoDistrito") String codigoDistrito);

   
}

