package com.desarrolloweb.zathura.service;

import java.util.List;

import com.desarrolloweb.zathura.exceptions.RecordNotFoundException;
import com.desarrolloweb.zathura.models.Nave;
import com.desarrolloweb.zathura.models.Tripulante;
import com.desarrolloweb.zathura.repositories.TripulanteRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Esta clase contiene los servicios para la entidad Tripulante
 *
 * @author Kenneth Leonel Triana
 * @author Juan Pablo Ortiz Rubio
 * @version 2.0.0
 */
@Service
public class TripulanteService {

	/**
	 * Objeto que permite el registro de trazas de la ejecución de las operaciones
	 * de la clase
	 */
	private Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * Inyección de dependencia del repositorio de la entidad Tripulante
	 */
	@Autowired
	private TripulanteRepository tripulanteRepository;

	/**
	 * Inyección de dependencia del servicio de la entidad Nave
	 */
	@Autowired
	private NaveService naveService;

	// CRUD - CREATE - READ - UPDATE - DELETE

	// ------------------------------------------------------------
	// -------------------------- CREATE --------------------------
	// ------------------------------------------------------------

	/**
	 * Método que permite crear un registro de la entidad Tripulante
	 *
	 * @param tripulante Objeto de la entidad Tripulante que contiene la información
	 *                   del tripulante a crear
	 * @return Objeto de la entidad Tripulante con la información del tripulante
	 *         creado
	 */
	public Tripulante crearTripulante(Tripulante tripulante) {
		Nave navePlantilla = tripulante.getNave();
		if (navePlantilla != null) {
			try {
				navePlantilla = naveService.obtenerNave(navePlantilla.getId());
			} catch (RecordNotFoundException e) {
				navePlantilla = naveService.crearNave(navePlantilla);
			}
			tripulante.setNave(navePlantilla);
		}
		return tripulanteRepository.save(tripulante);
	}

	// ------------------------------------------------------------
	// --------------------------- READ ---------------------------
	// ------------------------------------------------------------

	/**
	 * Método que permite obtener un registro de la entidad Tripulante
	 *
	 * @param id Identificador del tripulante a obtener
	 * @return Objeto de la entidad Tripulante con la información del tripulante
	 *         obtenido
	 * @throws RecordNotFoundException Si no existe el tripulante con el
	 *                                 identificador dado
	 */
	public Tripulante obtenerTripulante(Long id) throws RecordNotFoundException {
		Tripulante tripulante = tripulanteRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("No se encontro tripulante con id: " + id));
		return tripulante;
	}

	/**
	 * Método que permite obtener todos los registros de la entidad Tripulante
	 *
	 * @return Lista de objetos de la entidad Tripulante con la información de todos
	 *         los tripulantes obtenidos
	 */
	public List<Tripulante> obtenerTripulantes() {
		return (List<Tripulante>) tripulanteRepository.findAll();
	}

	// ------------------------------------------------------------
	// -------------------------- UPDATE --------------------------
	// ------------------------------------------------------------

	/**
	 * Método que permite modificar un registro de la entidad Tripulante
	 *
	 * @param id         Identificador del tripulante a modificar
	 * @param tripulante Objeto de la entidad Tripulante con la información del
	 *                   tripulante a modificar
	 * @return Objeto de la entidad Tripulante con la información del tripulante
	 *         actualizado
	 */
	public Tripulante modificarTripulante(Tripulante plantilla, Long id) {
		return tripulanteRepository.findById(id).map(tripulante -> {
			tripulante.setUsername(plantilla.getUsername());
			tripulante.setPassword(plantilla.getPassword());
			tripulante.setCapitan(plantilla.getCapitan());
			tripulante.setNavegante(plantilla.getNavegante());

			Nave navePlantilla = plantilla.getNave();
			if (navePlantilla != null) {
				try {
					navePlantilla = naveService.obtenerNave(navePlantilla.getId());
				} catch (RecordNotFoundException e) {
					navePlantilla = naveService.crearNave(navePlantilla);
				}
				tripulante.setNave(navePlantilla);
			}

			return tripulanteRepository.save(tripulante);
		}).orElseGet(() -> {
			plantilla.setId(id);
			return tripulanteRepository.save(plantilla);
		});
	}

	// ------------------------------------------------------------
	// -------------------------- DELETE --------------------------
	// ------------------------------------------------------------

	/**
	 * Método que permite eliminar un registro de la entidad Tripulante
	 *
	 * @param id Identificador del tripulante a eliminar
	 */
	public void eliminarTripulante(Long id) {
		tripulanteRepository.deleteById(id);
	}

	// ------------------------------------------------------------
	// --------------------------- OTROS --------------------------
	// ------------------------------------------------------------

	/**
	 * Método que permite obtener un registro de los tripulantes por nave
	 * 
	 * @param id Identificador de la nave
	 * @return Lista de objetos de la entidad Tripulante con la información de los tripulantes
	 */
    public List<Tripulante> obtenerTripulantesPorNave(Long id) {
        return tripulanteRepository.findByNaveId(id);
    }

}
