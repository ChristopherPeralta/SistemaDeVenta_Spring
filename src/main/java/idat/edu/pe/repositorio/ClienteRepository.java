package idat.edu.pe.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idat.edu.pe.entidad.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String>{

}
