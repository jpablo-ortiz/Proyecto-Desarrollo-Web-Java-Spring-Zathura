package com.desarrolloweb.zathura.repositories;

import com.desarrolloweb.zathura.models.PlanetaXProducto;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetaXProductoRepository extends PagingAndSortingRepository<PlanetaXProducto, Long> {

}
