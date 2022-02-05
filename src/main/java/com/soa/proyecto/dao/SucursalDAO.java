package com.soa.proyecto.dao;

import com.soa.proyecto.entidades.Cliente;
import com.soa.proyecto.entidades.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SucursalDAO extends JpaRepository<Sucursal, String> {
    Sucursal findByCodSucursal(String codSucursal);

    void deleteByCodSucursal(String codSucursal);

    List<Sucursal> findByCliente(Cliente cliente);
}
