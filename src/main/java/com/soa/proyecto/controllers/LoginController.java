package com.soa.proyecto.controllers;

import com.soa.proyecto.servicios.UsuarioServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @Autowired
    private UsuarioServicios usuarioServicios;

    @GetMapping("/")
    public String login(){
        return "login";
    }
}
