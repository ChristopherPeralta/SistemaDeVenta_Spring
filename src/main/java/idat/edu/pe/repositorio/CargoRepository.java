package idat.edu.pe.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idat.edu.pe.entidad.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, String> {
}