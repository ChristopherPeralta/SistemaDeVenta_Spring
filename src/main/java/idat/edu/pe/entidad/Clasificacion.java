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
@Table(name = "CLASIFICACION")
public class Clasificacion {
	
	@Id
	@Column(name= "COD_CLASIFICACION", length = 6)
	private String COD_CLASIFICACION;

	@Column(name = "NOMBRE", length = 40, nullable = false)
    private String NOMBRE;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "COD_CLASIFICACION"/* NOMBRE DE LA TABLA */, cascade = CascadeType.ALL)
    List<Producto> producto;

	public String getCOD_CLASIFICACION() {
		return COD_CLASIFICACION;
	}

	public void setCOD_CLASIFICACION(String cOD_CLASIFICACION) {
		COD_CLASIFICACION = cOD_CLASIFICACION;
	}

	public String getNOMBRE() {
		return NOMBRE;
	}

	public void setNOMBRE(String nOMBRE) {
		NOMBRE = nOMBRE;
	}

	public List<Producto> getProducto() {
		return producto;
	}

	public void setProducto(List<Producto> producto) {
		this.producto = producto;
	}

	
	
	
}
