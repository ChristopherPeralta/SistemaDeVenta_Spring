package idat.edu.pe.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class DetalleVenta {

	@Id
    @Column(name = "COD_DETALLEVENTA", length = 6)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generador_detalleVenta") // GENERADOR PARA EL CODIGO
	@GenericGenerator(name="generador_detalleVenta",
	strategy = "idat.edu.pe.entidad.GeneradorCodigo", parameters= { // IMPORTAMOS LA CLASE DEL GENERADOR	
			@Parameter(name = GeneradorCodigo.INCREMENT_PARAM, value = "1"),  // EMPEZARA EN EL NUMERO 1 Y AVANZARA DE UNO EN UNO
			@Parameter(name = GeneradorCodigo.VALUE_PREFIX_PARAMETER, value = "DV"),  // EL PREFIJO SERA E
			@Parameter(name = GeneradorCodigo.NUMBER_FORMAT_PARAMETER, value = "%04d")})// LOS DIGITOS SERAN 4 ( MAXIMO = 9999 )	
    private String COD_DETALLEVENTA;
	
	@Column(name = "CANTIDAD", length = 40)
    private int CANTIDAD;
	
	@Column(name = "PRECIO_VENTA", length = 40)
    private float PRECIO_VENTA;
	
	@Column(name = "ESTADO", length = 8 )
    private String ESTADO;
	
	@ManyToOne()
    @JoinColumn(name = "COD")
    private Producto COD;
	
	@ManyToOne()
	@JoinColumn(name="COD_VENTA") 
	private Venta COD_VENTA; 
	
	public Float getTotal() {
        return this.CANTIDAD * this.PRECIO_VENTA;
    }

	public String getCOD_DETALLEVENTA() {
		return COD_DETALLEVENTA;
	}

	public void setCOD_DETALLEVENTA(String cOD_DETALLEVENTA) {
		COD_DETALLEVENTA = cOD_DETALLEVENTA;
	}

	public int getCANTIDAD() {
		return CANTIDAD;
	}

	public void setCANTIDAD(int cANTIDAD) {
		CANTIDAD = cANTIDAD;
	}

	public float getPRECIO_VENTA() {
		return PRECIO_VENTA;
	}

	public void setPRECIO_VENTA(float pRECIO_VENTA) {
		PRECIO_VENTA = pRECIO_VENTA;
	}

	public String getESTADO() {
		return ESTADO;
	}

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}

	

	public Producto getCOD_PRODUCTO() {
		return COD;
	}

	public void setCOD_PRODUCTO(Producto cOD_PRODUCTO) {
		COD = cOD_PRODUCTO;
	}

	public Venta getCOD_VENTA() {
		return COD_VENTA;
	}

	public void setCOD_VENTA(Venta cOD_VENTA) {
		COD_VENTA = cOD_VENTA;
	}
	
	
}
