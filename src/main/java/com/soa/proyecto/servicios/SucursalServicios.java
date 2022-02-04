package com.soa.proyecto.servicios;

import com.soa.proyecto.dao.SucursalDAO;
import com.soa.proyecto.entidades.Sucursal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SucursalServicios implements Servicio<Sucursal>{

    @Autowired
    private SucursalDAO sucursalDAO;
    @Override
    public List<Sucursal> get() {
       return sucursalDAO.findAll();
    }

    @Override
    public Sucursal get(Sucursal entidad) {
        return sucursalDAO.findByCodSucursal(entidad.getCodSucursal());
    }

    @Override
    public Sucursal crear(Sucursal entidad) {
        return sucursalDAO.save(entidad);
    }

    @Override
    public Sucursal edit(Sucursal entidad) {
        Sucursal s = sucursalDAO.findByCodSucursal(entidad.getCodSucursal());
        s.setDireccion(entidad.getDireccion());
        s.setTelefono(entidad.getTelefono());
        s.setCiudad(entidad.getCiudad());
        sucursalDAO.save(s);
        return entidad;
    }

    @Override
    public Sucursal eliminar(Sucursal entidad) {
        sucursalDAO.deleteByCodSucursal(entidad.getCodSucursal());
        return entidad;
    }
}
