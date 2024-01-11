package org.nicmaish.besampsico.security.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthServiceImpl {

    public boolean hasAccess(String path){
        boolean status;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        //TODO: no está implementado

        auth.getAuthorities().forEach(e -> log.info(e.getAuthority()));

        return true;
    }
}

