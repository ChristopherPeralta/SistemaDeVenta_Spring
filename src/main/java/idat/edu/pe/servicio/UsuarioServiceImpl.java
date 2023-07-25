package idat.edu.pe.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.edu.pe.entidad.Usuario;
import idat.edu.pe.repositorio.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario getLogeoByUsuario(String usuario) {
        return usuarioRepository.findByUsuario(usuario);
    }

	@Override
	public void save(Usuario COD) {
		usuarioRepository.save(COD);
	}

	@Override
	public Usuario findById(String COD) {
		return usuarioRepository.findById(COD).orElse(null);
	}

	@Override
	public void deleteById(String COD) {
		usuarioRepository.findById(COD);
	}
}