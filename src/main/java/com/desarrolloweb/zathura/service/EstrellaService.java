package com.desarrolloweb.zathura.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.desarrolloweb.zathura.exception.RecordNotFoundException;
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
	EstrellaRepository repository;

	public List<Estrella> obtenerTodasLasEstrellas() {
		log.info("obteniendo todas las estrellas");
		List<Estrella> resultado = (List<Estrella>) repository.findAll();

		if (resultado.size() > 0) {
			return resultado;
		} else {
			return new ArrayList<Estrella>();
		}
	}

	public Estrella getEstrellaById(Long id) throws RecordNotFoundException {
		log.info("obtener estrella por id");
		Optional<Estrella> estrella = repository.findById(id);

		if (estrella.isPresent()) {
			return estrella.get();
		} else {
			throw new RecordNotFoundException("No existe la estrella con dicha identificación");
		}
	}

	public Estrella crearOactualizarEstrella(Estrella entity) {
		log.info("Creando o actualizando estrella");

		if (entity.getId() == null) {
			entity = repository.save(entity);

			return entity;
		} else {
			// update existing entry
			Optional<Estrella> estrella = repository.findById(entity.getId());

			if (estrella.isPresent()) {
				Estrella nueva = estrella.get();
				nueva.setNombre(entity.getNombre());
				nueva.setHabitado(entity.getHabitado());
				nueva.setRecurso(entity.getRecurso());
				// nueva.setPlanetas(entity.getPlanetas());
				// nueva.setRutasA(entity.getRutasA());
				// nueva.setRutasB(entity.getRutasB());
				nueva.setX(entity.getX());
				nueva.setY(entity.getY());
				nueva.setZ(entity.getZ());
				// Guardamos la nueva estrella
				nueva = repository.save(nueva);

				return nueva;
			} else {
				entity = repository.save(entity);

				return entity;
			}
		}
	}

	public void eliminarEstrellaById(Long id) throws RecordNotFoundException {
		log.info("Eliminar Estrella por id");

		Optional<Estrella> estrella = repository.findById(id);

		if (estrella.isPresent()) {
			repository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No se encontro la estrella con dicho id");
		}
	}
}
