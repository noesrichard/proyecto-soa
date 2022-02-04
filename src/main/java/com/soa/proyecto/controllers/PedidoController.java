package com.soa.proyecto.controllers;

import com.soa.proyecto.entidades.Pedido;
import com.soa.proyecto.servicios.PedidoServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PedidoController {

    @Autowired
    private PedidoServicios pedidoServicios;

    @GetMapping("/pedido")
    public String getAll(Model model){
        Pedido s = new Pedido();
        List<Pedido> pedidos = pedidoServicios.get();
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("pedido", s);
        return "pedidos/pedido";
    }

    @PostMapping("/pedido")
    public String save(@ModelAttribute("pedido") Pedido pedido){
        pedidoServicios.crear(pedido);
        return "redirect:/pedido";
    }


    @GetMapping("/pedido/{codPedido}")
    public String getEditPedidoPage(@PathVariable(name = "codPedido") String codPedido, Model model){
        Pedido pedido = new Pedido();
        pedido.setCodPedido(codPedido);
        Pedido pedidoEncontrado = pedidoServicios.get(pedido);
        model.addAttribute("pedido", pedidoEncontrado);
        return "pedidos/edit-pedido";
    }

    @PostMapping("/pedido/{codPedido}")
    public String editPedido(@ModelAttribute("pedido") Pedido pedido){
        System.out.println(pedido);
        pedidoServicios.edit(pedido);
        return "redirect:/pedido";
    }

    @GetMapping("/pedido/eliminar/{codPedido}")
    public String deleteucursal(@PathVariable(name = "codPedido") String codPedido){
        System.out.println(codPedido);
        Pedido c = new Pedido();
        c.setCodPedido(codPedido);
        pedidoServicios.eliminar(c);
        return "redirect:/pedido";
    }
}
