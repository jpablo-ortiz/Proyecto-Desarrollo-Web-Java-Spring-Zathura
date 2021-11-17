package com.desarrolloweb.zathura.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.desarrolloweb.zathura.exceptions.RecordNotFoundException;
import com.desarrolloweb.zathura.models.Estrella;
import com.desarrolloweb.zathura.models.Nave;
import com.desarrolloweb.zathura.models.Ruta;
import com.desarrolloweb.zathura.models.POJOs.EstrellaPojo;
import com.desarrolloweb.zathura.repositories.EstrellaRepository;
import com.desarrolloweb.zathura.repositories.NaveRepository;

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

	public static final int DIAS_DEL_ANO = 365;

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

	/**
	 * Inyección de dependencia del repositorio de la entidad Ruta
	 */
	@Autowired
	private RutaService rutaService;

	/**
	 * Inyección de dependencia del service de la entidad Tripulante
	 */
	@Autowired
	private TripulanteService tripulanteService;

	/**
	 * Inyección de dependencia del repositorio de la entidad Nave
	 */
	@Autowired
	private NaveRepository naveRepository;

	// CRUD - CREATE - READ - UPDATE - DELETE

	// ------------------------------------------------------------
	// -------------------------- CREATE --------------------------
	// ------------------------------------------------------------

	/**
	 * Método que permite crear una entidad Estrella
	 * 
	 * @param estrella Objeto de tipo Estrella que contiene la información de la
	 *                 entidad a crear
	 * @return Objeto de tipo Estrella con la información de la entidad creada
	 */
	public Estrella crearEstrella(Estrella estrella) {
		return estrellaRepository.save(estrella);
	}

	// ------------------------------------------------------------
	// --------------------------- READ ---------------------------
	// ------------------------------------------------------------

	/**
	 * Método que permite obtener una entidad Estrella
	 * 
	 * @param id Identificador de la entidad Estrella
	 * @return Objeto de tipo Estrella con la información de la entidad obtenida
	 * @throws RecordNotFoundException Excepción que permite notificar que no se
	 *                                 encontró el registro de la entidad Estrella
	 */
	public Estrella obtenerEstrella(Long id) throws RecordNotFoundException {
		Estrella estrella = estrellaRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("No se encontro estrella con id: " + id));
		return estrella;
	}

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

	/**
	 * Método que permite modificar una entidad Estrella
	 * 
	 * @param estrella Objeto de tipo Estrella con la información de la entidad a
	 *                 modificar
	 * @return Objeto de tipo Estrella con la información de la entidad actualizada
	 */
	public Estrella modificarEstrella(Estrella plantilla, Long id) {
		return estrellaRepository.findById(id).map(estrella -> {
			estrella.setNombre(plantilla.getNombre());
			estrella.setRecurso(plantilla.getRecurso());
			estrella.setX(plantilla.getX());
			estrella.setY(plantilla.getY());
			estrella.setZ(plantilla.getZ());
			estrella.setHabitado(plantilla.getHabitado());

			return estrellaRepository.save(estrella);
		}).orElseGet(() -> {
			plantilla.setId(id);
			return estrellaRepository.save(plantilla);
		});
	}

	// ------------------------------------------------------------
	// -------------------------- DELETE --------------------------
	// ------------------------------------------------------------

	/**
	 * Método que permite eliminar una entidad Estrella
	 * 
	 * @param id Identificador de la entidad Estrella a eliminar
	 */
	public void eliminarEstrella(Long id) {
		estrellaRepository.deleteById(id);
	}

	/**
	 * Método que permite obtener las x estrellas más cercanas a la estrella
	 * 
	 * @param id       Identificador de la entidad Estrella
	 * @param cantidad Cantidad de estrellas a obtener
	 * @return Lista de objetos de tipo Estrella con la información de las 10
	 *         estrellas más cercanas a la estrella
	 * @throws RecordNotFoundException
	 */
	public List<EstrellaPojo> obtenerEstrellasCercanas(Long id, int cantidad) throws RecordNotFoundException {
		List<EstrellaPojo> resultado = new ArrayList<EstrellaPojo>();
		List<Ruta> rutas = rutaService.obtenerRutasDeEstrellaId(id);

		Collections.sort(rutas, (ruta1, ruta2) -> ruta1.getDistancia().compareTo(ruta2.getDistancia()));

		for (Ruta ruta : rutas) {
			EstrellaPojo estrellaTemp = new EstrellaPojo();
			if (ruta.getEstrellaA().getId() == id) {
				estrellaTemp.id = ruta.getEstrellaB().getId();
				estrellaTemp.nombre = ruta.getEstrellaB().getNombre();
				estrellaTemp.x = ruta.getEstrellaB().getX();
				estrellaTemp.y = ruta.getEstrellaB().getY();
				estrellaTemp.z = ruta.getEstrellaB().getZ();
				estrellaTemp.habitado = ruta.getEstrellaB().getHabitado();
				estrellaTemp.distancia = ruta.getDistancia();
				resultado.add(estrellaTemp);
			} else {
				estrellaTemp.id = ruta.getEstrellaA().getId();
				estrellaTemp.nombre = ruta.getEstrellaA().getNombre();
				estrellaTemp.x = ruta.getEstrellaA().getX();
				estrellaTemp.y = ruta.getEstrellaA().getY();
				estrellaTemp.z = ruta.getEstrellaA().getZ();
				estrellaTemp.habitado = ruta.getEstrellaA().getHabitado();
				estrellaTemp.distancia = ruta.getDistancia();
				resultado.add(estrellaTemp);
			}
			if (resultado.size() == cantidad) {
				break;
			}
		}

		return resultado;
	}

	public boolean verificarViaje(Long idEstrellaO, Long idEstrellaD, Long idTripulante)
			throws RecordNotFoundException {
		Ruta ruta = rutaService.obtenerRutaDeEstrellaAEstrella(idEstrellaO, idEstrellaD);
		Nave nave = tripulanteService.obtenerNaveActualByTripulante(idTripulante);

		double calculoTiempo = (ruta.getDistancia()) / ((nave.getModeloNave().getVelocidadMax()) * DIAS_DEL_ANO);

		if (calculoTiempo > nave.getModeloNave().getTiempoLimite()) {
			return false;
		}
		return true;
	}

	public boolean viajar(Long idEstrellaO, Long idEstrellaD, Long idTripulante) {
		Ruta ruta = rutaService.obtenerRutaDeEstrellaAEstrella(idEstrellaO, idEstrellaD);
		Nave nave = tripulanteService.obtenerNaveActualByTripulante(idTripulante);

		double calculoTiempo = (ruta.getDistancia()) / ((nave.getModeloNave().getVelocidadMax()) * DIAS_DEL_ANO);

		if (calculoTiempo > nave.getModeloNave().getTiempoLimite()) {
			return false;
		}

		nave.setTotalTiempoViaje(nave.getTotalTiempoViaje() + calculoTiempo);
		naveRepository.save(nave);
		return true;
	}

}
