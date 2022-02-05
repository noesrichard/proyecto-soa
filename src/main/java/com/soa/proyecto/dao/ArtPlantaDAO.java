package com.soa.proyecto.dao;

import com.soa.proyecto.entidades.ArtPlanta;
import com.soa.proyecto.entidades.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtPlantaDAO extends JpaRepository<ArtPlanta, Integer> {
    ArtPlanta findByCodArtPlanta(Integer codArtPlanta);

    void deleteByCodArtPlanta(Integer codArtPlanta);

    List<ArtPlanta> findByArticulo(Articulo articulo);
}
