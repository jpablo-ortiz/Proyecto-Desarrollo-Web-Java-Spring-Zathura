package com.desarrolloweb.zathura.controllers;

import com.desarrolloweb.zathura.models.Estrella;
import com.desarrolloweb.zathura.service.EstrellaService;
import com.desarrolloweb.zathura.exception.RecordNotFoundException;


import java.util.List;
import java.util.Optional;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMethod;




@Controller
@RequestMapping("/")
public class EstrellaController {

    //Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    EstrellaService service;
    //private EstrellaRepository estrellaRepository;

    @RequestMapping
    public String obtenerTodasLasEstrellas( Model model ){
        
		System.out.println("obtenerTodasLasEstrellas");
       List <Estrella> lista = service.obtenerTodasLasEstrellas();
        model.addAttribute("estrellas", lista);
       return "listaEstrellas";
    }
    @RequestMapping(path = {"/editar", "/editar/{id}"})
	public String editarEstrellaById(Model model, @PathVariable("id") Optional<Long> id) 
							throws RecordNotFoundException 
	{
		
		System.out.println("editarEstrellaById" + id);
		if (id.isPresent()) {
			Estrella entity = service.getEstrellaById(id.get());
			model.addAttribute("estrella", entity);
		} else {
			model.addAttribute("estrella", new Estrella());
		}
		
		return "crearEstrella";
	}
    @RequestMapping(path = "/eliminar/{id}")
	public String eliminarEstrellaById(Model model, @PathVariable("id") Long id) 
							throws RecordNotFoundException 
	{
		
		System.out.println("eliminarEstrellaById" + id);
		
		service.eliminarEstrellaById(id);
		return "redirect:/";
	}

	@RequestMapping(path = "/crearEstrella", method = RequestMethod.POST)
	public String crearOactualizarEstrella(Estrella estrella) 
	{
		System.out.println("creando estrella ");
		
		service.crearOactualizarEstrella(estrella);
		
		return "redirect:/";
	}


}