package org.nicmaish.besampsico.controller;

import org.nicmaish.besampsico.service.interfaces.IVoluntarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/voluntarios")
public class VoluntarioRestController {

    @Autowired
    private IVoluntarioService service;
}
