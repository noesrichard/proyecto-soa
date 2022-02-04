package com.soa.proyecto.servicios;

import com.soa.proyecto.dao.UsuarioDAO;
import com.soa.proyecto.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UsuarioServicios implements Servicio<Usuario> {

    @Autowired
    private UsuarioDAO usuarioDAO;
    @Override
    public List<Usuario> get() {
        return usuarioDAO.findAll();
    }

    @Override
    public Usuario get(Usuario entidad) {
        return usuarioDAO.findByUsername(entidad.getUsername());
    }

    @Override
    public Usuario crear(Usuario entidad) {
        return null;
    }

    @Override
    public Usuario edit(Usuario entidad) {
        return null;
    }

    @Override
    public Usuario eliminar(Usuario entidad) {
        return null;
    }
}
