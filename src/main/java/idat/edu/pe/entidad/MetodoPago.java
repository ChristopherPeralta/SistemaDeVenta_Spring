package idat.edu.pe.entidad;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name= "METODO_PAGO")
public class MetodoPago {
	@Id
    @Column(name = "COD_METODO_PAGO", length = 6)
    private String COD_METODO_PAGO;
	
	@Column(name= "NOMBRE", length = 40)
	private String NOMBRE;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "COD_VENTA"/* NOMBRE DE LA TABLA */, cascade = CascadeType.ALL)
    List<Venta> venta;

	public String getCOD_METODO_PAGO() {
		return COD_METODO_PAGO;
	}

	public void setCOD_METODO_PAGO(String cOD_METODO_PAGO) {
		COD_METODO_PAGO = cOD_METODO_PAGO;
	}

	

	public String getNOMBRE() {
		return NOMBRE;
	}

	public void setNOMBRE(String nOMBRE) {
		NOMBRE = nOMBRE;
	}

	public List<Venta> getVenta() {
		return venta;
	}

	public void setVenta(List<Venta> venta) {
		this.venta = venta;
	}
    
    
}
