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
@Table(name = "CARGO")
public class Cargo {
    @Id
    @Column(name = "COD_CARGO", length = 6)
    private String COD_CARGO;
    
    @Column(name = "NOMBRE", length = 40, nullable = false)
    private String NOMBRE;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "COD_CARGO"/* NOMBRE DE LA TABLA */, cascade = CascadeType.ALL)
    List<Usuario> usuario;

	

	public String getCOD_CARGO() {
		return COD_CARGO;
	}

	public void setCOD_CARGO(String cOD_CARGO) {
		COD_CARGO = cOD_CARGO;
	}

	public String getNOMBRE() {
		return NOMBRE;
	}

	public void setNOMBRE(String nOMBRE) {
		NOMBRE = nOMBRE;
	}

	public List<Usuario> getUsuario() {
		return usuario;
	}

	public void setUsuario(List<Usuario> usuario) {
		this.usuario = usuario;
	}

	
}
