package idat.edu.pe.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idat.edu.pe.entidad.ProductoVendido;

@Repository
public interface ProductoVendidoRepository extends JpaRepository<ProductoVendido, String>{

	
}
