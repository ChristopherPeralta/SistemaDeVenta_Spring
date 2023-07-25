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
@Table(name="VENTA")
public class Venta {

	@Id
    @Column(name = "COD_VENTA", length = 6)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generador_venta") // GENERADOR PARA EL CODIGO
	@GenericGenerator(name="generador_venta",
	strategy = "idat.edu.pe.entidad.GeneradorCodigo", parameters= { // IMPORTAMOS LA CLASE DEL GENERADOR	
			@Parameter(name = GeneradorCodigo.INCREMENT_PARAM, value = "1"),  // EMPEZARA EN EL NUMERO 1 Y AVANZARA DE UNO EN UNO
			@Parameter(name = GeneradorCodigo.VALUE_PREFIX_PARAMETER, value = "VE"),  // EL PREFIJO SERA E
			@Parameter(name = GeneradorCodigo.NUMBER_FORMAT_PARAMETER, value = "%04d")})// LOS DIGITOS SERAN 4 ( MAXIMO = 9999 )	
    private String COD_VENTA;
	
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
	@JoinColumn(name="COD_CLIENTE") 
	private Cliente COD_CLIENTE;
	
	@ManyToOne()
	@JoinColumn(name="COD_METODO_PAGO") 
	private MetodoPago COD_METODO_PAGO; 
	
	@OneToMany(mappedBy = "COD_DETALLEVENTA", cascade = CascadeType.ALL)
	private List<DetalleVenta> detalleVenta;

	
	public Float getTotal() {
		Float total = 0f;
		for (DetalleVenta productoVendido: this.detalleVenta) {
			total += productoVendido.getTotal();
		}
		return total;
	}
	
	public Venta() {
		this.FECHA = GeneradorFecha.obtenerFechaYHoraActual();
	}

	
	

	public Empleado getCOD_EMPLEADO() {
		return COD_EMPLEADO;
	}

	public void setCOD_EMPLEADO(Empleado cOD_EMPLEADO) {
		COD_EMPLEADO = cOD_EMPLEADO;
	}

	public Cliente getCOD_CLIENTE() {
		return COD_CLIENTE;
	}

	public void setCOD_CLIENTE(Cliente cOD_CLIENTE) {
		COD_CLIENTE = cOD_CLIENTE;
	}

	public MetodoPago getCOD_METODO_PAGO() {
		return COD_METODO_PAGO;
	}

	public void setCOD_METODO_PAGO(MetodoPago cOD_METODO_PAGO) {
		COD_METODO_PAGO = cOD_METODO_PAGO;
	}

	public String getCOD_VENTA() {
		return COD_VENTA;
	}


	public void setCOD_VENTA(String cOD_VENTA) {
		COD_VENTA = cOD_VENTA;
	}


	public String getFECHA() {
		return FECHA;
	}


	public void setFECHA(String fECHA) {
		FECHA = fECHA;
	}


	public String getIMPORTE_TOTAL() {
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

	public List<DetalleVenta> getDetalleVenta() {
		return detalleVenta;
	}

	public void setDetalleVenta(List<DetalleVenta> detalleVenta) {
		this.detalleVenta = detalleVenta;
	}



	
	
	
	
}
