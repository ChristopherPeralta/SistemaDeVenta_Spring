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
@Table(name = "PRODUCTO")
public class Producto {

	@Id
    @Column(name = "COD", length = 6)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generador_producto") // GENERADOR PARA EL CODIGO
	@GenericGenerator(name="generador_producto",
	strategy = "idat.edu.pe.entidad.GeneradorCodigo", parameters= { // IMPORTAMOS LA CLASE DEL GENERADOR	
			@Parameter(name = GeneradorCodigo.INCREMENT_PARAM, value = "1"),  // EMPEZARA EN EL NUMERO 1 Y AVANZARA DE UNO EN UNO
			@Parameter(name = GeneradorCodigo.VALUE_PREFIX_PARAMETER, value = "PR"),  // EL PREFIJO SERA E
			@Parameter(name = GeneradorCodigo.NUMBER_FORMAT_PARAMETER, value = "%04d")})// LOS DIGITOS SERAN 4 ( MAXIMO = 9999 )	
    private String COD;
	
	@Column(name = "DESCRIPCION", length = 50)
    private String DESCRIPCION;
    
	@Column(name = "STOCK", length = 40, nullable = false)
    private Float STOCK;
    
    @Column(name = "PRECIO_VENTA", length = 9)
    private Float PRECIO_VENTA;
    
    @Column(name = "PRECIO_COMPRA", length = 40)
    private Float PRECIO_COMPRA;
    
    @Column(name = "ESTADO", length = 40)
    private String ESTADO;
    
    @ManyToOne()
    @JoinColumn(name = "COD_CLASIFICACION")
    private Clasificacion COD_CLASIFICACION;
    
    @ManyToOne()
    @JoinColumn(name = "COD_MARCA")
    private Marca COD_MARCA;
    
    @OneToMany(mappedBy = "COD", cascade = CascadeType.ALL)
	private List<DetalleVenta> detalleVenta;

    
    //FUNCIONES
    //public boolean stockmin() {
		//return this.STOCK <= this.minimo;
	//}

	public boolean sinStock() {
		return this.STOCK <= 0;
	}

    public Producto(String cOD_PRODUCTO, String dESCRIPCION, Float sTOCK, Float pRECIO_VENTA) {
		super();
		COD = cOD_PRODUCTO;
		DESCRIPCION = dESCRIPCION;
		STOCK = sTOCK;
		PRECIO_VENTA = pRECIO_VENTA;
	}
    
    public Producto() {
	}

	public String getCOD() {
		return COD;
	}

	public void setCOD(String cOD) {
		COD = cOD;
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

	public Float getPRECIO_COMPRA() {
		return PRECIO_COMPRA;
	}

	public void setPRECIO_COMPRA(Float pRECIO_COMPRA) {
		PRECIO_COMPRA = pRECIO_COMPRA;
	}

	public String getESTADO() {
		return ESTADO;
	}

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}

	public Clasificacion getCOD_CLASIFICACION() {
		return COD_CLASIFICACION;
	}

	public void setCOD_CLASIFICACION(Clasificacion cOD_CLASIFICACION) {
		COD_CLASIFICACION = cOD_CLASIFICACION;
	}

	public Marca getCOD_MARCA() {
		return COD_MARCA;
	}

	public void setCOD_MARCA(Marca cOD_MARCA) {
		COD_MARCA = cOD_MARCA;
	}

	public List<DetalleVenta> getDetalleVenta() {
		return detalleVenta;
	}

	public void setDetalleVenta(List<DetalleVenta> detalleVenta) {
		this.detalleVenta = detalleVenta;
	}

	

	
}
