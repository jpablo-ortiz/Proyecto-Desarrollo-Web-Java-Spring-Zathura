package com.desarrolloweb.zathura.service;

import java.util.List;

import com.desarrolloweb.zathura.exceptions.RecordNotFoundException;
import com.desarrolloweb.zathura.models.Estrella;
import com.desarrolloweb.zathura.repositories.EstrellaRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstrellaService {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private EstrellaRepository estrellaRepository;

	// CRUD - CREATE - READ - UPDATE - DELETE

	// ------------------------------------------------------------
	// -------------------------- CREATE --------------------------
	// ------------------------------------------------------------

	// Post
	public Estrella crearEstrella(Estrella estrella) {
		estrella = estrellaRepository.save(estrella);
		return estrella;
	}

	// ------------------------------------------------------------
	// --------------------------- READ ---------------------------
	// ------------------------------------------------------------

	// Get
	public Estrella obtenerEstrella(Long id) throws RecordNotFoundException {
		Estrella estrella = estrellaRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("No se encontro estrella con id: " + id));
		return estrella;
	}

	// Get
	public List<Estrella> obtenerEstrellas() {
		List<Estrella> resultado = (List<Estrella>) estrellaRepository.findAll();
		return resultado;
	}

	// ------------------------------------------------------------
	// -------------------------- UPDATE --------------------------
	// ------------------------------------------------------------

	// Post
	public Estrella actualizarEstrella(Estrella plantilla, Long id) {
		return estrellaRepository.findById(id).map(estrella -> {
			estrella.setNombre(plantilla.getNombre());
			estrella.setHabitado(plantilla.getHabitado());
			estrella.setRecurso(plantilla.getRecurso());
			// nueva.setPlanetas(entity.getPlanetas());
			// nueva.setRutasA(entity.getRutasA());
			// nueva.setRutasB(entity.getRutasB());
			estrella.setX(plantilla.getX());
			estrella.setY(plantilla.getY());
			estrella.setZ(plantilla.getZ());

			return estrellaRepository.save(estrella);
		}).orElseGet(() -> {
			plantilla.setId(id);
			return estrellaRepository.save(plantilla);
		});
	}

	// ------------------------------------------------------------
	// -------------------------- DELETE --------------------------
	// ------------------------------------------------------------

	// Delete (Pero usamos GET)
	public void eliminarEstrellaById(Long id) {
		estrellaRepository.deleteById(id);
	}
}
