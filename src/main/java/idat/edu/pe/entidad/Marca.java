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
@Table(name = "MARCA")
public class Marca {
		
		@Id
		@Column(name= "COD_MARCA", length = 6)
		private String COD_MARCA;

		@Column(name = "NOMBRE", length = 40, nullable = false)
	    private String NOMBRE;
		
	    @OneToMany(fetch = FetchType.LAZY, mappedBy = "COD_MARCA"/* NOMBRE DE LA TABLA */, cascade = CascadeType.ALL)
	    List<Producto> producto;

		public String getCOD_MARCA() {
			return COD_MARCA;
		}

		public void setCOD_MARCA(String cOD_MARCA) {
			COD_MARCA = cOD_MARCA;
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
