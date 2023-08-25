package idat.edu.pe.entidad;

import java.util.Set;

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
@Table(name="COMPRA")
public class Compra {

	@Id
    @Column(name = "COD_COMPRA", length = 6)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generador_compra") // GENERADOR PARA EL CODIGO
	@GenericGenerator(name="generador_compra",
	strategy = "idat.edu.pe.entidad.GeneradorCodigo", parameters= { // IMPORTAMOS LA CLASE DEL GENERADOR	
			@Parameter(name = GeneradorCodigo.INCREMENT_PARAM, value = "1"),  // EMPEZARA EN EL NUMERO 1 Y AVANZARA DE UNO EN UNO
			@Parameter(name = GeneradorCodigo.VALUE_PREFIX_PARAMETER, value = "CO"),  // EL PREFIJO SERA E
			@Parameter(name = GeneradorCodigo.NUMBER_FORMAT_PARAMETER, value = "%04d")})// LOS DIGITOS SERAN 4 ( MAXIMO = 9999 )	
    private String COD_COMPRA;
	
	@Column(name = "FECHA", length = 40)
    private String FECHA;
	
	@Column(name = "IMPORTE_TOTAL", length = 40)
    private String IMPORTE_TOTAL;
	
	@Column(name = "ESTADO", length = 8 )
    private String ESTADO;
	
	@ManyToOne()
	@JoinColumn(name="COD_EMPLEADO") 
	private Empleado COD_EMPLEADO; 
	
	@ManyToOne()
	@JoinColumn(name="COD_PROVEEDOR") 
	private Proveedor COD_PROVEEDOR;
	
	@OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
	private Set<ProductoComprado> productos;

	public Compra() {
		this.FECHA = GeneradorFecha.obtenerFechaYHoraActual();
	}

	public String getCOD_COMPRA() {
		return COD_COMPRA;
	}

	public void setCOD_COMPRA(String cOD_COMPRA) {
		COD_COMPRA = cOD_COMPRA;
	}

	public String getFECHA() {
		return FECHA;
	}

	public void setFECHA(String fECHA) {
		FECHA = fECHA;
	}

	public Float getIMPORTE_TOTAL() {
		Float IMPORTE_TOTAL = 0f;
		for (ProductoComprado productoComprado: this.productos) {
			IMPORTE_TOTAL += productoComprado.getTotal();
		}
		return IMPORTE_TOTAL;
	}

	public void setIMPORTE_TOTAL(String iMPORTE_TOTAL) {
		IMPORTE_TOTAL = iMPORTE_TOTAL;
	}

	public String getESTADO() {
		return ESTADO;
	}

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}

	public Empleado getCOD_EMPLEADO() {
		return COD_EMPLEADO;
	}

	public void setCOD_EMPLEADO(Empleado cOD_EMPLEADO) {
		COD_EMPLEADO = cOD_EMPLEADO;
	}

	public Proveedor getCOD_PROVEEDOR() {
		return COD_PROVEEDOR;
	}

	public void setCOD_PROVEEDOR(Proveedor cOD_PROVEEDOR) {
		COD_PROVEEDOR = cOD_PROVEEDOR;
	}

	public Set<ProductoComprado> getProductos() {
		return productos;
	}

	public void setProductos(Set<ProductoComprado> productos) {
		this.productos = productos;
	}
	
	
}
