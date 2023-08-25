package idat.edu.pe.entidad;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "MARCA")
public class Marca {
		
		@Id
	    @Column(name = "COD_MARCA", length = 6)
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generador_marca") // GENERADOR PARA EL CODIGO
		@GenericGenerator(name="generador_marca",
		strategy = "idat.edu.pe.entidad.GeneradorCodigo", parameters= { // IMPORTAMOS LA CLASE DEL GENERADOR	
				@Parameter(name = GeneradorCodigo.INCREMENT_PARAM, value = "1"),  // EMPEZARA EN EL NUMERO 1 Y AVANZARA DE UNO EN UNO
				@Parameter(name = GeneradorCodigo.VALUE_PREFIX_PARAMETER, value = "MA"),  // EL PREFIJO SERA E
				@Parameter(name = GeneradorCodigo.NUMBER_FORMAT_PARAMETER, value = "%04d")})// LOS DIGITOS SERAN 4 ( MAXIMO = 9999 )	
	    private String COD_MARCA;
		
		@Column(name = "NOMBRE", length = 40, nullable = false)
	    private String NOMBRE;
		
		@Column(name = "ESTADO", length = 8 )
	    private String ESTADO;
		
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

		public String getESTADO() {
			return ESTADO;
		}

		public void setESTADO(String eSTADO) {
			ESTADO = eSTADO;
		}
		
		

		


}
