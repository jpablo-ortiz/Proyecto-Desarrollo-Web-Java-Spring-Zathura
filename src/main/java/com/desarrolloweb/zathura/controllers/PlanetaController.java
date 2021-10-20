package com.desarrolloweb.zathura.controllers;

import java.util.List;

import com.desarrolloweb.zathura.exceptions.RecordNotFoundException;
import com.desarrolloweb.zathura.models.Planeta;
import com.desarrolloweb.zathura.service.PlanetaService;

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
 * entidad planeta
 * 
 * @author Kenneth Leonel Triana
 * @author Juan Pablo Ortiz Rubio
 * @version 2.0.0
 */
@RestController
@RequestMapping("/planeta")
@CrossOrigin(origins = "http://localhost:4200")
public class PlanetaController {

	/**
	 * Objeto que permite el registro de trazas de la ejecución de las operaciones
	 * de la clase
	 */
	private Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * Inyección de dependencia del servicio de planeta
	 */
    @Autowired
    private PlanetaService planetaService;

    // CRUD - CREATE - READ - UPDATE - DELETE

	// ------------------------------------------------------------
	// -------------------------- CREATE --------------------------
	// ------------------------------------------------------------

	@PostMapping("")
	@Operation(summary = "Crea un nuevo planeta")
	public Planeta crearPlaneta(@RequestBody Planeta planetaNueva) {
		log.info("Creando Planeta");
		return planetaService.crearPlaneta(planetaNueva);
	}

	// ------------------------------------------------------------
	// --------------------------- READ ---------------------------
	// ------------------------------------------------------------

	@GetMapping("/{id}")
	@Operation(summary = "Obtiene un planeta por su id")
	public Planeta obtenerPlaneta(@PathVariable Long id) throws RecordNotFoundException {
		log.info("Obtener Planeta por ID");
		return planetaService.obtenerPlaneta(id);
	}

	@GetMapping("")
	@Operation(summary = "Obtiene todos los planetas")
	public List<Planeta> obtenerPlanetas() {
		log.info("Obtener todos los Planetas");
		return planetaService.obtenerPlanetas();
	}

	// ------------------------------------------------------------
	// -------------------------- UPDATE --------------------------
	// ------------------------------------------------------------

	@PostMapping("/{id}")
	@Operation(summary = "Modifica un planeta")
	public Planeta modificarPlaneta(@RequestBody Planeta planeta, @PathVariable Long id) {
		log.info("modificar Planeta : " + planeta.getId());
		return planetaService.modificarPlaneta(planeta, planeta.getId());
	}

	// ------------------------------------------------------------
	// -------------------------- DELETE --------------------------
	// ------------------------------------------------------------

	@DeleteMapping("/{id}")
	@Operation(summary = "Elimina un planeta")
	public void eliminarPlanetaById(@PathVariable Long id) {
		log.info("Eliminar Planeta por id" + id);
		planetaService.eliminarPlaneta(id);
	}

}