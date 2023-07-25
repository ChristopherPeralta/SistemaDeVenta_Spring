package idat.edu.pe.repositorio;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idat.edu.pe.entidad.Distrito;


@Repository
public interface DistritoRepository extends JpaRepository <Distrito, String> {
	
}
