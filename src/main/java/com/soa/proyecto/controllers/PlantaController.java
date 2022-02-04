package com.soa.proyecto.controllers;

import com.soa.proyecto.entidades.Planta;
import com.soa.proyecto.servicios.PlantaServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PlantaController {

    @Autowired
    private PlantaServicios plantaServicios;

    @GetMapping("/planta")
    public String getAll(Model model){
        List<Planta> lista = plantaServicios.get();
        model.addAttribute("planta", new Planta());
        model.addAttribute("plantas", lista);
        return "plantas/plantas";
    }

    @PostMapping("/planta")
    public String save(@ModelAttribute("planta") Planta planta){
        System.out.println(planta);
        plantaServicios.crear(planta);
        return "redirect:/planta";
    }

    @GetMapping("/planta/{codPlanta}")
    public String getEditPlantaPage(@PathVariable(name = "codPlanta") String codPlanta, Model model){
        Planta planta = new Planta();
        planta.setCodPlanta(codPlanta);
        Planta plantaEncontrado = plantaServicios.get(planta);
        model.addAttribute("planta", plantaEncontrado);
        return "plantas/edit-planta";
    }

    @PostMapping("/planta/{codPlanta}")
    public String editPlanta(@ModelAttribute("planta") Planta planta){
        System.out.println(planta);
        plantaServicios.edit(planta);
        return "redirect:/planta";
    }

    @GetMapping("/planta/eliminar/{codPlanta}")
    public String deletePlanta(@PathVariable(name = "codPlanta") String codPlanta){
        System.out.println(codPlanta);
        Planta c = new Planta();
        c.setCodPlanta(codPlanta);
        plantaServicios.eliminar(c);
        return "redirect:/planta";
    }
}
