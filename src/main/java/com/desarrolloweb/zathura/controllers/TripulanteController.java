package com.desarrolloweb.zathura.controllers;

import java.util.List;

import com.desarrolloweb.zathura.exceptions.RecordNotFoundException;
import com.desarrolloweb.zathura.models.Estrella;
import com.desarrolloweb.zathura.models.Nave;
import com.desarrolloweb.zathura.models.Planeta;
import com.desarrolloweb.zathura.models.Tripulante;
import com.desarrolloweb.zathura.service.TripulanteService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

	@PostMapping("")
	@Operation(summary = "Crea un nuevo tripulante")
	public Tripulante crearTripulante(@RequestBody Tripulante tripulanteNueva) {
		log.info("Creando Tripulante");
		return tripulanteService.crearTripulante(tripulanteNueva);
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

	@DeleteMapping("/{id}")
	@Operation(summary = "Elimina un tripulante")
	public void eliminarTripulanteById(@PathVariable Long id) {
		log.info("Eliminar Tripulante por id" + id);
		tripulanteService.eliminarTripulante(id);
	}

	// ------------------------------------------------------------
	// --------------------------- OTROS --------------------------
	// ------------------------------------------------------------

	// Obtener los tripulantes por el id de la nave
	@GetMapping("/nave/{id}")
	@Operation(summary = "Obtiene los tripulantes por el id de la nave")
	public List<Tripulante> obtenerTripulantesPorNave(@PathVariable Long id) {
		log.info("Obtener Tripulantes por ID de la nave");
		return tripulanteService.obtenerTripulantesPorNave(id);
	}

	// Obtener la estrella actual de la nave del tripulante dado
	@GetMapping("/{id}/estrella")
	@Operation(summary = "Obtiene la estrella actual de la nave del tripulante")
	public Estrella obtenerEstrellaActual(@PathVariable Long id) {
		log.info("Obtener la estrella actual de la nave del tripulante");
		return tripulanteService.obtenerEstrellaActual(id);
	}

	// Obtener el planeta actual de la nave del tripulante dado
	@GetMapping("/{id}/planeta")
	@Operation(summary = "Obtiene el planeta actual de la nave del tripulante")
	public Planeta obtenerPlanetaActual(@PathVariable Long id) {
		log.info("Obtener el planeta actual de la nave del tripulante");
		return tripulanteService.obtenerPlanetaActual(id);
	}

	// Obtener la nave actual del tripulante dado
	@GetMapping("/{id}/nave")
	@Operation(summary = "Obtiene la nave actual del tripulante")
	public Nave obtenerNaveActual(@PathVariable Long id) {
		log.info("Obtener la nave actual del tripulante");
		return tripulanteService.obtenerNaveActual(id);
	}

}