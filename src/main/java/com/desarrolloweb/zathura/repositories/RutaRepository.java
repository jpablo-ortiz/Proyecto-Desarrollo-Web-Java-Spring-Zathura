package com.desarrolloweb.zathura.repositories;

import java.util.List;

import com.desarrolloweb.zathura.models.Ruta;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RutaRepository extends PagingAndSortingRepository<Ruta, Long> {

    @Query("SELECT r FROM Estrella e JOIN e.rutasA r WHERE e.id = ?1")
    List<Ruta> obtenerRutasADeEstrellaId(Long id);

    @Query("SELECT r FROM Estrella e JOIN e.rutasB r WHERE e.id = ?1")
    List<Ruta> obtenerRutasBDeEstrellaId(Long id);

    @Query("SELECT r FROM Estrella e, Ruta r WHERE e.id = r.estrellaA.id "
    + "AND (r.estrellaA.id = ?1 AND r.estrellaB.id = ?2) OR (r.estrellaA.id = ?2 AND r.estrellaB.id = ?1)")
    Ruta obtenerRutaDeEstrellaAEstrella(Long idEstrellaO, Long idEstrellaD);

}