package idat.edu.pe.servicio;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import idat.edu.pe.dto.UsuarioRegistroDTO;
import idat.edu.pe.entidad.Rol;
import idat.edu.pe.entidad.Usuario;
import idat.edu.pe.repositorio.UsuarioRepositorio;
import idat.edu.pe.util.TokenUtil;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

	
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
    private UsuarioRepositorio usuarioRepository;
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	public UsuarioServicioImpl(UsuarioRepositorio usuarioRepositorio) {
		super();
		this.usuarioRepositorio = usuarioRepositorio;
	}

	@Override
	public Usuario guardar(UsuarioRegistroDTO registroDTO) {
		Usuario usuario = new Usuario(registroDTO.getNombre(), 
				registroDTO.getApellido(),registroDTO.getEmail(),
				passwordEncoder.encode(registroDTO.getPassword()),Arrays.asList(new Rol("ROLE_USER")));
		return usuarioRepositorio.save(usuario);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepositorio.findByEmail(username);
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuario o password inválidos");
		}
		return new User(usuario.getEmail(),usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
	}
	
	@Override
	public List<Usuario> listarUsuarios() {
		return usuarioRepositorio.findAll();
	}

	@Override
	public Usuario getLogeoByUsuario(String email) {
		return usuarioRepositorio.findByEmail(email);
	}

	@Override
    public void requestPasswordReset(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null) {
            String token = TokenUtil.generateToken();
            LocalDateTime tokenExpiration = LocalDateTime.now().plus(30, ChronoUnit.MINUTES);
            usuario.setToken(token);
            usuario.setTokenExpiration(tokenExpiration);
            usuarioRepository.save(usuario);
            
            sendPasswordResetEmail(usuario.getEmail(), token); // Enviar correo electrónico
        }
    }

    private void sendPasswordResetEmail(String toEmail, String token) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject("Restablecimiento de Contraseña");
            String resetUrl = "http://your-app-url/reset-password?token=" + token; // Cambia la URL a la de tu aplicación
            String emailContent = "Haz clic en el siguiente enlace para restablecer tu contraseña: " + resetUrl;
            helper.setText(emailContent, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(message);
    }

    @Override
    public void resetPassword(Usuario usuario, String newPassword) {
        usuario.setPassword(newPassword);
        usuario.setToken(null);
        usuario.setTokenExpiration(null);
        usuarioRepository.save(usuario);
    }
    
    @Override
    public Usuario findByToken(String token) {
        return usuarioRepository.findByToken(token);
    }

    @Override
    public boolean isTokenValid(Usuario usuario) {
        return usuario.getTokenExpiration() != null && usuario.getTokenExpiration().isAfter(LocalDateTime.now());
    }
    
    @Override
    public List<Usuario> buscarPorCodigoNombreApellido(String codigoNombreApellido) {
        return usuarioRepository.buscarPorCodigoNombreApellido(codigoNombreApellido);
    }
	
	
}
