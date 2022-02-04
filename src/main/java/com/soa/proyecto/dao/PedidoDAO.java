package com.soa.proyecto.dao;

import com.soa.proyecto.entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoDAO extends JpaRepository<Pedido, Long> {
    Pedido findByCodPedido(Long codPedido);

    void deleteByCodPedido(Long codPedido);
}
