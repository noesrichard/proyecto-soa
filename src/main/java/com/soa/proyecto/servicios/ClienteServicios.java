package com.soa.proyecto.servicios;

import com.soa.proyecto.dao.ClienteDAO;
import com.soa.proyecto.entidades.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClienteServicios implements Servicio<Cliente> {

    @Autowired
    private ClienteDAO clienteDAO;

    @Override
    public List<Cliente> get() {
        return clienteDAO.findAll();
    }

    @Override
    public Cliente get(Cliente entidad) {
        return null;
    }

    @Override
    public Cliente crear(Cliente entidad) {
        return null;
    }

    @Override
    public Cliente actualizar(Cliente entidad) {
        return null;
    }

    @Override
    public Cliente eliminar(Cliente entidad) {
        return null;
    }
}
