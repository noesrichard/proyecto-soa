package com.soa.proyecto.dao;

import com.soa.proyecto.entidades.ArtPlanta;
import com.soa.proyecto.entidades.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArtPlantaDAO extends JpaRepository<ArtPlanta, Integer> {
    ArtPlanta findByCodArtPlanta(Integer codArtPlanta);

    void deleteByCodArtPlanta(Integer codArtPlanta);

    List<ArtPlanta> findByArticulo(Articulo articulo);

    @Query("select distinct a.articulo from ArtPlanta a where a.articulo = ?1")
    List<Articulo> findDistinctByArticulo(Articulo articulo);

    @Query("select distinct a.articulo, sum(a.existencias) as existencias from ArtPlanta a where a.articulo = ?1")
    List<Object []> findDistinctByArticuloAndExistencias(Articulo articulo);
}
