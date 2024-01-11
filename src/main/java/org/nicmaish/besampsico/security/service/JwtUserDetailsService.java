package org.nicmaish.besampsico.security.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.nicmaish.besampsico.model.entity.Usuario;
import org.nicmaish.besampsico.repo.IUsuarioRepo;
import org.nicmaish.besampsico.service.interfaces.IUsuarioService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final IUsuarioRepo repo;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repo.findByCorreo(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("usuario no encontrado");
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        usuario.getRoles().forEach(rol -> {
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        });
        return new org.springframework.security.core.userdetails.User(usuario.getCorreo(), usuario.getContrasenia(), roles);
    }
}
