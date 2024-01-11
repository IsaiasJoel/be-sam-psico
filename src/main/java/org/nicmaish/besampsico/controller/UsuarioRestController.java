package org.nicmaish.besampsico.controller;

import lombok.RequiredArgsConstructor;
import org.nicmaish.besampsico.model.dto.usuario.DTOUsuarioCrearEditarRequest;
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
import static org.nicmaish.besampsico.utils.Constantes.MENSAJE_CONSULTA_EXITOSA;
import static org.nicmaish.besampsico.utils.Constantes.SISTEMA;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioRestController {

    private final IUsuarioService service;

    @GetMapping("/")
    public ResponseEntity<?> buscarPorCorreo(@RequestParam String correo){
        DTOUsuarioSesion usuario = service.buscarPorCorreo(correo);

        Map<String, String> message = new HashMap<>();
        message.put(SISTEMA, MENSAJE_CONSULTA_EXITOSA);
        return generarRespuesta(true, OK, message, usuario);
    }

    @GetMapping("/page")
    public ResponseEntity<Object> listarPaginacion(
            @RequestParam(required = false) String filtro,
            @RequestParam Integer numeroPagina,
            @RequestParam Integer tamanioPagina
    ) {
        Pageable pageable = PageRequest.of(numeroPagina,tamanioPagina);
        Page<DTOUsuarioListar> lista = service.listarPaginacion(pageable,filtro);

        Map<String, String> message = new HashMap<>();
        message.put(SISTEMA, MENSAJE_CONSULTA_EXITOSA);
        return generarRespuesta(true, OK, message, lista);
    }

    @PostMapping("/")
    public ResponseEntity<Object> crear(
            @RequestBody DTOUsuarioCrearEditarRequest dto
    ) {
        service.crear(dto);
        Map<String, String> message = new HashMap<>();
        message.put(SISTEMA, MENSAJE_CONSULTA_EXITOSA);
        return generarRespuesta(true, OK, message, "OK");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editar(
            @PathVariable Integer id,
            @RequestBody DTOUsuarioCrearEditarRequest dto
    ) {
        service.editar(id,dto);
        Map<String, String> message = new HashMap<>();
        message.put(SISTEMA, MENSAJE_CONSULTA_EXITOSA);
        return generarRespuesta(true, OK, message, "OK");
    }

    @PutMapping("/habilitar/{id}/")
    public ResponseEntity<Object> habilitar(
            @PathVariable Integer id,
            @RequestParam String tipo
    ) {
        service.habilitar(id,tipo);
        Map<String, String> message = new HashMap<>();
        message.put(SISTEMA, MENSAJE_CONSULTA_EXITOSA);
        return generarRespuesta(true, OK, message, "OK");
    }
}
