package com.desarrolloweb.zathura.repositories;

import java.util.List;

import com.desarrolloweb.zathura.models.Planeta;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetaRepository extends PagingAndSortingRepository<Planeta, Long> {
       // Listar todos los planetas por el id de la estrella
       @Query("select p from Planeta p where p.estrella.id = ?1")
       List<Planeta> findByEstrellaId(Long id);
}