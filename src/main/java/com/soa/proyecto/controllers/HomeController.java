package com.soa.proyecto.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

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

}
