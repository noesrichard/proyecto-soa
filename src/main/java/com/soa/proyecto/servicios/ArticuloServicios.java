package com.soa.proyecto.servicios;

import com.soa.proyecto.dao.ArticuloDAO;
import com.soa.proyecto.entidades.Articulo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ArticuloServicios implements Servicio<Articulo> {

    @Autowired
    private ArticuloDAO articuloDAO;

    @Override
    public List<Articulo> get() {
        return articuloDAO.findAll();
    }

    @Override
    public Articulo get(Articulo entidad) {
        return articuloDAO.findByCodArticulo(entidad.getCodArticulo());
    }

    @Override
    public Articulo crear(Articulo entidad) {
        return articuloDAO.save(entidad);
    }

    @Override
    public Articulo edit(Articulo entidad) {
        Articulo c = articuloDAO.findByCodArticulo(entidad.getCodArticulo());
        c.setNomArticulo(entidad.getNomArticulo());
        c.setColor(entidad.getColor());
        c.setPeso(entidad.getPeso());
        c.setCapacidad(entidad.getCapacidad());
        return articuloDAO.save(c);
    }

    @Override
    public Articulo eliminar(Articulo entidad) {
        articuloDAO.deleteByCodArticulo(entidad.getCodArticulo());
        return entidad;
    }
}
