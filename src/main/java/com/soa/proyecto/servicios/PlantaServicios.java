package com.soa.proyecto.servicios;

import com.soa.proyecto.dao.PlantaDAO;
import com.soa.proyecto.entidades.Planta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlantaServicios implements Servicio<Planta> {

    @Autowired
    private PlantaDAO plantaDAO;

    @Override
    public List<Planta> get() {
        return plantaDAO.findAll();
    }

    @Override
    public Planta get(Planta entidad) {
        return plantaDAO.findByCodPlanta(entidad.getCodPlanta());
    }

    @Override
    public Planta crear(Planta entidad) {
        return plantaDAO.save(entidad);
    }

    @Override
    public Planta edit(Planta entidad) {
        Planta c = plantaDAO.findByCodPlanta(entidad.getCodPlanta());
        c.setNomPlanta(entidad.getNomPlanta());
        c.setDireccion(entidad.getDireccion());
        c.setTelefono(entidad.getTelefono());
        return plantaDAO.save(c);
    }

    @Override
    public Planta eliminar(Planta entidad) {
        plantaDAO.deleteByCodPlanta(entidad.getCodPlanta());
        return entidad;
    }
}
