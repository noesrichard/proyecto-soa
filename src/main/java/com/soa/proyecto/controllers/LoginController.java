package com.soa.proyecto.controllers;

import com.soa.proyecto.entidades.Usuario;
import com.soa.proyecto.servicios.UsuarioServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UsuarioServicios usuarioServicios;

    @GetMapping("/")
    public String login(){
        List<Usuario> usuarios = usuarioServicios.get();
        return "login";
    }

    @GetMapping("/home")
    public String home(){
        return "plantilla";
    }
}
