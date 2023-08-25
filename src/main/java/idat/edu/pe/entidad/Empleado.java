package idat.edu.pe.entidad;


import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


@Entity
@Table(name = "EMPLEADO")
public class Empleado {
	
	
    @Id
    @Column(name = "COD_EMPLEADO", length = 6)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generador_empleado") // GENERADOR PARA EL CODIGO
	@GenericGenerator(name="generador_empleado",
	strategy = "idat.edu.pe.entidad.GeneradorCodigo", parameters= { // IMPORTAMOS LA CLASE DEL GENERADOR	
			@Parameter(name = GeneradorCodigo.INCREMENT_PARAM, value = "1"),  // EMPEZARA EN EL NUMERO 1 Y AVANZARA DE UNO EN UNO
			@Parameter(name = GeneradorCodigo.VALUE_PREFIX_PARAMETER, value = "EM"),  // EL PREFIJO SERA E
			@Parameter(name = GeneradorCodigo.NUMBER_FORMAT_PARAMETER, value = "%04d")})// LOS DIGITOS SERAN 4 ( MAXIMO = 9999 )	
    private String COD_EMPLEADO;
    
    @Column(name = "DNI", length = 8)
    private String DNI;
    
    @Column(name = "NOMBRE", length = 40, nullable = false)
    private String NOMBRE;
    
    @Column(name = "TELEFONO", length = 9)
    private String TELEFONO;
    
    @Column(name = "DIRECCION", length = 40)
    private String DIRECCION;
    
    @Column(name = "EMAIL", length = 40)
    private String EMAIL;
    
    @Column(name = "ESTADO", length = 8 )
    private String ESTADO;
    
    @ManyToOne()
    @JoinColumn(name = "COD_DISTRITO")
    private Distrito COD_DISTRITO;
    
    @OneToMany(mappedBy = "COD_EMPLEADO", cascade = CascadeType.ALL)
	private Set<Venta> Venta;
    
    /*  @OneToMany(fetch = FetchType.LAZY, mappedBy = "COD_EMPLEADO", cascade = CascadeType.ALL)
    List<Usuario> COD_USUARIO;

	public List<Usuario> getCOD_USUARIO() {
		return COD_USUARIO;
	}

	public void setCOD_USUARIO(List<Usuario> cOD_USUARIO) {
		COD_USUARIO = cOD_USUARIO;
	}*/

	public Set<Venta> getVenta() {
		return Venta;
	}

	public void setVenta(Set<Venta> venta) {
		Venta = venta;
	}

	public String getCOD_EMPLEADO() {
		return COD_EMPLEADO;
	}

	public void setCOD_EMPLEADO(String cOD_EMPLEADO) {
		COD_EMPLEADO = cOD_EMPLEADO;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getNOMBRE() {
		return NOMBRE;
	}

	public void setNOMBRE(String nOMBRE) {
		NOMBRE = nOMBRE;
	}

	public String getTELEFONO() {
		return TELEFONO;
	}

	public void setTELEFONO(String tELEFONO) {
		TELEFONO = tELEFONO;
	}

	public String getDIRECCION() {
		return DIRECCION;
	}

	public void setDIRECCION(String dIRECCION) {
		DIRECCION = dIRECCION;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public String getESTADO() {
		return ESTADO;
	}

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}

	public Distrito getCOD_DISTRITO() {
		return COD_DISTRITO;
	}

	public void setCOD_DISTRITO(Distrito cOD_DISTRITO) {
		COD_DISTRITO = cOD_DISTRITO;
	}

}
    
    