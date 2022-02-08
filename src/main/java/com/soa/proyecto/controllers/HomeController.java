package com.soa.proyecto.controllers;

import com.soa.proyecto.UserDetailsServiceImpl;
import com.soa.proyecto.Usuario;
import com.soa.proyecto.servicios.ClienteServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    private UserDetailsServiceImpl userService;

    @Autowired
    private ClienteServicios clienteServicios;

    @GetMapping("/")
    public String home(Principal principal){
        System.out.println(principal);
        if(principal == null) {
            return "redirect:/login";
        }
        return "plantilla";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/signin")
    public String signin(Model model){
        Usuario u = new Usuario();
        model.addAttribute("usuario", u);
        return "/signin";
    }

    @PostMapping("/nuevo-usuario")
    public String signin(@ModelAttribute(name = "usuario") Usuario u){
        clienteServicios.crear(u.getCliente());
        userService.crear(u);
        return "redirect:/login";
    }

}
