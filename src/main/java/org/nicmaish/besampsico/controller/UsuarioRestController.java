package org.nicmaish.besampsico.controller;

import lombok.RequiredArgsConstructor;
import org.nicmaish.besampsico.exception.BusinessExceptionCustom;
import org.nicmaish.besampsico.model.dto.usuario.DTOUsuarioCrearEditarRequest;
import org.nicmaish.besampsico.model.dto.usuario.DTOUsuarioEncontrado;
import org.nicmaish.besampsico.model.dto.usuario.DTOUsuarioSesion;
import org.nicmaish.besampsico.model.dto.usuario.DTOUsuarioListar;
import org.nicmaish.besampsico.service.interfaces.IUsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.nicmaish.besampsico.config.apiresponse.ApiResponseConfig.generarRespuesta;
import static org.nicmaish.besampsico.utils.Constantes.*;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioRestController {

    //==================================================================
    // Dependencias
    //==================================================================
    private final IUsuarioService service;

    //==================================================================
    // GET
    //==================================================================
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(
            @PathVariable Integer id
    ) {
        DTOUsuarioEncontrado usuario = service.buscarPorId(id);

        Map<String, String> message = new HashMap<>();
        message.put(SISTEMA, MENSAJE_CONSULTA_EXITOSA);
        return generarRespuesta(true, OK, message, usuario);
    }

    @GetMapping("/")
    public ResponseEntity<?> buscarPorCorreo(@RequestParam String correo) {
        DTOUsuarioSesion usuario = service.buscarPorCorreo(correo);

        Map<String, String> message = new HashMap<>();
        message.put(SISTEMA, MENSAJE_CONSULTA_EXITOSA);
        return generarRespuesta(true, OK, message, usuario);
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

        Page<DTOUsuarioListar> lista = service.listarPaginacion(pageable, filtros);

        Map<String, String> message = new HashMap<>();
        message.put(SISTEMA, MENSAJE_CONSULTA_EXITOSA);
        return generarRespuesta(true, OK, message, lista);
    }

    //==================================================================
    // POST
    //==================================================================
    @PostMapping("/")
    public ResponseEntity<Object> crear(
            @RequestBody DTOUsuarioCrearEditarRequest dto
    ) {
        //verificar que no existe otro usuario con el mismo DNI o correo
        DTOUsuarioEncontrado usuarioPorDni = service.buscarPorDni(dto.getDni());
        if (usuarioPorDni != null) {
            throw new BusinessExceptionCustom(ERROR, "Ya existe un usuario con el DNI ingresado");
        }

        DTOUsuarioSesion usuarioPorCorreo = service.buscarPorCorreo(dto.getCorreo());
        if (usuarioPorCorreo != null) {
            throw new BusinessExceptionCustom(ERROR, "Ya existe un usuario con el DNI ingresado");
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
            @RequestBody DTOUsuarioCrearEditarRequest dto
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
