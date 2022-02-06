package com.soa.proyecto.servicios;

import com.soa.proyecto.dao.ArtPlantaDAO;
import com.soa.proyecto.entidades.ArtPlanta;
import com.soa.proyecto.entidades.Articulo;
import com.soa.proyecto.entidades.ArticuloExistencias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    public List<ArtPlanta> getByArticulo(Articulo articulo){
        return artPlantaDAO.findByArticulo(articulo);
    }

    public List<Articulo> getDistinctArticulo(Articulo articulo){
       return artPlantaDAO.findDistinctByArticulo(articulo);
    }

    public List<ArticuloExistencias> getDistinctArticuloExistencias(Articulo articulo){
        List<Object []> objects = artPlantaDAO.findDistinctByArticuloAndExistencias(articulo);
        List<ArticuloExistencias> articulos = new ArrayList<ArticuloExistencias>();
        for(Object [] o : objects) {
            if(o[0] != null && o[1] != null) {
                ArticuloExistencias a = new ArticuloExistencias();
                a.setArticulo((Articulo) o[0]);
                a.setExistencias((Long) o[1]);
                articulos.add(a);
            }
        }
        return articulos;
    }
}
