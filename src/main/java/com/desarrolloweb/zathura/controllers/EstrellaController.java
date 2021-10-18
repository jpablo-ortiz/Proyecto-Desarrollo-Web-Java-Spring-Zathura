package com.desarrolloweb.zathura.controllers;

import java.util.List;

import com.desarrolloweb.zathura.exceptions.RecordNotFoundException;
import com.desarrolloweb.zathura.models.Estrella;
import com.desarrolloweb.zathura.service.EstrellaService;

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
 * entidad estrella
 * 
 * @author Kenneth Leonel Triana
 * @author Juan Pablo Ortiz Rubio
 * @version 2.0.0
 */
@RestController
@RequestMapping("/estrella")
@CrossOrigin(origins = "http://localhost:4200")
public class EstrellaController {

	/**
	 * Objeto que permite el registro de trazas de la ejecución de las operaciones
	 * de la clase
	 */
	private Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * Inyección de dependencia del servicio de estrellas
	 */
	@Autowired
	private EstrellaService estrellaService;

	// CRUD - CREATE - READ - UPDATE - DELETE

	// ------------------------------------------------------------
	// -------------------------- CREATE --------------------------
	// ------------------------------------------------------------

	@PostMapping("")
	@Operation(summary = "Crea una nueva estrella")
	public Estrella crearEstrella(@RequestBody Estrella estrellaNueva) {
		log.info("Creando Estrella");
		return estrellaService.crearEstrella(estrellaNueva);
	}

	// ------------------------------------------------------------
	// --------------------------- READ ---------------------------
	// ------------------------------------------------------------

	@GetMapping("/{id}")
	@Operation(summary = "Obtiene una estrella por su id")
	public Estrella obtenerEstrella(@PathVariable Long id) throws RecordNotFoundException {
		log.info("Obtener Estrella por ID");
		return estrellaService.obtenerEstrella(id);
	}

	@GetMapping("")
	@Operation(summary = "Obtiene todas las estrellas")
	public List<Estrella> obtenerEstrellas() {
		log.info("Obtener todas las Estrellas");
		return estrellaService.obtenerEstrellas();
	}

	// ------------------------------------------------------------
	// -------------------------- UPDATE --------------------------
	// ------------------------------------------------------------

	@PostMapping("/{id}")
	@Operation(summary = "Modifica una estrella")
	public Estrella modificarEstrella(@RequestBody Estrella estrella, @PathVariable Long id) {
		log.info("modificar Estrella : " + estrella.getId());
		return estrellaService.modificarEstrella(estrella, estrella.getId());
	}

	// ------------------------------------------------------------
	// -------------------------- DELETE --------------------------
	// ------------------------------------------------------------

	@DeleteMapping("{id}")
	@Operation(summary = "Elimina una estrella")
	public void eliminarEstrellaById(@PathVariable Long id) {
		log.info("Eliminar Estrella por id" + id);
		estrellaService.eliminarEstrella(id);
	}

}