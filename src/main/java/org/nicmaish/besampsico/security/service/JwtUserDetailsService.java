package org.nicmaish.besampsico.security.service;

import lombok.RequiredArgsConstructor;

import org.nicmaish.besampsico.model.entity.Voluntario;
import org.nicmaish.besampsico.service.interfaces.IVoluntarioService;
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

    private final IVoluntarioService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //se puso null provisionalmente hasta encontrar la forma de cómo pasar los parámetros "responsable" e "ip"
        Voluntario voluntario = service.buscarPorCorreo(username);

        if (voluntario == null) {
            throw new UsernameNotFoundException("usuario no encontrado");
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        voluntario.getRoles().forEach(rol -> {
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        });
        return new org.springframework.security.core.userdetails.User(voluntario.getCorreo(), voluntario.getContrasenia(), roles);
    }
}
