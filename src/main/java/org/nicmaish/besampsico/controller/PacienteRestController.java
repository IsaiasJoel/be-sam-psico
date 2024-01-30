package org.nicmaish.besampsico.controller;

import lombok.RequiredArgsConstructor;
import org.nicmaish.besampsico.exception.BusinessExceptionCustom;
import org.nicmaish.besampsico.model.dto.paciente.DTOPacienteCrearEditarRequest;
import org.nicmaish.besampsico.model.dto.paciente.DTOPacienteEncontrado;
import org.nicmaish.besampsico.model.dto.paciente.DTOPacienteListar;
import org.nicmaish.besampsico.model.dto.usuario.DTOUsuarioCrearEditarRequest;
import org.nicmaish.besampsico.model.dto.usuario.DTOUsuarioEncontrado;
import org.nicmaish.besampsico.model.dto.usuario.DTOUsuarioListar;
import org.nicmaish.besampsico.model.dto.usuario.DTOUsuarioSesion;
import org.nicmaish.besampsico.service.interfaces.IPacienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.nicmaish.besampsico.config.apiresponse.ApiResponseConfig.generarRespuesta;
import static org.nicmaish.besampsico.utils.Constantes.*;
import static org.nicmaish.besampsico.utils.Constantes.MENSAJE_CONSULTA_EXITOSA;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/pacientes")
@RequiredArgsConstructor
public class PacienteRestController {
    //==================================================================
    // Dependencias
    //==================================================================
    private final IPacienteService service;

    //==================================================================
    // GET
    //==================================================================
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(
            @PathVariable Integer id
    ) {
        DTOPacienteEncontrado paciente = service.buscarPorId(id);

        Map<String, String> message = new HashMap<>();
        message.put(SISTEMA, MENSAJE_CONSULTA_EXITOSA);
        return generarRespuesta(true, OK, message, paciente);
    }

    @GetMapping("/page")
    public ResponseEntity<Object> listarPaginacion(
            @RequestParam(required = false) String filtroDni,
            @RequestParam(required = false) String filtroNombres,
            @RequestParam(required = false) String filtroEstado,
            @RequestParam Integer numeroPagina,
            @RequestParam Integer tamanioPagina
    ) {
        Pageable pageable = PageRequest.of(numeroPagina, tamanioPagina);

        Map<String, Object> filtros = new HashMap<>();
        filtros.put("filtroDni", filtroDni);
        filtros.put("filtroNombres", filtroNombres);
        filtros.put("filtroEstado", filtroEstado);

        Page<DTOPacienteListar> lista = service.listarPaginacion(pageable, filtros);

        Map<String, String> message = new HashMap<>();
        message.put(SISTEMA, MENSAJE_CONSULTA_EXITOSA);
        return generarRespuesta(true, OK, message, lista);
    }

    //==================================================================
    // POST
    //==================================================================
    @PostMapping("/")
    public ResponseEntity<Object> crear(
            @RequestBody DTOPacienteCrearEditarRequest dto
    ) {
        //verificar que no existe otro usuario con el mismo DNI o correo
        DTOPacienteEncontrado pacientePorDni = service.buscarPorDni(dto.getDni());
        if (pacientePorDni != null) {
            throw new BusinessExceptionCustom(ERROR, "Ya existe un paciente con el DNI ingresado");
        }

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
            @RequestBody DTOPacienteCrearEditarRequest dto
    ) {
        service.editar(id, dto);
        Map<String, String> message = new HashMap<>();
        message.put(SISTEMA, MENSAJE_CONSULTA_EXITOSA);
        return generarRespuesta(true, OK, message, "OK");
    }
}
