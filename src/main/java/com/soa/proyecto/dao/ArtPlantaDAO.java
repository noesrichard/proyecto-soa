package com.soa.proyecto.dao;

import com.soa.proyecto.entidades.ArtPlanta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtPlantaDAO extends JpaRepository<ArtPlanta, Integer> {
    ArtPlanta findByCodArtPlanta(Integer codArtPlanta);

    void deleteByCodArtPlanta(Integer codArtPlanta);
}
