package idat.edu.pe.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idat.edu.pe.entidad.Proveedor;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, String>{

}
