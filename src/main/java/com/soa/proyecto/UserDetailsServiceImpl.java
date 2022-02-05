package com.soa.proyecto;

import com.soa.proyecto.dao.UsuarioDAO;
import com.soa.proyecto.servicios.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, Servicio<Usuario> {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public UserDetails loadUserByUsername(String username){
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public List<Usuario> get() {
        return null;
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