package com.soa.proyecto.dao;

import com.soa.proyecto.entidades.Planta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantaDAO extends JpaRepository<Planta, String> {
    Planta findByCodPlanta(String codPlanta);

    void deleteByCodPlanta(String codPlanta);
}
