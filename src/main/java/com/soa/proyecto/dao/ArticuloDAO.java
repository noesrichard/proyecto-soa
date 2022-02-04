package com.soa.proyecto.dao;

import com.soa.proyecto.entidades.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticuloDAO extends JpaRepository<Articulo, String> {
    Articulo findByCodArticulo(String codArticulo);

    void deleteByCodArticulo(String codArticulo);
}
