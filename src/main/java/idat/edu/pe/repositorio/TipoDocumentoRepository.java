package idat.edu.pe.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idat.edu.pe.entidad.TipoDocumento;

@Repository
public interface TipoDocumentoRepository extends JpaRepository <TipoDocumento, String> {

}
