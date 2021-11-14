package com.desarrolloweb.zathura.controllers;

import java.util.List;

import com.desarrolloweb.zathura.exceptions.RecordNotFoundException;
import com.desarrolloweb.zathura.models.Ruta;
import com.desarrolloweb.zathura.service.RutaService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
 * entidad ruta
 * 
 * @author Kenneth Leonel Triana
 * @author Juan Pablo Ortiz Rubio
 * @version 2.0.0
 */
@RestController
@RequestMapping("/ruta")
@CrossOrigin(origins = "http://localhost:4200")
public class RutaController {

	/**
	 * Objeto que permite el registro de trazas de la ejecución de las operaciones
	 * de la clase
	 */
	private Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * Inyección de dependencia del servicio de ruta
	 */
	@Autowired
	private RutaService rutaService;

	// CRUD - CREATE - READ - UPDATE - DELETE

	// ------------------------------------------------------------
	// -------------------------- CREATE --------------------------
	// ------------------------------------------------------------

	@PreAuthorize("hasRole('CAPITAN') or hasRole('NAVEGANTE')")
	@PostMapping("")
	@Operation(summary = "Crea una nueva ruta")
	public Ruta crearRuta(@RequestBody Ruta rutaNueva) {
		log.info("Creando Ruta");
		return rutaService.crearRuta(rutaNueva);
	}

	// ------------------------------------------------------------
	// --------------------------- READ ---------------------------
	// ------------------------------------------------------------

	@PreAuthorize("hasRole('CAPITAN') or hasRole('NAVEGANTE')")
	@GetMapping("/{id}")
	@Operation(summary = "Obtiene una ruta por su id")
	public Ruta obtenerRuta(@PathVariable Long id) throws RecordNotFoundException {
		log.info("Obtener Ruta por ID");
		return rutaService.obtenerRuta(id);
	}

	@PreAuthorize("hasRole('CAPITAN') or hasRole('NAVEGANTE')")
	@GetMapping("")
	@Operation(summary = "Obtiene todas las rutas")
	public List<Ruta> obtenerRutas() {
		log.info("Obtener todas las Rutas");
		return rutaService.obtenerRutas();
	}

	// ------------------------------------------------------------
	// -------------------------- UPDATE --------------------------
	// ------------------------------------------------------------

	@PreAuthorize("hasRole('CAPITAN') or hasRole('NAVEGANTE')")
	@PostMapping("/{id}")
	@Operation(summary = "Modifica una ruta")
	public Ruta modificarRuta(@RequestBody Ruta ruta, @PathVariable Long id) {
		log.info("modificar Ruta : " + ruta.getId());
		return rutaService.modificarRuta(ruta, ruta.getId());
	}

	// ------------------------------------------------------------
	// -------------------------- DELETE --------------------------
	// ------------------------------------------------------------

	@PreAuthorize("hasRole('CAPITAN') or hasRole('NAVEGANTE')")
	@DeleteMapping("/{id}")
	@Operation(summary = "Elimina una ruta")
	public void eliminarRutaById(@PathVariable Long id) {
		log.info("Eliminar Ruta por id" + id);
		rutaService.eliminarRuta(id);
	}
}