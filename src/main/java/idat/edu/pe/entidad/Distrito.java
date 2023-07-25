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
@Table(name = "DISTRITO")
public class Distrito {
    @Id
    @Column(name = "COD_DISTRITO", length = 6)
    private String COD_DISTRITO;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "COD_DISTRITO"/* NOMBRE DE LA TABLA */, cascade = CascadeType.ALL)
    List<Empleado> empleado;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "COD_DISTRITO"/* NOMBRE DE LA TABLA */, cascade = CascadeType.ALL)
    List<Cliente> cliente;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "COD_DISTRITO"/* NOMBRE DE LA TABLA */, cascade = CascadeType.ALL)
    List<Proveedor> proveedor;

    @Column(name = "NOMBRE", length = 40, nullable = false)
    private String NOMBRE;

	public String getCOD_DISTRITO() {
		return COD_DISTRITO;
	}

	public void setCOD_DISTRITO(String cOD_DISTRITO) {
		COD_DISTRITO = cOD_DISTRITO;
	}

	public String getNOMBRE() {
		return NOMBRE;
	}

	public void setNOMBRE(String nOMBRE) {
		NOMBRE = nOMBRE;
	}

	public List<Empleado> getEmpleado() {
		return empleado;
	}

	public void setEmpleado(List<Empleado> empleado) {
		this.empleado = empleado;
	}

	public List<Cliente> getCliente() {
		return cliente;
	}

	public void setCliente(List<Cliente> cliente) {
		this.cliente = cliente;
	}

	public List<Proveedor> getProveedor() {
		return proveedor;
	}

	public void setProveedor(List<Proveedor> proveedor) {
		this.proveedor = proveedor;
	}
	
	
}