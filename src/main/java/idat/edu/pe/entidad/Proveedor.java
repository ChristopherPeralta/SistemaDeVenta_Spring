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
@Table(name = "PROVEEDOR")
public class Proveedor {

	@Id
    @Column(name = "COD_PROVEEDOR", length = 6)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generador_proveedor") // GENERADOR PARA EL CODIGO
	@GenericGenerator(name="generador_proveedor",
	strategy = "idat.edu.pe.entidad.GeneradorCodigo", parameters= { // IMPORTAMOS LA CLASE DEL GENERADOR	
			@Parameter(name = GeneradorCodigo.INCREMENT_PARAM, value = "1"),  // EMPEZARA EN EL NUMERO 1 Y AVANZARA DE UNO EN UNO
			@Parameter(name = GeneradorCodigo.VALUE_PREFIX_PARAMETER, value = "PV"),  // EL PREFIJO SERA E
			@Parameter(name = GeneradorCodigo.NUMBER_FORMAT_PARAMETER, value = "%04d")})// LOS DIGITOS SERAN 4 ( MAXIMO = 9999 )	
    private String COD_PROVEEDOR;
    
	@Column(name = "RUC", length = 40)
    private String RUC;
    
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

	public String getCOD_PROVEEDOR() {
		return COD_PROVEEDOR;
	}

	public void setCOD_PROVEEDOR(String cOD_PROVEEDOR) {
		COD_PROVEEDOR = cOD_PROVEEDOR;
	}

	public String getRUC() {
		return RUC;
	}

	public void setRUC(String rUC) {
		RUC = rUC;
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
