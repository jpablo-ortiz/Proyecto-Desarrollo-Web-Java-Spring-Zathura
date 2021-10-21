package com.desarrolloweb.zathura.repositories;

import com.desarrolloweb.zathura.models.NaveXProducto;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NaveXProductoRepository extends PagingAndSortingRepository<NaveXProducto, Long>{
    
}
