package com.desarrolloweb.zathura.repositories;

import com.desarrolloweb.zathura.models.Nave;
import com.desarrolloweb.zathura.models.NaveXProducto;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NaveRepository extends PagingAndSortingRepository<Nave, Long> {

    @Query("SELECT nxp FROM NaveXProducto nxp WHERE nxp.nave.id = ?1 AND nxp.producto.id = ?2 ")
    NaveXProducto findNaveXProducto(Long naveId, Long productoId);

}