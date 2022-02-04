package com.soa.proyecto.dao;

import com.soa.proyecto.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteDAO extends JpaRepository<Cliente, String> {
    Cliente findByCodCliente(String codCliente);

    void deleteByCodCliente(String codCliente);
}
