package com.desarrolloweb.zathura.repositories;

import java.util.List;

import com.desarrolloweb.zathura.models.Estrella;
import com.desarrolloweb.zathura.models.Nave;
import com.desarrolloweb.zathura.models.Planeta;
import com.desarrolloweb.zathura.models.Tripulante;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripulanteRepository extends PagingAndSortingRepository<Tripulante, Long> {

    // Listar todos los tripulantes por el id de la nave
    @Query("select t from Tripulante t where t.nave.id = ?1")
    List<Tripulante> findByNaveId(Long id);

    @Query("select e from Tripulante t join t.nave n join n.planetaActual p join p.estrella e where t.id = ?1")
    Estrella findEstrellaByIdTripulante(Long id);

    @Query("select p from Tripulante t join t.nave n join n.planetaActual p where t.id = ?1")
    Planeta findPlanetaByIdTripulante(Long id);

    @Query("select n from Tripulante t join t.nave n where t.id = ?1")
    Nave findNaveByIdTripulante(Long id);

    @Query("select t from Tripulante t where t.username = ?1")
    Tripulante findByUsername(String username);

}