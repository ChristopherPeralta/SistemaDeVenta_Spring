package idat.edu.pe.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import idat.edu.pe.entidad.Empleado;
import idat.edu.pe.entidad.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{

	 Usuario findByEmail(String email);
	    
	 Usuario findByToken(String token);

	@Query("SELECT u FROM Usuario u WHERE "
		          + "(u.nombre LIKE %:codigoNombreApellido% OR u.apellido LIKE %:codigoNombreApellido%)")
	List<Usuario> buscarPorCodigoNombreApellido(@Param("codigoNombreApellido") String codigoNombreApellido);
		

	
}
