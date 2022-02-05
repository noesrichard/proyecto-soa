package com.soa.proyecto.servicios;

import com.soa.proyecto.dao.DetalleDAO;
import com.soa.proyecto.entidades.Detalle;
import com.soa.proyecto.entidades.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DetalleServicio implements Servicio<Detalle> {

    @Autowired
    private DetalleDAO detalleDAO;

    @Override
    public List<Detalle> get() {
        return detalleDAO.findAll();
    }

    @Override
    public Detalle get(Detalle entidad) {
        return detalleDAO.findByCodDetalle(entidad.getCodDetalle());
    }

    @Override
    public Detalle crear(Detalle entidad) {
        return detalleDAO.save(entidad);
    }

    @Override
    public Detalle edit(Detalle entidad) {
        Detalle d = detalleDAO.findByCodDetalle(entidad.getCodDetalle());
        d.setArticulo(entidad.getArticulo());
        d.setPedido(entidad.getPedido());
        d.setCantidad(entidad.getCantidad());
        return entidad;
    }

    @Override
    public Detalle eliminar(Detalle entidad) {
        detalleDAO.deleteDetalleByCodDetalle(entidad.getCodDetalle());
        return entidad;
    }

    public List<Detalle> getByPedido(Pedido pedido){
        return detalleDAO.findByPedido(pedido);
    }
}
