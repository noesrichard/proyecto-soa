package com.soa.proyecto.controllers;

import com.soa.proyecto.entidades.Articulo;
import com.soa.proyecto.servicios.ArticuloServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ArticuloController {

    @Autowired
    private ArticuloServicios articuloServicios;

    @GetMapping("/articulo")
    public String getAll(Model model){
        List<Articulo> lista = articuloServicios.get();
        model.addAttribute("articulo", new Articulo());
        model.addAttribute("articulos", lista);
        return "articulos/articulos";
    }

    @PostMapping("/articulo")
    public String save(@ModelAttribute("articulo") Articulo articulo){
        System.out.println(articulo);
        articuloServicios.crear(articulo);
        return "redirect:/articulo";
    }

    @GetMapping("/articulo/{codArticulo}")
    public String getEditArticuloPage(@PathVariable(name = "codArticulo") String codArticulo, Model model){
        Articulo articulo = new Articulo();
        articulo.setCodArticulo(codArticulo);
        Articulo articuloEncontrado = articuloServicios.get(articulo);
        model.addAttribute("articulo", articuloEncontrado);
        return "articulos/edit-articulo";
    }

    @PostMapping("/articulo/{codArticulo}")
    public String editArticulo(@ModelAttribute("articulo") Articulo articulo){
        System.out.println(articulo);
        articuloServicios.edit(articulo);
        return "redirect:/articulo";
    }

    @GetMapping("/articulo/eliminar/{codArticulo}")
    public String deleteArticulo(@PathVariable(name = "codArticulo") String codArticulo){
        System.out.println(codArticulo);
        Articulo c = new Articulo();
        c.setCodArticulo(codArticulo);
        articuloServicios.eliminar(c);
        return "redirect:/articulo";
    }
}
