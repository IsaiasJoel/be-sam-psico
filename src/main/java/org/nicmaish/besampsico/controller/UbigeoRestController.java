package org.nicmaish.besampsico.controller;

import lombok.RequiredArgsConstructor;
import org.nicmaish.besampsico.model.dto.ubigeo.DTOBuscarUbigeo;
import org.nicmaish.besampsico.service.interfaces.IUbigeoService;
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
@RequestMapping("/ubigeos")
@RequiredArgsConstructor
public class UbigeoRestController {

    private final IUbigeoService service;

    @GetMapping("listarUbigeos")
    public ResponseEntity<?> listarUbigeos() {
        List<DTOBuscarUbigeo> lista = service.listarUbigeos();

        Map<String, String> message = new HashMap<>();
        message.put(SISTEMA, MENSAJE_CONSULTA_EXITOSA);
        return generarRespuesta(true, OK, message, lista);
    }

    @GetMapping("listarDepartamentos")
    public ResponseEntity<?> listarDepartamentos() {
        List<String> lista = service.listarDepartamentos();

        Map<String, String> message = new HashMap<>();
        message.put(SISTEMA, MENSAJE_CONSULTA_EXITOSA);
        return generarRespuesta(true, OK, message, lista);
    }

    @GetMapping("listarProvinciaPorDepartamento")
    public ResponseEntity<?> listarProvinciaPorDepartamento(
            @RequestParam String departamento
    ) {
        List<String> lista = service.listarProvinciaPorDepartamento(departamento);

        Map<String, String> message = new HashMap<>();
        message.put(SISTEMA, MENSAJE_CONSULTA_EXITOSA);
        return generarRespuesta(true, OK, message, lista);
    }

    @GetMapping("listarDistritoPorProvincia")
    public ResponseEntity<?> listarDistritoPorProvincia(
            @RequestParam String provincia
    ) {
        List<String> lista = service.listarDistritoPorProvincia(provincia);

        Map<String, String> message = new HashMap<>();
        message.put(SISTEMA, MENSAJE_CONSULTA_EXITOSA);
        return generarRespuesta(true, OK, message, lista);
    }

    @GetMapping("buscarUbigeoPorCodigo")
    public ResponseEntity<?> buscarUbigeo(
            @RequestParam String codigoUbigeo
    ) {
        DTOBuscarUbigeo ubigeo = service.buscarUbigeoPorCodigo(codigoUbigeo);

        Map<String, String> message = new HashMap<>();
        message.put(SISTEMA, MENSAJE_CONSULTA_EXITOSA);
        return generarRespuesta(true, OK, message, ubigeo);
    }

    @GetMapping(value = "/buscarCodigoUbigeoPorDepartamentoProvinciaDistrito")
    public ResponseEntity<?> buscarCodigoUbigeoPorDepartamentoProvinciaDistrito(
            @RequestParam String departamento,
            @RequestParam String provincia,
            @RequestParam String distrito
    ) {
        DTOBuscarUbigeo ubigeo = service.buscarCodigoUbigeoPorDepartamentoProvinciaDistrito(departamento, provincia, distrito);

        Map<String, String> message = new HashMap<>();
        message.put(SISTEMA, MENSAJE_CONSULTA_EXITOSA);
        return generarRespuesta(true, OK, message, ubigeo);
    }
}