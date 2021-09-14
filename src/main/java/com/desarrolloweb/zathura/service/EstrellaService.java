package com.desarrolloweb.zathura.service;

import com.desarrolloweb.zathura.repositories.EstrellaRepository;
import com.desarrolloweb.zathura.models.Estrella;
import com.desarrolloweb.zathura.exception.RecordNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstrellaService{
 
    @Autowired
	EstrellaRepository repository;
	
	public List<Estrella> obtenerTodasLasEstrellas()
	{
		System.out.println("obteniendo todas las estrellas");
		List<Estrella> resultado = (List<Estrella>) repository.findAll();
		
		if(resultado.size() > 0) {
			return resultado;
		} else {
			return new ArrayList<Estrella>();
		}
	}

	
	public Estrella getEstrellaById(Long id) throws RecordNotFoundException 
	{
		System.out.println("obtener estrella por id");
		Optional<Estrella> estrella = repository.findById(id);
		
		if(estrella.isPresent()) {
			return estrella.get();
		} else {
			throw new RecordNotFoundException("No existe la estrella con dicha identificación");
		}
	}
	
	public Estrella crearOactualizarEstrella(Estrella entity) 
	{
		System.out.println("Creando o actualizando estrella");
	 
		if(entity.getId()  == null) 
		{
			entity = repository.save(entity);
			
			return entity;
		} 
		else 
		{
			// update existing entry 
			Optional<Estrella> estrella = repository.findById(entity.getId());
			
			if(estrella.isPresent()) 
			{
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
			    //Guardamos la nueva estrella
				nueva = repository.save(nueva);
				
				return nueva;
			} else {
				entity = repository.save(entity);
				
				return entity;
			}
		}
	} 
	
	public void eliminarEstrellaById(Long id) throws RecordNotFoundException 
	{
		System.out.println("Eliminar Estrella por id");
		
		Optional<Estrella> estrella = repository.findById(id);
		
		if(estrella.isPresent()) 
		{
			repository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No se encontro la estrella con dicho id");
		}
	} 
}
