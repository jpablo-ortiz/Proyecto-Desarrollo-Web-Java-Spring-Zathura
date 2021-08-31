package com.desarrolloweb.zathura.repositories;

import com.desarrolloweb.zathura.models.ModeloNave;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeloNaveRepository extends PagingAndSortingRepository<ModeloNave, Long> {

}