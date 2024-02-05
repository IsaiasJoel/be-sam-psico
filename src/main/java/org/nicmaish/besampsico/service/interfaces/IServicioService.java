package org.nicmaish.besampsico.service.interfaces;

import org.nicmaish.besampsico.model.dto.servicio.DTOServicioCrearEditarRequest;
import org.nicmaish.besampsico.model.dto.servicio.DTOServicioListar;

import java.util.List;

public interface IServicioService {
    DTOServicioListar buscarPorId(Integer id);
    List<DTOServicioListar> listarTodos();
    void crear(DTOServicioCrearEditarRequest dto);
    void editar(Integer id, DTOServicioCrearEditarRequest dto);
    void habilitar(Integer id, String tipo);
}
