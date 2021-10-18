package com.desarrolloweb.zathura.controllers;

import java.util.List;
import java.util.Optional;

import com.desarrolloweb.zathura.exceptions.RecordNotFoundException;
import com.desarrolloweb.zathura.models.Estrella;
import com.desarrolloweb.zathura.service.EstrellaService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Esta clase contiene los métodos para el manejo de los servicios rest 
 * 		de la entidad estrella
 * 
 * @author Kenneth Leonel Triana
 * @author Juan Pablo Ortiz Rubio
 * @version 2.0.0
 */
@RestController
@RequestMapping("/estrella")
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

	@RequestMapping(path = "/crear", method = RequestMethod.POST)
	public String crearEstrella(Estrella estrella) {
		log.info("Creando Estrella");

		// Service crear
		estrellaService.crearEstrella(estrella);

		// Redireccionar a la lista de estrellas
		return "redirect:/estrellas";
	}

	// ------------------------------------------------------------
	// --------------------------- READ ---------------------------
	// ------------------------------------------------------------

	@GetMapping("/{id}")
	public Estrella obtenerEstrella(@PathVariable Long id) {
		log.info("Obtener Estrella por ID");
		try {
			Estrella estrella = estrellaService.obtenerEstrella(id);
			return estrella;
		} catch (RecordNotFoundException e) {
			log.error("No se encontró estrella", e);
			return null;
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public String obtenerEstrellas(Model model) {
		log.info("Obtener todas las Estrellas");

		// Service obtenerEstrellas
		List<Estrella> lista = estrellaService.obtenerEstrellas();

		// Abrimos html listaEstrellas
		model.addAttribute("estrellas", lista);
		return "estrellas/listaEstrellas";

	}

	// ------------------------------------------------------------
	// -------------------------- UPDATE --------------------------
	// ------------------------------------------------------------

	@RequestMapping(path = "/estrella", method = RequestMethod.POST)
	public String modificarEstrella(Estrella estrella)  {
		log.info("modificar Estrella : " + estrella.getId());
		
		// Service actuaizarEstrella por id
		estrellaService.modificarEstrella(estrella, estrella.getId());

		// Redireccionar a la lista de estrellas
		return "redirect:/estrellas";
	}

	// ------------------------------------------------------------
	// -------------------------- DELETE --------------------------
	// ------------------------------------------------------------

	@RequestMapping(path = "/eliminar/{id}", method = RequestMethod.GET)
	public String eliminarEstrellaById(@PathVariable Long id, Model model) {
		log.info("Eliminar Estrella por id" + id);

		// Service eliminarEstrella por id
		estrellaService.eliminarEstrella(id);
		
		// Redireccionar a la lista de estrellas
		return "redirect:/estrellas";
	}

	// ------------------------------------------------------------
	// ------------------------- PANTALLAS ------------------------
	// ------------------------------------------------------------

	@RequestMapping(path = { "/edicionOCreacion", "/edicionOCreacion/{id}" })
	public String realizarAccionCreacionOEdicion(@PathVariable Optional<Long> id, Model model)
			throws RecordNotFoundException {
		if (id.isPresent()) {
			// Service obtenerEstrella por id
			Estrella estrella = estrellaService.obtenerEstrella(id.get());

			// Abrimos html editarEstrella
			model.addAttribute("estrella", estrella);
			return "estrellas/editarEstrella";
		} else {
			// Abrimos html crearEstrella
			model.addAttribute("estrella", new Estrella());
			return "estrellas/crearEstrella";
		}
	}

}