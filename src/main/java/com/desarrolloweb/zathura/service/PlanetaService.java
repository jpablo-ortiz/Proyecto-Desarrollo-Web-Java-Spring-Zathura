package com.desarrolloweb.zathura.service;

import java.util.List;

import com.desarrolloweb.zathura.exceptions.RecordNotFoundException;
import com.desarrolloweb.zathura.models.Estrella;
import com.desarrolloweb.zathura.models.Planeta;
import com.desarrolloweb.zathura.models.PlanetaXProducto;
import com.desarrolloweb.zathura.models.Producto;
import com.desarrolloweb.zathura.repositories.PlanetaRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Esta clase contiene los servicios para la entidad Planeta
 *
 * @author Kenneth Leonel Triana
 * @author Juan Pablo Ortiz Rubio
 * @version 2.0.0
 */
@Service
public class PlanetaService {

	/**
	 * Objeto que permite el registro de trazas de la ejecución de las operaciones
	 * de la clase
	 */
	private Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * Inyección de dependencia del repositorio de la entidad Planeta
	 */
	@Autowired
	private PlanetaRepository planetaRepository;

	/**
	 * Inyección de dependencia del servicio de la entidad Estrella
	 */
	@Autowired
	private EstrellaService estrellaService;

	// CRUD - CREATE - READ - UPDATE - DELETE

	// ------------------------------------------------------------
	// -------------------------- CREATE --------------------------
	// ------------------------------------------------------------

	/**
	 * Método que permite crear una entidad Planeta
	 *
	 * @param planeta Objeto de tipo Planeta que contiene la información del planeta
	 *                a crear
	 * @return Objeto de tipo Planeta con la información del planeta creado
	 */
	public Planeta crearPlaneta(Planeta planeta) {
		Estrella estrellaPlantilla = planeta.getEstrella();
		if (estrellaPlantilla != null) {
			try {
				estrellaPlantilla = estrellaService.obtenerEstrella(estrellaPlantilla.getId());
			} catch (RecordNotFoundException e) {
				estrellaPlantilla = estrellaService.crearEstrella(estrellaPlantilla);
			}
			planeta.setEstrella(estrellaPlantilla);
		}
		return planetaRepository.save(planeta);
	}

	// ------------------------------------------------------------
	// --------------------------- READ ---------------------------
	// ------------------------------------------------------------

	/**
	 * Método que permite obtener una entidad Planeta por su identificador
	 *
	 * @param id Identificador de la entidad Planeta
	 * @return Objeto de tipo Planeta con la información del planeta obtenido
	 * @throws RecordNotFoundException Excepción que se arroja cuando no se
	 *                                 encuentra el planeta
	 */
	public Planeta obtenerPlaneta(Long id) throws RecordNotFoundException {
		Planeta planeta = planetaRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("No se encontro planeta con id: " + id));
		return planeta;
	}

	/**
	 * Método que permite obtener todas las entidades Planeta
	 *
	 * @return Lista de objetos de tipo Planeta con la información de todos los
	 *         planetas
	 */
	public List<Planeta> obtenerPlanetas() {
		return (List<Planeta>) planetaRepository.findAll();
	}

	// ------------------------------------------------------------
	// -------------------------- UPDATE --------------------------
	// ------------------------------------------------------------

	/**
	 * Método que permite modificar una entidad Planeta
	 *
	 * @param id      Identificador de la entidad Planeta
	 * @param planeta Objeto de tipo Planeta con la información del planeta a
	 *                modificar
	 * @return Objeto de tipo Planeta con la información del planeta actualizado
	 */
	public Planeta modificarPlaneta(Planeta plantilla, Long id) {
		return planetaRepository.findById(id).map(planeta -> {
			planeta.setNombre(plantilla.getNombre());
			planeta.setHabitado(plantilla.getHabitado());

			Estrella estrellaPlantilla = plantilla.getEstrella();
			if (estrellaPlantilla != null) {
				try {
					estrellaPlantilla = estrellaService.obtenerEstrella(estrellaPlantilla.getId());
				} catch (RecordNotFoundException e) {
					estrellaPlantilla = estrellaService.crearEstrella(estrellaPlantilla);
				}
				planeta.setEstrella(estrellaPlantilla);
			}

			return planetaRepository.save(planeta);
		}).orElseGet(() -> {
			plantilla.setId(id);
			return planetaRepository.save(plantilla);
		});
	}

	// ------------------------------------------------------------
	// -------------------------- DELETE --------------------------
	// ------------------------------------------------------------

	/**
	 * Método que permite eliminar una entidad Planeta
	 *
	 * @param id Identificador de la entidad Planeta
	 */
	public void eliminarPlaneta(Long id) {
		planetaRepository.deleteById(id);
	}

	// ------------------------------------------------------------
	// --------------------------- OTROS --------------------------
	// ------------------------------------------------------------

	/**
	 * Método que permite obtener un registro de los planetas por estrella
	 * 
	 * @param id Identificador de la estrella
	 * @return Lista de objetos de la entidad Planeta con la información de los
	 *         planetas
	 */
	public List<Planeta> obtenerPlanetasPorEstrella(Long id) {
		return planetaRepository.findByEstrellaId(id);
	}

	public List<Producto> obtenerProductosPorPlaneta(Long id) {
		return planetaRepository.findProductosByPlanetaId(id);
	}

	public PlanetaXProducto obtenerPlanetaXProducto(Long planetaId, Long productoId) {
		return planetaRepository.findPlanetaXProductoByIds(planetaId, productoId);
	}

    public List<PlanetaXProducto> obtenerPlanetaXProductos(Long idPlaneta) {
        return planetaRepository.findPlanetaXProductoByIdPlaneta(idPlaneta);
    }

    public Planeta obtenerPlanetaAleatorioHabitado() {
		List<Planeta> planetas = this.obtenerPlanetas();
		for (Planeta planeta : planetas) {
			if (planeta.getHabitado()) {
				return planeta;
			}
		}
		return null;
    }

}
