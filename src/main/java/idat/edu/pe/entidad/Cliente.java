package idat.edu.pe.entidad;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "CLIENTE")
public class Cliente {
	
	@Id
    @Column(name = "COD_CLIENTE", length = 6)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generador_cliente") // GENERADOR PARA EL CODIGO
	@GenericGenerator(name="generador_cliente",
	strategy = "idat.edu.pe.entidad.GeneradorCodigo", parameters= { // IMPORTAMOS LA CLASE DEL GENERADOR	
			@Parameter(name = GeneradorCodigo.INCREMENT_PARAM, value = "1"),  // EMPEZARA EN EL NUMERO 1 Y AVANZARA DE UNO EN UNO
			@Parameter(name = GeneradorCodigo.VALUE_PREFIX_PARAMETER, value = "CL"),  // EL PREFIJO SERA E
			@Parameter(name = GeneradorCodigo.NUMBER_FORMAT_PARAMETER, value = "%04d")})// LOS DIGITOS SERAN 4 ( MAXIMO = 9999 )	
    private String COD_CLIENTE;
    
	@Column(name = "NUMERO_DOCUMENTO", length = 40)
    private String NUMERO_DOCUMENTO;
    
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
    
    @ManyToOne()
    @JoinColumn(name = "COD_TIPO_DOCUMENTO")
    private TipoDocumento COD_TIPO_DOCUMENTO;
    
    @OneToMany(mappedBy = "COD_CLIENTE", cascade = CascadeType.ALL)
	private List<Venta> venta;

	
	public String getCOD_CLIENTE() {
		return COD_CLIENTE;
	}

	public void setCOD_CLIENTE(String cOD_CLIENTE) {
		COD_CLIENTE = cOD_CLIENTE;
	}
	

	public String getNUMERO_DOCUMENTO() {
		return NUMERO_DOCUMENTO;
	}

	public void setNUMERO_DOCUMENTO(String nUMERO_DOCUMENTO) {
		NUMERO_DOCUMENTO = nUMERO_DOCUMENTO;
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

	public TipoDocumento getCOD_TIPO_DOCUMENTO() {
		return COD_TIPO_DOCUMENTO;
	}

	public void setCOD_TIPO_DOCUMENTO(TipoDocumento cOD_TIPO_DOCUMENTO) {
		COD_TIPO_DOCUMENTO = cOD_TIPO_DOCUMENTO;
	}

    
}
