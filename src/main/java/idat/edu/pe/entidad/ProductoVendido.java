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

// CREACION DE LA ENTIDAD Y TABLA PRODUCTOVENDIDO
@Entity
@Table(name="ProductoVendido")
public class ProductoVendido {

	@Id
    @Column(name = "COD_PRODUCTO_VENDIDO", length = 6)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generador_productoVendido") // GENERADOR PARA EL CODIGO
	@GenericGenerator(name="generador_productoVendido",
	strategy = "idat.edu.pe.entidad.GeneradorCodigo", parameters= { // IMPORTAMOS LA CLASE DEL GENERADOR	
			@Parameter(name = GeneradorCodigo.INCREMENT_PARAM, value = "1"),  // EMPEZARA EN EL NUMERO 1 Y AVANZARA DE UNO EN UNO
			@Parameter(name = GeneradorCodigo.VALUE_PREFIX_PARAMETER, value = "PV"),  // EL PREFIJO SERA E
			@Parameter(name = GeneradorCodigo.NUMBER_FORMAT_PARAMETER, value = "%04d")})// LOS DIGITOS SERAN 4 ( MAXIMO = 9999 )	
    private String codigo;
	
	@Column(name = "DESCRIPCION", length = 8)
    private String DESCRIPCION;
    
	@Column(name = "STOCK", length = 40, nullable = false)
    private Float STOCK;
    
    @Column(name = "PRECIO_VENTA", length = 9)
    private Float PRECIO_VENTA;
	
	@ManyToOne
	@JoinColumn(name="CodVenta") // RENOMBRE DE LA COLUMNA POR CODVENTA
	private Venta venta; // ATRIBUTO VENTA
	
	
	
	
	public ProductoVendido(String dESCRIPCION, Float sTOCK, Float pRECIO_VENTA, Venta venta) {
		this.DESCRIPCION = dESCRIPCION;
		this.STOCK = sTOCK;
		this.PRECIO_VENTA = pRECIO_VENTA;
		this.venta = venta;
	}

	public ProductoVendido() {
		
	}
	
	public Float getTotal() {
		return this.STOCK * this.PRECIO_VENTA;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDESCRIPCION() {
		return DESCRIPCION;
	}

	public void setDESCRIPCION(String dESCRIPCION) {
		DESCRIPCION = dESCRIPCION;
	}

	public Float getSTOCK() {
		return STOCK;
	}

	public void setSTOCK(Float sTOCK) {
		STOCK = sTOCK;
	}

	public Float getPRECIO_VENTA() {
		return PRECIO_VENTA;
	}

	public void setPRECIO_VENTA(Float pRECIO_VENTA) {
		PRECIO_VENTA = pRECIO_VENTA;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}
	
	
}
