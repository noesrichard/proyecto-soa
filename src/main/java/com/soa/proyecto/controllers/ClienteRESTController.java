package com.soa.proyecto.controllers;

import com.soa.proyecto.entidades.Cliente;
import com.soa.proyecto.servicios.ClienteServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/cliente")
public class ClienteRESTController {
    @Autowired
    private ClienteServicios clienteServicios;
    @GetMapping
    public ResponseEntity<?> getAll(){
        Map<String, Object> response = new HashMap<>();
        List<Cliente> lista = clienteServicios.get();
        if (lista.size() > 0) {
            return new ResponseEntity<List<Cliente>>(lista, HttpStatus.OK);
        }
        response.put("mensaje", "No hay estudiantes en la base de datos.");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
    }
}
