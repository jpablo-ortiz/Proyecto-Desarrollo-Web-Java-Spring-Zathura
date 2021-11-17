package com.desarrolloweb.zathura.controllers;

import java.util.List;

import com.desarrolloweb.zathura.exceptions.RecordNotFoundException;
import com.desarrolloweb.zathura.models.Estrella;
import com.desarrolloweb.zathura.models.Nave;
import com.desarrolloweb.zathura.models.Planeta;
import com.desarrolloweb.zathura.models.Tripulante;
import com.desarrolloweb.zathura.service.TripulanteService;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

/**
 * Esta clase contiene los métodos para el manejo de los servicios rest de la
 * entidad tripulante
 * 
 * @author Kenneth Leonel Triana
 * @author Juan Pablo Ortiz Rubio
 * @version 2.0.0
 */
@RestController
@RequestMapping("/tripulante")
@CrossOrigin(origins = "http://localhost:4200")
public class TripulanteController {

	/**
	 * Objeto que permite el registro de trazas de la ejecución de las operaciones
	 * de la clase
	 */
	private Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * Inyección de dependencia del servicio de tripulante
	 */
	@Autowired
	private TripulanteService tripulanteService;

	// CRUD - CREATE - READ - UPDATE - DELETE

	// ------------------------------------------------------------
	// -------------------------- CREATE --------------------------
	// ------------------------------------------------------------

	@PostMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Crea un nuevo tripulante")
	public Tripulante crearTripulante(@RequestBody String json) throws RecordNotFoundException {
		JSONObject respuesta = new JSONObject(json);
		String username = respuesta.getString("username");
		String password = respuesta.getString("password");
		boolean capitan = respuesta.getBoolean("capitan");
		boolean navegante = respuesta.getBoolean("navegante");
		boolean comerciante = respuesta.getBoolean("comerciante");

		Tripulante tripulanteNuevo = new Tripulante();
		tripulanteNuevo.setUsername(username);
		tripulanteNuevo.setPassword(password);
		tripulanteNuevo.setCapitan(capitan);
		tripulanteNuevo.setNavegante(navegante);
		tripulanteNuevo.setComerciante(comerciante);


		log.info("Creando Tripulante");
		return tripulanteService.crearTripulante(tripulanteNuevo);
	}

	// ------------------------------------------------------------
	// --------------------------- READ ---------------------------
	// ------------------------------------------------------------

	@GetMapping("/{id}")
	@Operation(summary = "Obtiene un tripulante por su id")
	public Tripulante obtenerTripulante(@PathVariable Long id) throws RecordNotFoundException {
		log.info("Obtener Tripulante por ID");
		return tripulanteService.obtenerTripulante(id);
	}

	@GetMapping("")
	@Operation(summary = "Obtiene todos los tripulantes")
	public List<Tripulante> obtenerTripulantes() {
		log.info("Obtener todos los Tripulantes");
		return tripulanteService.obtenerTripulantes();
	}

	// ------------------------------------------------------------
	// -------------------------- UPDATE --------------------------
	// ------------------------------------------------------------

	@PostMapping("/{id}")
	@Operation(summary = "Modifica un tripulante")
	public Tripulante modificarTripulante(@RequestBody Tripulante tripulante, @PathVariable Long id) {
		log.info("modificar Tripulante : " + tripulante.getId());
		return tripulanteService.modificarTripulante(tripulante, tripulante.getId());
	}

	// ------------------------------------------------------------
	// -------------------------- DELETE --------------------------
	// ------------------------------------------------------------

	@PreAuthorize("hasRole('CAPITAN') or hasRole('NAVEGANTE') or hasRole('COMERCIANTE')")
	@DeleteMapping("/{id}")
	@Operation(summary = "Elimina un tripulante")
	public void eliminarTripulanteById(@PathVariable Long id) {
		log.info("Eliminar Tripulante por id" + id);
		tripulanteService.eliminarTripulante(id);
	}

	// ------------------------------------------------------------
	// --------------------------- OTROS --------------------------
	// ------------------------------------------------------------

	@PreAuthorize("hasRole('CAPITAN') or hasRole('NAVEGANTE') or hasRole('COMERCIANTE')")
	@GetMapping("/nave/{id}")
	@Operation(summary = "Obtiene los tripulantes por el id de la nave")
	public List<Tripulante> obtenerTripulantesPorNave(@PathVariable Long id) {
		log.info("Obtener Tripulantes por ID de la nave");
		return tripulanteService.obtenerTripulantesPorNave(id);
	}

	@PreAuthorize("hasRole('CAPITAN') or hasRole('NAVEGANTE')")
	@GetMapping("/{id}/estrella")
	@Operation(summary = "Obtiene la estrella actual de la nave del tripulante")
	public Estrella obtenerEstrellaActual(@PathVariable Long id) {
		log.info("Obtener la estrella actual de la nave del tripulante");
		return tripulanteService.obtenerEstrellaActualPorTripulante(id);
	}

	@PreAuthorize("hasRole('CAPITAN') or hasRole('NAVEGANTE') or hasRole('COMERCIANTE')")
	@GetMapping("/{id}/planeta")
	@Operation(summary = "Obtiene el planeta actual de la nave del tripulante")
	public Planeta obtenerPlanetaActual(@PathVariable Long id) {
		log.info("Obtener el planeta actual de la nave del tripulante");
		return tripulanteService.obtenerPlanetaActualPorTripulante(id);
	}

	@PreAuthorize("hasRole('CAPITAN') or hasRole('NAVEGANTE') or hasRole('COMERCIANTE')")
	@GetMapping("/{id}/nave")
	@Operation(summary = "Obtiene la nave actual del tripulante")
	public Nave obtenerNaveActual(@PathVariable Long id) {
		log.info("Obtener la nave actual del tripulante");
		return tripulanteService.obtenerNaveActualByTripulante(id);
	}
	
	@PreAuthorize("hasRole('CAPITAN') or hasRole('NAVEGANTE') or hasRole('COMERCIANTE')")
	@GetMapping(path = "/{idTripulante}/{idPlaneta}/productos", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Obtiene los productos que se pueden vender dado la nave de un tripulante y un planeta")
	public String obtenerProductosVendibles(@PathVariable Long idTripulante, @PathVariable Long idPlaneta) throws RecordNotFoundException {
		log.info("Obtener los productos que se pueden vender dado la nave de un tripulante y un planeta");
		return tripulanteService.obtenerProductosVendibles(idTripulante, idPlaneta).toString();
	}

	@PreAuthorize("hasRole('CAPITAN') or hasRole('NAVEGANTE') or hasRole('COMERCIANTE')")
	@GetMapping("/{usuario}/login/{password}")
    public Tripulante getTripulanteLogin(@PathVariable("usuario") String usuario, @PathVariable("password") String password) {
		log.info("Obtener el Tripulante por username y contraseña");
		return tripulanteService.findByUserAndPassword(usuario, password);
    }

}