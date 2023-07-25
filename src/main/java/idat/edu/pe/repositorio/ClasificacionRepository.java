package idat.edu.pe.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idat.edu.pe.entidad.Clasificacion;

@Repository
public interface ClasificacionRepository extends JpaRepository<Clasificacion, String>{

}
