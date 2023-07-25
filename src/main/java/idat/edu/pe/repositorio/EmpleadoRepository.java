package idat.edu.pe.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import idat.edu.pe.entidad.Empleado;


@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, String> {
	
	
	
}






	

