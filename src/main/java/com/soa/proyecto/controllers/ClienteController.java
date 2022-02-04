package com.soa.proyecto.controllers;

import com.soa.proyecto.entidades.Cliente;
import com.soa.proyecto.servicios.ClienteServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClienteController {

    @Autowired
    private ClienteServicios clienteServicios;

    @GetMapping("/cliente")
    public String getAll(Model model){
        List<Cliente> lista = clienteServicios.get();
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("clientes", lista);
        return "clientes/clientes";
    }

    @PostMapping("/cliente")
    public String save(@ModelAttribute("cliente") Cliente cliente){
        System.out.println(cliente);
        clienteServicios.crear(cliente);
        return "redirect:/cliente";
    }

    @GetMapping("/cliente/{codCliente}")
    public String getEditClientePage(@PathVariable(name = "codCliente") String codCliente, Model model){
        Cliente cliente = new Cliente();
        cliente.setCodCliente(codCliente);
        Cliente clienteEncontrado = clienteServicios.get(cliente);
        model.addAttribute("cliente", clienteEncontrado);
        return "clientes/edit-cliente";
    }

    @PostMapping("/cliente/{codCliente}")
    public String editCliente(@ModelAttribute("cliente") Cliente cliente){
        System.out.println(cliente);
        clienteServicios.edit(cliente);
        return "redirect:/cliente";
    }

    @GetMapping("/cliente/eliminar/{codCliente}")
    public String deleteCliente(@PathVariable(name = "codCliente") String codCliente){
        System.out.println(codCliente);
        Cliente c = new Cliente();
        c.setCodCliente(codCliente);
        clienteServicios.eliminar(c);
        return "redirect:/cliente";
    }
}
