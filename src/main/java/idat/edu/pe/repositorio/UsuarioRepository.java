package idat.edu.pe.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idat.edu.pe.entidad.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Usuario findByUsuario(String usuario);
}
