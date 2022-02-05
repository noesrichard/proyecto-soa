package com.soa.proyecto.servicios;

import com.soa.proyecto.dao.PedidoDAO;
import com.soa.proyecto.entidades.Pedido;
import com.soa.proyecto.entidades.Sucursal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PedidoServicios implements Servicio<Pedido>{

    @Autowired
    private PedidoDAO pedidoDAO;
    @Override
    public List<Pedido> get() {
        return pedidoDAO.findAll();
    }

    @Override
    public Pedido get(Pedido entidad) {
        return pedidoDAO.findByCodPedido(entidad.getCodPedido());
    }

    @Override
    public Pedido crear(Pedido entidad) {
        return pedidoDAO.save(entidad);
    }

    @Override
    public Pedido edit(Pedido entidad) {
        Pedido p = pedidoDAO.findByCodPedido(entidad.getCodPedido());
        p.setFechaPedido(entidad.getFechaPedido());
        pedidoDAO.save(p);
        return entidad;
    }

    @Override
    public Pedido eliminar(Pedido entidad) {
        pedidoDAO.deleteByCodPedido(entidad.getCodPedido());
        return entidad;
    }

    public List<Pedido> getBySucursal(Sucursal sucursal){
        return pedidoDAO.findBySucursal(sucursal);
    }
}
