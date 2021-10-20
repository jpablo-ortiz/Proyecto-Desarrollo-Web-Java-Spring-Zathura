package com.desarrolloweb.zathura.repositories;

import java.util.List;

import com.desarrolloweb.zathura.models.Tripulante;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripulanteRepository extends PagingAndSortingRepository<Tripulante, Long> {

    // Listar todos los tripulantes por el id de la nave
    @Query("select t from Tripulante t where t.nave.id = ?1")
    List<Tripulante> findByNaveId(Long id);

}