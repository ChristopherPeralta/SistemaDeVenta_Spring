package idat.edu.pe.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idat.edu.pe.entidad.MetodoPago;

@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPago, String> {

	
}
