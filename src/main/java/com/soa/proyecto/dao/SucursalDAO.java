package com.soa.proyecto.dao;

import com.soa.proyecto.entidades.Cliente;
import com.soa.proyecto.entidades.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SucursalDAO extends JpaRepository<Sucursal, String> {
    Sucursal findByCodSucursal(String codCliente);

    void deleteByCodSucursal(String codCliente);
}
