package idat.edu.pe.servicio;

import idat.edu.pe.entidad.Usuario;

public interface UsuarioService {
    Usuario getLogeoByUsuario(String usuario);
    void save(Usuario Usuario);

	public Usuario findById(String COD);

	void deleteById(String COD);
}