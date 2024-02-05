package org.nicmaish.besampsico.controller;

import lombok.RequiredArgsConstructor;
import org.nicmaish.besampsico.model.dto.ambiente.DTOAmbienteCrearEditarRequest;
import org.nicmaish.besampsico.model.dto.ambiente.DTOAmbienteListar;
import org.nicmaish.besampsico.service.interfaces.IAmbienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.nicmaish.besampsico.config.apiresponse.ApiResponseConfig.generarRespuesta;
import static org.nicmaish.besampsico.utils.Constantes.MENSAJE_CONSULTA_EXITOSA;
import static org.nicmaish.besampsico.utils.Constantes.SISTEMA;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/ambientes")
@RequiredArgsConstructor
public class AmbienteRestController {
    //==================================================================
    // Dependencias
    //==================================================================
    private final IAmbienteService service;

    //==================================================================
    // GET
    //==================================================================
    @GetMapping("/")
    public ResponseEntity<?> listarTodos() {
        List<DTOAmbienteListar> lista = service.listarTodos();

        Map<String, String> message = new HashMap<>();
        message.put(SISTEMA, MENSAJE_CONSULTA_EXITOSA);
        return generarRespuesta(true, OK, message, lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(
            @PathVariable Integer id
    ) {
        DTOAmbienteListar objeto = service.buscarPorId(id);

        Map<String, String> message = new HashMap<>();
        message.put(SISTEMA, MENSAJE_CONSULTA_EXITOSA);
        return generarRespuesta(true, OK, message, objeto);
    }

    //==================================================================
    // POST
    //==================================================================
    @PostMapping("/")
    public ResponseEntity<Object> crear(
            @RequestBody DTOAmbienteCrearEditarRequest dto
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
            @RequestBody DTOAmbienteCrearEditarRequest dto
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
