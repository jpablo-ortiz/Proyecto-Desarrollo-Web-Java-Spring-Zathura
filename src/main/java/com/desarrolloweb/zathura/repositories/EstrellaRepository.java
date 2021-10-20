package com.desarrolloweb.zathura.repositories;

import com.desarrolloweb.zathura.models.Estrella;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstrellaRepository extends PagingAndSortingRepository<Estrella, Long> {

}

