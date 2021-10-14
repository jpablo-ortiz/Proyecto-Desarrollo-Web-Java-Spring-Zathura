package com.desarrolloweb.zathura.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class MenuController {

    @RequestMapping(path = "/")
    public String menu() {
        return "menu";
    }

}
