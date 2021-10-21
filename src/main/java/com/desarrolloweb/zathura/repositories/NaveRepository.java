package com.desarrolloweb.zathura.repositories;

import com.desarrolloweb.zathura.models.Nave;
import com.desarrolloweb.zathura.models.NaveXProducto;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NaveRepository extends PagingAndSortingRepository<Nave, Long> {

    @Query("SELECT p FROM NaveXProducto pxp JOIN pxp.producto p WHERE pxp.nave.id = ?1 AND pxp.producto.id = ?2 ")
    NaveXProducto findNaveXProducto(Long naveId, Long productoId);

}