package idat.edu.pe.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idat.edu.pe.entidad.ProductoComprado;

@Repository
public interface ProductoCompradoRepository extends JpaRepository<ProductoComprado, String>{

}
