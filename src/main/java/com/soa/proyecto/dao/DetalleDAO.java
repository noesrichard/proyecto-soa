package com.soa.proyecto.dao;

import com.soa.proyecto.entidades.Detalle;
import com.soa.proyecto.entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DetalleDAO extends JpaRepository<Detalle, Integer> {

    public Detalle findByCodDetalle(Integer codDetalle);
    public Detalle findDetalleByPedido(Integer codPedido);
    public void deleteDetalleByCodDetalle(Integer codDetalle);

    List<Detalle> findByPedido(Pedido pedido);
}
