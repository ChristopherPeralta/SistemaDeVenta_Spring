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
@Table(name="ProductoComprado")
public class ProductoComprado {

	@Id
    @Column(name = "COD_PRODUCTO_COMPRADO", length = 6)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generador_productoComprado") // GENERADOR PARA EL CODIGO
	@GenericGenerator(name="generador_productoComprado",
	strategy = "idat.edu.pe.entidad.GeneradorCodigo", parameters= { // IMPORTAMOS LA CLASE DEL GENERADOR	
			@Parameter(name = GeneradorCodigo.INCREMENT_PARAM, value = "1"),  // EMPEZARA EN EL NUMERO 1 Y AVANZARA DE UNO EN UNO
			@Parameter(name = GeneradorCodigo.VALUE_PREFIX_PARAMETER, value = "PC"),  // EL PREFIJO SERA E
			@Parameter(name = GeneradorCodigo.NUMBER_FORMAT_PARAMETER, value = "%04d")})// LOS DIGITOS SERAN 4 ( MAXIMO = 9999 )	
    private String codigo;
	
	@Column(name = "DESCRIPCION", length = 40)
    private String DESCRIPCION;
    
	@Column(name = "cantidad", length = 40, nullable = false)
    private int cantidad;
    
    @Column(name = "precio", length = 9)
    private Float precio;
	
	@ManyToOne
	@JoinColumn(name="COD_COMPRA") // RENOMBRE DE LA COLUMNA POR CODVENTA
	private Compra compra; // ATRIBUTO VENTA
	
	public Float getTotal() {
		return this.cantidad * this.precio;
	}
	
	public ProductoComprado(String dESCRIPCION, int cantidad, Float precio, Compra compra) {
		this.DESCRIPCION = dESCRIPCION;
		this.cantidad = cantidad;
		this.precio = precio;
		this.compra = compra;
	}
	
	public ProductoComprado() {
		
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

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}
}
