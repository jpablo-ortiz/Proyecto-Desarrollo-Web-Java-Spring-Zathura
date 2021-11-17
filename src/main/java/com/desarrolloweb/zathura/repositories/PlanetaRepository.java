package com.desarrolloweb.zathura.repositories;

import java.util.List;

import com.desarrolloweb.zathura.models.Planeta;
import com.desarrolloweb.zathura.models.PlanetaXProducto;
import com.desarrolloweb.zathura.models.Producto;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetaRepository extends PagingAndSortingRepository<Planeta, Long> {
       // Listar todos los planetas por el id de la estrella
      @Query("select p from Planeta p where p.estrella.id = ?1")
      List<Planeta> findByEstrellaId(Long id);

      @Query("SELECT p FROM PlanetaXProducto pxp JOIN pxp.producto p WHERE pxp.planeta.id = ?1")
      List<Producto> findProductosByPlanetaId(Long id);

      @Query("SELECT pxp FROM PlanetaXProducto pxp WHERE pxp.planeta.id = ?1 AND pxp.producto.id = ?2 ")
      PlanetaXProducto findPlanetaXProductoByIds(Long planetaId, Long productoId);

      @Query("SELECT pxp FROM PlanetaXProducto pxp WHERE pxp.planeta.id = ?1")
      List<PlanetaXProducto> findPlanetaXProductoByIdPlaneta(Long idPlaneta);

}