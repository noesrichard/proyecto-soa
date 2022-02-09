package com.soa.proyecto.controllers;

import com.soa.proyecto.entidades.Cliente;
import com.soa.proyecto.entidades.Sucursal;
import com.soa.proyecto.entidades.Sucursal;
import com.soa.proyecto.servicios.ClienteServicios;
import com.soa.proyecto.servicios.SucursalServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SucursalController {

    @Autowired
    private SucursalServicios sucursalServicios;

    @Autowired
    private ClienteServicios clienteServicios;

    @GetMapping("/adm/sucursal")
    public String getAll(Model model){
        Sucursal s = new Sucursal();
        List<Sucursal> sucursales = sucursalServicios.get();
        List<Cliente> clientes = clienteServicios.get();
        model.addAttribute("sucursales", sucursales);
        model.addAttribute("clientes", clientes);
        model.addAttribute("sucursal", s);
        return "sucursales/sucursal";
    }

    @PostMapping("/adm/sucursal")
    public String save(@ModelAttribute("sucursal") Sucursal sucursal){
        if(sucursal.validar()) {
            sucursalServicios.crear(sucursal);
        }
        return "redirect:/adm/sucursal";
    }


    @GetMapping("/adm/sucursal/{codSucursal}")
    public String getEditSucursalPage(@PathVariable(name = "codSucursal") String codSucursal, Model model){
        Sucursal sucursal = new Sucursal();
        sucursal.setCodSucursal(codSucursal);
        Sucursal sucursalEncontrado = sucursalServicios.get(sucursal);
        List<Cliente> clientes = clienteServicios.get();
        model.addAttribute("sucursal", sucursalEncontrado);
        model.addAttribute("clientes", clientes);
        return "sucursales/edit-sucursal";
    }

    @PostMapping("/adm/sucursal/{codSucursal}")
    public String editSucursal(@ModelAttribute("sucursal") Sucursal sucursal){
        System.out.println(sucursal);
        sucursalServicios.edit(sucursal);
        return "redirect:/adm/sucursal";
    }

    @GetMapping("/adm/sucursal/eliminar/{codSucursal}")
    public String deleteucursal(@PathVariable(name = "codSucursal") String codSucursal){
        System.out.println(codSucursal);
        Sucursal c = new Sucursal();
        c.setCodSucursal(codSucursal);
        sucursalServicios.eliminar(c);
        return "redirect:/adm/sucursal";
    }
}
