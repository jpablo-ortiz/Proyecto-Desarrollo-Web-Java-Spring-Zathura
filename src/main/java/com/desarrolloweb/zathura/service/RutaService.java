package com.desarrolloweb.zathura.service;

import java.util.ArrayList;
import java.util.List;

import com.desarrolloweb.zathura.exceptions.RecordNotFoundException;
import com.desarrolloweb.zathura.models.Estrella;
import com.desarrolloweb.zathura.models.Ruta;
import com.desarrolloweb.zathura.repositories.RutaRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Esta clase contiene los servicios para la entidad Ruta
 *
 * @author Kenneth Leonel Triana
 * @author Juan Pablo Ortiz Rubio
 * @version 2.0.0
 */
@Service
public class RutaService {

	/**
	 * Objeto que permite el registro de trazas de la ejecución de las operaciones
	 * de la clase
	 */
	private Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * Inyección de dependencia del repositorio de la entidad Ruta
	 */
	@Autowired
	private RutaRepository rutaRepository;

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
	 * Método que permite crear una entidad Ruta
	 *
	 * @param ruta Objeto de tipo Ruta que contiene la información de la entidad
	 *             Ruta
	 * @return Objeto de tipo Ruta que contiene la información de la entidad Ruta
	 */
	public Ruta crearRuta(Ruta ruta) {
		Estrella estrellaAPlantilla = ruta.getEstrellaA();
		if (estrellaAPlantilla != null) {
			try {
				estrellaAPlantilla = estrellaService.obtenerEstrella(estrellaAPlantilla.getId());
			} catch (RecordNotFoundException e) {
				estrellaAPlantilla = estrellaService.crearEstrella(estrellaAPlantilla);
			}
			ruta.setEstrellaA(estrellaAPlantilla);
		}

		Estrella estrellaBPlantilla = ruta.getEstrellaB();
		if (estrellaBPlantilla != null) {
			try {
				estrellaBPlantilla = estrellaService.obtenerEstrella(estrellaBPlantilla.getId());
			} catch (RecordNotFoundException e) {
				estrellaBPlantilla = estrellaService.crearEstrella(estrellaBPlantilla);
			}
			ruta.setEstrellaB(estrellaBPlantilla);
		}

		if (ruta.getDistancia() == null) {
			double distancia = Math.sqrt(Math.pow(estrellaAPlantilla.getX() - estrellaBPlantilla.getX(), 2)
					+ Math.pow(estrellaAPlantilla.getY() - estrellaBPlantilla.getY(), 2)
					+ Math.pow(estrellaAPlantilla.getZ() - estrellaBPlantilla.getZ(), 2));
			ruta.setDistancia(distancia);
		}

		return rutaRepository.save(ruta);
	}

	// ------------------------------------------------------------
	// --------------------------- READ ---------------------------
	// ------------------------------------------------------------

	/**
	 * Método que permite obtener una entidad Ruta por su identificador
	 *
	 * @param id Identificador de la entidad Ruta
	 * @return Objeto de tipo Ruta que contiene la información de la entidad Ruta
	 * @throws RecordNotFoundException Excepción que se arroja cuando no se
	 *                                 encuentra el registro de la entidad Ruta
	 */
	public Ruta obtenerRuta(Long id) throws RecordNotFoundException {
		Ruta ruta = rutaRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("No se encontro ruta con id: " + id));
		return ruta;
	}

	/**
	 * Método que permite obtener todas las entidades Ruta
	 *
	 * @return Lista de objetos de tipo Ruta que contiene la información de las
	 *         entidades Ruta
	 */
	public List<Ruta> obtenerRutas() {
		return (List<Ruta>) rutaRepository.findAll();
	}

	// ------------------------------------------------------------
	// -------------------------- UPDATE --------------------------
	// ------------------------------------------------------------

	/**
	 * Método que permite modificar una entidad Ruta
	 *
	 * @param id   Identificador de la entidad Ruta
	 * @param ruta Objeto de tipo Ruta que contiene la información de la entidad
	 *             Ruta
	 * @return Objeto de tipo Ruta que contiene la información de la entidad Ruta
	 */
	public Ruta modificarRuta(Ruta plantilla, Long id) {
		return rutaRepository.findById(id).map(ruta -> {
			ruta.setDistancia(plantilla.getDistancia());

			Estrella estrellaAPlantilla = plantilla.getEstrellaA();
			if (estrellaAPlantilla != null) {
				try {
					estrellaAPlantilla = estrellaService.obtenerEstrella(estrellaAPlantilla.getId());
				} catch (RecordNotFoundException e) {
					estrellaAPlantilla = estrellaService.crearEstrella(estrellaAPlantilla);
				}
				ruta.setEstrellaA(estrellaAPlantilla);
			}

			Estrella estrellaBPlantilla = plantilla.getEstrellaB();
			if (estrellaBPlantilla != null) {
				try {
					estrellaBPlantilla = estrellaService.obtenerEstrella(estrellaBPlantilla.getId());
				} catch (RecordNotFoundException e) {
					estrellaBPlantilla = estrellaService.crearEstrella(estrellaBPlantilla);
				}
				ruta.setEstrellaA(estrellaBPlantilla);
			}

			return rutaRepository.save(ruta);
		}).orElseGet(() -> {
			plantilla.setId(id);
			return rutaRepository.save(plantilla);
		});
	}

	// ------------------------------------------------------------
	// -------------------------- DELETE --------------------------
	// ------------------------------------------------------------

	/**
	 * Método que permite eliminar una entidad Ruta
	 *
	 * @param id Identificador de la entidad Ruta
	 */
	public void eliminarRuta(Long id) {
		rutaRepository.deleteById(id);
	}

	public List<Ruta> obtenerRutasDeEstrellaId(Long id) {
		List<Ruta> rutas = new ArrayList<>();
		rutas.addAll(rutaRepository.obtenerRutasADeEstrellaId(id));
		rutas.addAll(rutaRepository.obtenerRutasBDeEstrellaId(id));
		return rutas;
	}

    public Ruta obtenerRutaDeEstrellaAEstrella(Long idEstrellaO, Long idEstrellaD) {
		return rutaRepository.obtenerRutaDeEstrellaAEstrella(idEstrellaO, idEstrellaD);	
    }

}
