package com.soa.proyecto.controllers;

import com.soa.proyecto.UserDetailsServiceImpl;
import com.soa.proyecto.Usuario;
import com.soa.proyecto.entidades.*;
import com.soa.proyecto.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PedidoController {

    @Autowired
    private PedidoServicios pedidoServicios;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private SucursalServicios sucursalServicios;

    @Autowired
    private ArticuloServicios articuloServicios;

    @Autowired
    private ArtPlantaServicios artPlantaServicios;

    @Autowired
    private DetalleServicio detalleServicio;


    @GetMapping("/adm/pedido")
    public String getAll(Model model){
        Pedido s = new Pedido();
        List<Pedido> pedidos = pedidoServicios.get();
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("pedido", s);
        return "pedidos/pedido";
    }

    @PostMapping("/adm/pedido")
    public String save(@ModelAttribute("pedido") Pedido pedido){
        pedidoServicios.crear(pedido);
        return "redirect:/adm/pedido";
    }


    @GetMapping("/adm/pedido/{codPedido}")
    public String getEditPedidoPage(@PathVariable(name = "codPedido") Integer codPedido, Model model){
        Pedido pedido = new Pedido();
        pedido.setCodPedido(codPedido);
        Pedido pedidoEncontrado = pedidoServicios.get(pedido);
        model.addAttribute("pedido", pedidoEncontrado);
        return "pedidos/edit-pedido";
    }

    @PostMapping("/adm/pedido/{codPedido}")
    public String editPedido(@ModelAttribute("pedido") Pedido pedido){
        System.out.println(pedido);
        pedidoServicios.edit(pedido);
        return "redirect:/adm/pedido";
    }

    @GetMapping("/adm/pedido/eliminar/{codPedido}")
    public String deleteucursal(@PathVariable(name = "codPedido") Integer codPedido){
        System.out.println(codPedido);
        Pedido c = new Pedido();
        c.setCodPedido(codPedido);
        pedidoServicios.eliminar(c);
        return "redirect:/adm/pedido";
    }

    @GetMapping("/pedido/{codPedido}")
    public String verPedido(@PathVariable(name = "codPedido") Integer codPedido, Model model){
        Pedido p = new Pedido();
        p.setCodPedido(codPedido);
        p = pedidoServicios.get(p);
        List<Detalle> detalles = detalleServicio.getByPedido(p);
        model.addAttribute("detalles", detalles);
        model.addAttribute("pedido", p);
        return "pedidos/ver-pedido";
    }

    @GetMapping("/clt/pedido")
    public String getPedidoCliente(Principal principal, Model model){
        String username = principal.getName();
        Usuario u = new Usuario();
        u.setUsername(username);
        Usuario usuario = userDetailsService.get(u);

        Pedido p = new Pedido();

        List<Pedido> pedidos = new ArrayList<Pedido>();
        List<Sucursal> sucursales = sucursalServicios.getByCliente(usuario.getCliente());

        for(Sucursal sucursal : sucursales) {
            pedidos.addAll( pedidoServicios.getBySucursal(sucursal));
        }
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("pedido", p);
        return "pedidos/pedidos-cliente";
    }

    @GetMapping("/clt/nuevo-pedido")
    public String nuevoPedido(@ModelAttribute("pedido") Pedido pedido,
                              Model model){
        if(pedidoServicios.get(pedido) == null) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDateTime now = LocalDateTime.now();
            pedido.setFechaPedido(dtf.format(now));
            pedidoServicios.crear(pedido);
        }else {
            List<Detalle> detalles = detalleServicio.getByPedido(pedido);
            model.addAttribute("detalles", detalles);
            pedido = pedidoServicios.get(pedido);
        }
        Detalle d = new Detalle();
        d.setPedido(pedido);
        List<ArtPlanta> lista = artPlantaServicios.get();
        model.addAttribute("artPlantas", lista);
        model.addAttribute("pedido", pedido);
        model.addAttribute("detalle",d);
        return "pedidos/nuevo-pedido";
    }

    @PostMapping("/clt/pedido/agg-articulo")
    public String aggArticulo(@ModelAttribute(name = "detalle") Detalle detalle, Model model){
        System.out.println(detalle);
        List<ArtPlanta> articulos = artPlantaServicios.getByArticulo(detalle.getArticulo());
        Pedido p = pedidoServicios.get(detalle.getPedido());
        int cantidadMaxima = 0;
        for(ArtPlanta articulo: articulos) {
            cantidadMaxima += articulo.getExistencias();
        }
        if(detalle.getCantidad() > cantidadMaxima) {
            return nuevoPedido(p, model);
        }
        detalle.setPedido(p);
        detalleServicio.crear(detalle);
        int cantidad = detalle.getCantidad();
        for(ArtPlanta articulo: articulos) {
            int resultado = articulo.getExistencias() - cantidad;
            if( resultado < 0) {
                articulo.setExistencias(0);
                artPlantaServicios.edit(articulo);
                cantidad = resultado*(-1);
            }
            else if( resultado >= 0) {
                articulo.setExistencias(resultado);
                artPlantaServicios.edit(articulo);
                break;
            }
        }
        List<Detalle> detalles = detalleServicio.getByPedido(p);

        model.addAttribute("detalles", detalles);
        model.addAttribute("pedido", p);
        return nuevoPedido(p,model);
    }


}
