package org.nicmaish.besampsico.controller;

import lombok.RequiredArgsConstructor;
import org.nicmaish.besampsico.model.dto.servicio.DTOServicioCrearEditarRequest;
import org.nicmaish.besampsico.model.dto.servicio.DTOServicioListar;
import org.nicmaish.besampsico.service.interfaces.IServicioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.nicmaish.besampsico.config.apiresponse.ApiResponseConfig.generarRespuesta;
import static org.nicmaish.besampsico.utils.Constantes.*;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/servicios")
@RequiredArgsConstructor
public class ServicioRestController {
    //==================================================================
    // Dependencias
    //==================================================================
    private final IServicioService service;

    //==================================================================
    // GET
    //==================================================================
    @GetMapping("/")
    public ResponseEntity<?> listarTodos() {
        List<DTOServicioListar> lista = service.listarTodos();

        Map<String, String> message = new HashMap<>();
        message.put(SISTEMA, MENSAJE_CONSULTA_EXITOSA);
        return generarRespuesta(true, OK, message, lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(
            @PathVariable Integer id
    ) {
        DTOServicioListar objeto = service.buscarPorId(id);

        Map<String, String> message = new HashMap<>();
        message.put(SISTEMA, MENSAJE_CONSULTA_EXITOSA);
        return generarRespuesta(true, OK, message, objeto);
    }

    //==================================================================
    // POST
    //==================================================================
    @PostMapping("/")
    public ResponseEntity<Object> crear(
            @RequestBody DTOServicioCrearEditarRequest dto
    ) {
        service.crear(dto);
        Map<String, String> message = new HashMap<>();
        message.put(SISTEMA, MENSAJE_CONSULTA_EXITOSA);
        return generarRespuesta(true, OK, message, "OK");
    }

    //==================================================================
    // PUT
    //==================================================================
    @PutMapping("/{id}")
    public ResponseEntity<Object> editar(
            @PathVariable Integer id,
            @RequestBody DTOServicioCrearEditarRequest dto
    ) {
        service.editar(id, dto);
        Map<String, String> message = new HashMap<>();
        message.put(SISTEMA, MENSAJE_CONSULTA_EXITOSA);
        return generarRespuesta(true, OK, message, "OK");
    }

    @PutMapping("/habilitar/{id}/")
    public ResponseEntity<Object> habilitar(
            @PathVariable Integer id,
            @RequestParam String tipo
    ) {
        service.habilitar(id, tipo);
        Map<String, String> message = new HashMap<>();
        message.put(SISTEMA, MENSAJE_CONSULTA_EXITOSA);
        return generarRespuesta(true, OK, message, "OK");
    }
}
