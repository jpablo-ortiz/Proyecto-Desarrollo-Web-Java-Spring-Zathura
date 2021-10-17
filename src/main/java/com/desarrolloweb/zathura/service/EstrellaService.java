package com.desarrolloweb.zathura.service;

import java.util.List;

import com.desarrolloweb.zathura.exceptions.RecordNotFoundException;
import com.desarrolloweb.zathura.models.Estrella;
import com.desarrolloweb.zathura.repositories.EstrellaRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Esta clase contiene los servicios para la entidad Estrella
 * 
 * @author Kenneth Leonel Triana
 * @author Juan Pablo Ortiz Rubio
 * @version 2.0.0
 */
@Service
public class EstrellaService {

	/**
	 * Objeto que permite el registro de trazas de la ejecución de las operaciones
	 * de la clase
	 */
	private Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * Inyección de dependencia del repositorio de la entidad Estrella
	 */
	@Autowired
	private EstrellaRepository estrellaRepository;

	// CRUD - CREATE - READ - UPDATE - DELETE

	// ------------------------------------------------------------
	// -------------------------- CREATE --------------------------
	// ------------------------------------------------------------

	// Post
	/**
	 * Método que permite crear una entidad Estrella
	 * 
	 * @param estrella
	 *            Objeto de tipo Estrella que contiene la información de la
	 *            entidad a crear
	 * @return Objeto de tipo Estrella con la información de la entidad creada
	 */
	public Estrella crearEstrella(Estrella estrella) {
		return estrellaRepository.save(estrella);
	}

	// ------------------------------------------------------------
	// --------------------------- READ ---------------------------
	// ------------------------------------------------------------

	// Get
	/**
	 * Método que permite obtener una entidad Estrella
	 * 
	 * @param id
	 *            Identificador de la entidad Estrella
	 * @return Objeto de tipo Estrella con la información de la entidad obtenida
	 * @throws RecordNotFoundException
	 *             Excepción que permite notificar que no se encontró el registro
	 *             de la entidad Estrella
	 */
	public Estrella obtenerEstrella(Long id) throws RecordNotFoundException {
		Estrella estrella = estrellaRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("No se encontro estrella con id: " + id));
		return estrella;
	}

	// Get
	/**
	 * Método que permite obtener todas las entidades Estrella
	 * 
	 * @return Lista de objetos de tipo Estrella con la información de todas las
	 *         entidades Estrella
	 */
	public List<Estrella> obtenerEstrellas() {
		return (List<Estrella>) estrellaRepository.findAll();
	}

	// ------------------------------------------------------------
	// -------------------------- UPDATE --------------------------
	// ------------------------------------------------------------

	// Post
	/**
	 * Método que permite actualizar una entidad Estrella
	 * 
	 * @param estrella
	 *            Objeto de tipo Estrella con la información de la entidad a
	 *            actualizar
	 * @return Objeto de tipo Estrella con la información de la entidad actualizada
	 */
	public Estrella actualizarEstrella(Estrella plantilla, Long id) {
		return estrellaRepository.findById(id).map(estrella -> {
			estrella.setNombre(plantilla.getNombre());
			estrella.setRecurso(plantilla.getRecurso());
			estrella.setX(plantilla.getX());
			estrella.setY(plantilla.getY());
			estrella.setZ(plantilla.getZ());
			estrella.setHabitado(plantilla.getHabitado());
			
			// nueva.setPlanetas(entity.getPlanetas());
			// nueva.setRutasA(entity.getRutasA());
			// nueva.setRutasB(entity.getRutasB());

			return estrellaRepository.save(estrella);
		}).orElseGet(() -> {
			plantilla.setId(id);
			return estrellaRepository.save(plantilla);
		});
	}

	// ------------------------------------------------------------
	// -------------------------- DELETE --------------------------
	// ------------------------------------------------------------

	// Delete
	/**
	 * Método que permite eliminar una entidad Estrella
	 * 
	 * @param id
	 *            Identificador de la entidad Estrella a eliminar
	 */
	public void eliminarEstrella(Long id) {
		estrellaRepository.deleteById(id);
	}
}
