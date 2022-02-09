package com.soa.proyecto.controllers;

import com.soa.proyecto.entidades.ArtPlanta;
import com.soa.proyecto.entidades.Articulo;
import com.soa.proyecto.entidades.Planta;
import com.soa.proyecto.servicios.ArtPlantaServicios;
import com.soa.proyecto.servicios.ArticuloServicios;
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
public class ArtPlantaController {

    @Autowired
    private ArtPlantaServicios artPlantaServicios;

    @Autowired
    private ArticuloServicios articuloServicios;

    @Autowired
    private PlantaServicios plantaServicios;

    @GetMapping("/adm/artplanta")
    public String getAll(Model model){
        ArtPlanta s = new ArtPlanta();
        List<ArtPlanta> artPlantas = artPlantaServicios.get();
        List<Planta>  plantas = plantaServicios.get();
        List<Articulo> articulos = articuloServicios.get();
        model.addAttribute("artPlantas", artPlantas);
        model.addAttribute("plantas", plantas);
        model.addAttribute("articulos", articulos);
        model.addAttribute("artPlanta", s);
        return "artplantas/artplantas";
    }

    @PostMapping("/adm/artplanta/existente")
    public String saveExistente(@ModelAttribute("artPlanta") ArtPlanta artPlanta){
        System.out.println(artPlanta.getArticulo());
        if (artPlanta.validar()) {
            artPlantaServicios.crear(artPlanta);
        }
        return "redirect:/adm/artplanta";
    }
    @PostMapping("/adm/artplanta")
    public String save(@ModelAttribute("artPlanta") ArtPlanta artPlanta){
        if(artPlanta.validar()) {
            articuloServicios.crear(artPlanta.getArticulo());
            artPlantaServicios.crear(artPlanta);
        }
        return "redirect:/adm/artplanta";
    }

    @GetMapping("/adm/artplanta/{codArtPlanta}")
    public String getEditArtPlantaPage(@PathVariable(name = "codArtPlanta") Integer codArtPlanta, Model model){
        ArtPlanta artPlanta = new ArtPlanta();
        artPlanta.setCodArtPlanta(codArtPlanta);
        ArtPlanta artPlantaEncontrado = artPlantaServicios.get(artPlanta);
        model.addAttribute("artPlanta", artPlantaEncontrado);
        return "artplantas/edit-artplanta";
    }

    @PostMapping("/adm/artplanta/{codArtPlanta}")
    public String editArtPlanta(@ModelAttribute("artPlanta") ArtPlanta artPlanta){
        System.out.println(artPlanta);
        artPlantaServicios.edit(artPlanta);
        return "redirect:/adm/artplanta";
    }

    @GetMapping("/adm/artplanta/eliminar/{codArtPlanta}")
    public String deleteucursal(@PathVariable(name = "codArtPlanta") Integer codArtPlanta){
        System.out.println(codArtPlanta);
        ArtPlanta c = new ArtPlanta();
        c.setCodArtPlanta(codArtPlanta);
        artPlantaServicios.eliminar(c);
        return "redirect:/adm/artplanta";
    }
}
