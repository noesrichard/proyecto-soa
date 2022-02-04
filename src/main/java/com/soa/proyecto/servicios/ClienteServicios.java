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
        return clienteDAO.findByCodCliente(entidad.getCodCliente());
    }

    @Override
    public Cliente crear(Cliente entidad) {
        return clienteDAO.save(entidad);
    }

    @Override
    public Cliente edit(Cliente entidad) {
        Cliente c = clienteDAO.findByCodCliente(entidad.getCodCliente());
        c.setNomCliente(entidad.getNomCliente());
        c.setLimiteCredito(entidad.getLimiteCredito());
        c.setSaldo(entidad.getSaldo());
        c.setPctDescuento(entidad.getPctDescuento());
        return clienteDAO.save(c);
    }

    @Override
    public Cliente eliminar(Cliente entidad) {
        clienteDAO.deleteByCodCliente(entidad.getCodCliente());
        return entidad;
    }
}
