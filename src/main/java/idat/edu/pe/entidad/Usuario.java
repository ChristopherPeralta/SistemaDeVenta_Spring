package idat.edu.pe.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "USUARIO")
public class Usuario {
	
	
    @Id
    @Column(name = "COD_USUARIO", length = 6)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generador_usuario") // GENERADOR PARA EL CODIGO
	@GenericGenerator(name="generador_usuario",
	strategy = "idat.edu.pe.entidad.GeneradorCodigo", parameters= { // IMPORTAMOS LA CLASE DEL GENERADOR	
			@Parameter(name = GeneradorCodigo.INCREMENT_PARAM, value = "1"),  // EMPEZARA EN EL NUMERO 1 Y AVANZARA DE UNO EN UNO
			@Parameter(name = GeneradorCodigo.VALUE_PREFIX_PARAMETER, value = "US"),  // EL PREFIJO SERA E
			@Parameter(name = GeneradorCodigo.NUMBER_FORMAT_PARAMETER, value = "%04d")})// LOS DIGITOS SERAN 4 ( MAXIMO = 9999 )	
    private String COD_USUARIO;
    
    
    @Column(name = "USUARIO", length = 40, nullable = false, unique = true)
    private String usuario;
    
    @Column(name = "CONTRASEÑA", length = 40, nullable = false)
    private String contraseña;
    
    @ManyToOne
    @JoinColumn(name = "COD_CARGO")
    private Cargo COD_CARGO;

    @ManyToOne
    @JoinColumn(name = "COD_EMPLEADO")
    private Empleado COD_EMPLEADO;

	public String getCOD_USUARIO() {
		return COD_USUARIO;
	}

	public void setCOD_USUARIO(String cOD_USUARIO) {
		COD_USUARIO = cOD_USUARIO;
	}


	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public Cargo getCOD_CARGO() {
		return COD_CARGO;
	}

	public void setCOD_CARGO(Cargo cOD_CARGO) {
		COD_CARGO = cOD_CARGO;
	}

	public Empleado getCOD_EMPLEADO() {
		return COD_EMPLEADO;
	}

	public void setCOD_EMPLEADO(Empleado cOD_EMPLEADO) {
		COD_EMPLEADO = cOD_EMPLEADO;
	}

    
	
    
    
    
}