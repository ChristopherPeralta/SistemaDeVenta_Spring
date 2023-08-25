package idat.edu.pe.servicio;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import idat.edu.pe.dto.UsuarioRegistroDTO;
import idat.edu.pe.entidad.Usuario;


public interface UsuarioServicio extends UserDetailsService{

	public Usuario guardar(UsuarioRegistroDTO registroDTO);
	
	public List<Usuario> listarUsuarios();

	Usuario getLogeoByUsuario(String email);
	
	void requestPasswordReset(String email);

    void resetPassword(Usuario usuario, String newPassword);
    
    Usuario findByToken(String token);

    boolean isTokenValid(Usuario usuario);

    List<Usuario> buscarPorCodigoNombreApellido(String codigoNombreApellido);
}
