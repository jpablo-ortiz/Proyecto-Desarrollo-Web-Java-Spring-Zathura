package com.desarrolloweb.zathura.controllers;

import java.util.List;

import com.desarrolloweb.zathura.exceptions.RecordNotFoundException;
import com.desarrolloweb.zathura.models.Planeta;
import com.desarrolloweb.zathura.models.PlanetaXProducto;
import com.desarrolloweb.zathura.models.Producto;
import com.desarrolloweb.zathura.service.PlanetaService;

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

	// ------------------------------------------------------------
	// --------------------------- OTROS --------------------------
	// ------------------------------------------------------------
	//Mirar
	//@PreAuthorize("hasRole('CAPITAN') or hasRole('NAVEGANTE')")
	// Obtener los planetas por el id de la nave
	@GetMapping("/estrella/{id}")
	@Operation(summary = "Obtiene los planetas por el id de la estrella")
	public List<Planeta> obtenerPlanetasPorEstrella(@PathVariable Long id) {
		log.info("Obtener Planetas por ID de la estrella");
		return planetaService.obtenerPlanetasPorEstrella(id);
	}

	// Obtener los productos por el id del PLANETA
	//Mirar
	//@PreAuthorize("hasRole('CAPITAN') or hasRole('COMERCIANTE')")
	@GetMapping("/productos/{id}")
	@Operation(summary = "Obtiene los productos por el id del Planeta")
	public List<Producto> obtenerProductosPorPlaneta(@PathVariable Long id) {
		log.info("Obtener productos por ID del planeta");
		return planetaService.obtenerProductosPorPlaneta(id);
	}
	//Mirar
	//@PreAuthorize("hasRole('CAPITAN') or hasRole('COMERCIANTE')")
	@GetMapping("/{planetaId}/producto/{productoId}")
	@Operation(summary = "Obtiene el PlanetaXProducto por el id del Planeta y del producto")
	public PlanetaXProducto obtenerPlanetaXProducto(@PathVariable Long planetaId, @PathVariable Long productoId) {
		log.info("Obtener planetaXProducto por ID del planeta y producto");
		return planetaService.obtenerPlanetaXProducto(planetaId, productoId);
	}

}