package idat.edu.pe.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idat.edu.pe.entidad.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, String> {

}
