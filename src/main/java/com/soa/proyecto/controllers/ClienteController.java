package com.soa.proyecto.controllers;

import com.soa.proyecto.entidades.Cliente;
import com.soa.proyecto.servicios.ClienteServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ClienteController {

    @Autowired
    private ClienteServicios clienteServicios;

    @GetMapping("clientes")
    public String getAll(Model model){
        List<Cliente> lista = clienteServicios.get();
        model.addAttribute("clientes", lista);
        return "clientes";
    }
}
