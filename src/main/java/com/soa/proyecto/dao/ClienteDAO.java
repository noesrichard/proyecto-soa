package com.soa.proyecto.dao;

import com.soa.proyecto.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ClienteDAO extends JpaRepository<Cliente, String> {
    Cliente findByCodCliente(String codCliente);

}
