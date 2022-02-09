package com.soa.proyecto.controllers;

import com.soa.proyecto.UserDetailsServiceImpl;
import com.soa.proyecto.Usuario;
import com.soa.proyecto.entidades.*;
import com.soa.proyecto.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
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

    private List<Detalle> detalles = new ArrayList<>();
    List<ArticuloExistencias> listaArticulos = new ArrayList<>();

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

    @GetMapping("/clt/pedido/eliminar/{codPedido}")
    public String deletePedido(@PathVariable(name = "codPedido") Integer codPedido){
        System.out.println(codPedido);
        Pedido c = new Pedido();
        c.setCodPedido(codPedido);
        pedidoServicios.eliminar(c);
        detalles.clear();
        return "redirect:/clt/pedido";
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
        model.addAttribute("sucursales", sucursales);
        model.addAttribute("pedido", p);
        return "pedidos/pedidos-cliente";
    }

    @GetMapping("/clt/nuevo-pedido")
    public String nuevoPedido(@ModelAttribute("pedido") Pedido pedido,
                              Model model){
        if(!pedido.validar()) {
            return "redirect:/clt/pedido";
        }
        List<Articulo> articulos = articuloServicios.get();
        List<Articulo> articulosPlanta = new ArrayList<Articulo>();
        if(pedidoServicios.get(pedido) == null) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDateTime now = LocalDateTime.now();
            pedido.setFechaPedido(dtf.format(now));
            listaArticulos.clear();
            for(Articulo a : articulos) {
                listaArticulos.addAll(artPlantaServicios.getDistinctArticuloExistencias(a));
            }
            pedidoServicios.crear(pedido);
        }else {
            List<Detalle> detalles = detalleServicio.getByPedido(pedido);
            model.addAttribute("detalles", detalles);
            pedido = pedidoServicios.get(pedido);
        }
        Detalle d = new Detalle();
        d.setPedido(pedido);
        for(Articulo a : articulos) {
            articulosPlanta.addAll(artPlantaServicios.getDistinctArticulo(a));
        }
        System.out.println(listaArticulos);
        model.addAttribute("detalles", detalles);
        model.addAttribute("listaArticulos", listaArticulos);
        model.addAttribute("pedido", pedido);
        model.addAttribute("articulos", articulosPlanta);
        model.addAttribute("detalle",d);
        return "pedidos/nuevo-pedido";
    }

    @PostMapping("/clt/pedido/{codPedido}/agg-articulo/{codArticulo}")
    public String aggArticulo(@ModelAttribute(name = "detalle") Detalle detalle,
                                @PathVariable(name = "codArticulo") String codArticulo,
                              @PathVariable(name = "codPedido") Integer codPedido,
                              Model model){
        Pedido p = new Pedido();
        p.setCodPedido(codPedido);
        p = pedidoServicios.get(p);
        detalle.setPedido(p);

        Articulo busqueda = new Articulo();
        busqueda.setCodArticulo(codArticulo);
        busqueda = articuloServicios.get(busqueda);
        detalle.setArticulo(busqueda);

        int cantidadMaxima = 0;
        List<ArtPlanta> articulos = artPlantaServicios.getByArticulo(detalle.getArticulo());
        for(ArtPlanta articulo: articulos) {
            cantidadMaxima += articulo.getExistencias();
        }
        if(detalle.getCantidad() > cantidadMaxima) {
            return nuevoPedido(p, model);
        }
        detalles.add(detalle);
        eliminarDeLaLista(codArticulo);
        return nuevoPedido(p, model);
    }

    @GetMapping("/clt/pedido/{codPedido}/guardar")
    public String guardarPedido(@PathVariable(name = "codPedido") Integer codPedido){
        Pedido p = new Pedido();
        p.setCodPedido(codPedido);
        for(Detalle detalle: detalles) {
            detalle.setPedido(p);
            detalleServicio.crear(detalle);
            List<ArtPlanta> articulos = artPlantaServicios.getByArticulo(detalle.getArticulo());
            int cantidad = detalle.getCantidad();
            for (ArtPlanta articulo : articulos) {
                int resultado = articulo.getExistencias() - cantidad;
                if (resultado < 0) {
                    articulo.setExistencias(0);
                    artPlantaServicios.edit(articulo);
                    cantidad = resultado * (-1);
                } else if (resultado >= 0) {
                    articulo.setExistencias(resultado);
                    artPlantaServicios.edit(articulo);
                    break;
                }
            }
        }
        detalles.clear();
        return "redirect:/clt/pedido";
    }

    private void eliminarDeLaLista(String codArticulo){
        for(ArticuloExistencias a: listaArticulos) {
            if(a.getArticulo().getCodArticulo().equals(codArticulo)) {
                listaArticulos.remove(a);
                break;
            }
        }
    }
}
