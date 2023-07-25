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
@Table(name="TIPO_DOCUMENTO")
public class TipoDocumento {
	
	@Id
    @Column(name = "COD_TIPO_DOCUMENTO", length = 6)
    private String COD_TIPO_DOCUMENTO;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "COD_TIPO_DOCUMENTO"/* NOMBRE DE LA TABLA */, cascade = CascadeType.ALL)
    List<Cliente> cliente;

    @Column(name = "NOMBRE", length = 40, nullable = false)
    private String NOMBRE;
    

	public String getCOD_TIPO_DOCUMENTO() {
		return COD_TIPO_DOCUMENTO;
	}

	public void setCOD_TIPO_DOCUMENTO(String cOD_TIPO_DOCUMENTO) {
		COD_TIPO_DOCUMENTO = cOD_TIPO_DOCUMENTO;
	}

	public List<Cliente> getCliente() {
		return cliente;
	}

	public void setCliente(List<Cliente> cliente) {
		this.cliente = cliente;
	}

	public String getNOMBRE() {
		return NOMBRE;
	}

	public void setNOMBRE(String nOMBRE) {
		NOMBRE = nOMBRE;
	}

}
