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
@RequestMapping("/estrella")
public class EstrellaController {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private EstrellaService estrellaService;

	// CRUD - CREATE - READ - UPDATE - DELETE

	// ------------------------------------------------------------
	// -------------------------- CREATE --------------------------
	// ------------------------------------------------------------

	@RequestMapping(method = RequestMethod.POST)
	public String crearEstrella(Estrella estrella) {
		log.info("creando estrella");

		estrellaService.crearEstrella(estrella);

		return "redirect:/estrella";
	}

	// ------------------------------------------------------------
	// --------------------------- READ ---------------------------
	// ------------------------------------------------------------

	//@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	//public String obtenerEstrella(@PathVariable Long id, Model model) {
	//	log.info("obtener estrella por id");
	//	try {
	//		Estrella estrella = estrellaService.obtenerEstrella(id);
	//		model.addAttribute("estrella", estrella);
	//		return "MostrarEstrella";
	//	} catch (RecordNotFoundException e) {
	//		log.error("No se encontró estrella", e);
	//		// Implementar Error html page not found
	//		return "Error";
	//	}
	//}

	@RequestMapping(method = RequestMethod.GET)
	public String obtenerEstrellas(Model model) {
		log.info("obtener todas LasEstrellas");

		List<Estrella> lista = estrellaService.obtenerEstrellas();
		model.addAttribute("estrellas", lista);

		return "listaEstrellas";

	}

	// ------------------------------------------------------------
	// -------------------------- UPDATE --------------------------
	// ------------------------------------------------------------

	@RequestMapping(path = "/{id}", method = RequestMethod.POST)
	public String actualizarEstrella(Estrella estrella, @PathVariable("id") Long id) {

		//log.info("editarEstrellaById" + id);
		//estrellaService.actualizarEstrella(estrella, id);

		return "redirect:/estrella";
	}

	// ------------------------------------------------------------
	// -------------------------- DELETE --------------------------
	// ------------------------------------------------------------

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE, produces = "application/json", consumes = "application/x-www-form-urlencoded")
	public String eliminarEstrellaById(@PathVariable Long id, Model model){

		log.info("eliminarEstrellaById" + id);
		estrellaService.eliminarEstrellaById(id);

		return "redirect:/estrella";
	}

	// ------------------------------------------------------------
	// ------------------------- PANTALLAS ------------------------
	// ------------------------------------------------------------


	@RequestMapping(path = "/edicionOCreacion/{id}")
	public String realizarAccionCreacionOEdicion(@PathVariable Optional<Long> id, Model model)
			throws RecordNotFoundException {

		if (id.isPresent()) {
			Estrella estrella = estrellaService.obtenerEstrella(id.get());
			model.addAttribute("estrella", estrella);
		} else {
			model.addAttribute("estrella", new Estrella());
		}

		return "crearEstrella";
	}

}