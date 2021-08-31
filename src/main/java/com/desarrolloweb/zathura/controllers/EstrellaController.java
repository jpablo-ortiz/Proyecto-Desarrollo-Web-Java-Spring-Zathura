package com.desarrolloweb.zathura.controllers;

import com.desarrolloweb.zathura.repositories.EstrellaRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/estrella")
public class EstrellaController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private EstrellaRepository estrellaRepository;
}