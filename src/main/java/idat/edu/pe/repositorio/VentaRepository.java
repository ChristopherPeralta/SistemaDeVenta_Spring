package idat.edu.pe.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idat.edu.pe.entidad.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, String>{

}
