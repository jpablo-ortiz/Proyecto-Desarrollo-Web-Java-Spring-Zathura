package com.desarrolloweb.zathura.service;

import java.util.List;

import com.desarrolloweb.zathura.exceptions.RecordNotFoundException;
import com.desarrolloweb.zathura.models.ModeloNave;
import com.desarrolloweb.zathura.models.Nave;
import com.desarrolloweb.zathura.models.Planeta;
import com.desarrolloweb.zathura.repositories.NaveRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Esta clase contiene los servicios para la entidad Nave
 *
 * @author Kenneth Leonel Triana
 * @author Juan Pablo Ortiz Rubio
 * @version 2.0.0
 */
@Service
public class NaveService {

	/**
	 * Objeto que permite el registro de trazas de la ejecución de las operaciones
	 * de la clase
	 */
	private Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * Inyección de dependencia del repositorio de la entidad Nave
	 */
	@Autowired
	private NaveRepository naveRepository;

	/**
	 * Inyección de dependencia del servicio de la entidad Planeta
	 */
	@Autowired
	private PlanetaService planetaService;

	/**
	 * Inyección de dependencia del servicio de la entidad ModeloNave
	 */
	@Autowired
	private ModeloNaveService modeloNaveService;

	// CRUD - CREATE - READ - UPDATE - DELETE

	// ------------------------------------------------------------
	// -------------------------- CREATE --------------------------
	// ------------------------------------------------------------

	/**
	 * Método que permite crear una entidad Nave
	 *
	 * @param nave Objeto de tipo Nave que contiene los datos de la nave a crear
	 * @return Objeto de tipo Nave que contiene los datos de la nave creada
	 */
	public Nave crearNave(Nave nave) {
		Planeta planetaPlantilla = nave.getPlanetaActual();
		if (planetaPlantilla != null) {
			try {
				planetaPlantilla = planetaService.obtenerPlaneta(planetaPlantilla.getId());
			} catch (RecordNotFoundException e) {
				planetaPlantilla = planetaService.crearPlaneta(planetaPlantilla);
			}
			nave.setPlanetaActual(planetaPlantilla);
		}

		ModeloNave modeloNavePlantilla = nave.getModeloNave();
		if (modeloNavePlantilla != null) {
			try {
				modeloNavePlantilla = modeloNaveService.obtenerModeloNave(modeloNavePlantilla.getId());
			} catch (RecordNotFoundException e) {
				modeloNavePlantilla = modeloNaveService.crearModeloNave(modeloNavePlantilla);
			}
			nave.setModeloNave(modeloNavePlantilla);
		}
		return naveRepository.save(nave);
	}

	// ------------------------------------------------------------
	// --------------------------- READ ---------------------------
	// ------------------------------------------------------------

	/**
	 * Método que permite obtener una entidad Nave
	 *
	 * @param id Identificador de la nave a obtener
	 * @return Objeto de tipo Nave que contiene los datos de la nave obtenida
	 * @throws RecordNotFoundException Excepción que permite notificar que no se
	 *                                 encontró el registro de la entidad Nave
	 */
	public Nave obtenerNave(Long id) throws RecordNotFoundException {
		Nave nave = naveRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("No se encontro nave con id: " + id));
		return nave;
	}

	/**
	 * Método que permite obtener todas las entidades Nave
	 *
	 * @return Lista de objetos de tipo Nave que contiene los datos de todas las
	 *         naves obtenidas
	 */
	public List<Nave> obtenerNaves() {
		return (List<Nave>) naveRepository.findAll();
	}

	// ------------------------------------------------------------
	// -------------------------- UPDATE --------------------------
	// ------------------------------------------------------------

	/**
	 * Método que permite modificar una entidad Nave
	 *
	 * @param nave Objeto de tipo Nave que contiene los datos de la nave a
	 *             modificar
	 * @return Objeto de tipo Nave que contiene los datos de la nave actualizada
	 */
	public Nave modificarNave(Nave plantilla, Long id) {
		return naveRepository.findById(id).map(nave -> {
			nave.setNombre(plantilla.getNombre());
			nave.setCargaActual(plantilla.getCargaActual());
			nave.setCantidadCredito(plantilla.getCantidadCredito());
			nave.setTotalTiempoViaje(plantilla.getTotalTiempoViaje());

			Planeta planetaPlantilla = plantilla.getPlanetaActual();
			if (planetaPlantilla != null) {
				try {
					planetaPlantilla = planetaService.obtenerPlaneta(planetaPlantilla.getId());
				} catch (RecordNotFoundException e) {
					planetaPlantilla = planetaService.crearPlaneta(planetaPlantilla);
				}
				nave.setPlanetaActual(planetaPlantilla);
			}

			ModeloNave modeloNavePlantilla = plantilla.getModeloNave();
			if (modeloNavePlantilla != null) {
				try {
					modeloNavePlantilla = modeloNaveService.obtenerModeloNave(modeloNavePlantilla.getId());
				} catch (RecordNotFoundException e) {
					modeloNavePlantilla = modeloNaveService.crearModeloNave(modeloNavePlantilla);
				}
				nave.setModeloNave(modeloNavePlantilla);
			}

			return naveRepository.save(nave);
		}).orElseGet(() -> {
			plantilla.setId(id);
			return naveRepository.save(plantilla);
		});
	}

	// ------------------------------------------------------------
	// -------------------------- DELETE --------------------------
	// ------------------------------------------------------------

	/**
	 * Método que permite eliminar una entidad Nave
	 *
	 * @param id Identificador de la nave a eliminar
	 */
	public void eliminarNave(Long id) {
		naveRepository.deleteById(id);
	}
}
