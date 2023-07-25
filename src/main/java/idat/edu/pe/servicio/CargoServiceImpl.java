package idat.edu.pe.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.edu.pe.entidad.Cargo;
import idat.edu.pe.repositorio.CargoRepository;

@Service
public class CargoServiceImpl implements CargoService {
    @Autowired
    private CargoRepository cargoRepository;

	@Override
	public List<Cargo> listaCargo() {
		return cargoRepository.findAll();
	}


}