package com.soa.proyecto.dao;

import com.soa.proyecto.entidades.Pedido;
import com.soa.proyecto.entidades.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoDAO extends JpaRepository<Pedido, Integer> {
    Pedido findByCodPedido(Integer codPedido);

    void deleteByCodPedido(Integer codPedido);

    List<Pedido> findBySucursal(Sucursal sucursal);
}
