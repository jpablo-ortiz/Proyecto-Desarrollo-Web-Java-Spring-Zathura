package com.desarrolloweb.zathura.controllers;

import java.util.List;
import java.util.Optional;

import com.desarrolloweb.zathura.exceptions.RecordNotFoundException;
import com.desarrolloweb.zathura.models.Estrella;
import com.desarrolloweb.zathura.service.EstrellaService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/estrellas")
public class EstrellaController {

	Logger log = LoggerFactory.getLogger(getClass());

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

	// @RequestMapping(path = "/estrella/{id}", method = RequestMethod.GET)
	// public Estrella obtenerEstrella(@PathVariable Long id) {
	// 	log.info("Obtener Estrella por ID");
	// 	try {
	// 		Estrella estrella = estrellaService.obtenerEstrella(id);
	// 		return estrella;
	// 	} catch (RecordNotFoundException e) {
	// 		log.error("No se encontró estrella", e);
	// 		return null;
	// 	}
	// }

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
	public String actualizarEstrella(Estrella estrella)  {
		log.info("Actualizar Estrella : " + estrella.getId());
		
		// Service actuaizarEstrella por id
		estrellaService.actualizarEstrella(estrella, estrella.getId());

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
		estrellaService.eliminarEstrellaById(id);
		
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