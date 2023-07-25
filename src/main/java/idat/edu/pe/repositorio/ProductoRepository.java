package idat.edu.pe.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idat.edu.pe.entidad.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, String> {

	Producto findFirstByCOD(String COD);

}
