package idat.edu.pe.servicio;

import java.util.List;
import idat.edu.pe.entidad.Empleado;


public interface EmpleadoService {

		public List<Empleado> listaEmpleados();
		
		void save(Empleado empleado);

		void softDelete(String COD_EMPLEADO);
		
		public Empleado findById(String COD);
		
	    List<Empleado> buscarEmpleadosPorCriterio(String codigoDNIoNombre);
	    
	    List<Empleado> buscarEmpleadosPorCriterioYDistrito(String codigoDNIoNombre, String codigoDistrito);


}
