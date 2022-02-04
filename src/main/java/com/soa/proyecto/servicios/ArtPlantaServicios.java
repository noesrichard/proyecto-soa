package com.soa.proyecto.servicios;

import com.soa.proyecto.dao.ArtPlantaDAO;
import com.soa.proyecto.entidades.ArtPlanta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ArtPlantaServicios implements Servicio<ArtPlanta>{

    @Autowired
    private ArtPlantaDAO artPlantaDAO;
    @Override
    public List<ArtPlanta> get() {
        return artPlantaDAO.findAll();
    }

    @Override
    public ArtPlanta get(ArtPlanta entidad) {
        return artPlantaDAO.findByCodArtPlanta(entidad.getCodArtPlanta());
    }

    @Override
    public ArtPlanta crear(ArtPlanta entidad) {
        return artPlantaDAO.save(entidad);
    }

    @Override
    public ArtPlanta edit(ArtPlanta entidad) {
        ArtPlanta p = artPlantaDAO.findByCodArtPlanta(entidad.getCodArtPlanta());
        p.setExistencias(entidad.getExistencias());
        p.setNivelRiesgo(entidad.getNivelRiesgo());
        artPlantaDAO.save(p);
        return entidad;
    }

    @Override
    public ArtPlanta eliminar(ArtPlanta entidad) {
        artPlantaDAO.deleteByCodArtPlanta(entidad.getCodArtPlanta());
        return entidad;
    }
}
