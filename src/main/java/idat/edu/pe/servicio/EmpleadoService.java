package idat.edu.pe.servicio;

import java.util.List;
import idat.edu.pe.entidad.Empleado;


public interface EmpleadoService {

		public List<Empleado> listaEmpleados();
		
		void save(Empleado empleado);

		public Empleado findById(String COD);

		void deleteById(String COD);
		

}
