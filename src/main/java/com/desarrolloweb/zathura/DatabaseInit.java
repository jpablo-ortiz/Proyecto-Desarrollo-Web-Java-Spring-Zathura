package com.desarrolloweb.zathura;

import com.desarrolloweb.zathura.repositories.EstrellaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class DatabaseInit implements ApplicationRunner {

    @Autowired
    private EstrellaRepository estrellaRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        
    }
    
}
