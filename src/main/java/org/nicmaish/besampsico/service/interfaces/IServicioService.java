package org.nicmaish.besampsico.service.interfaces;

import org.nicmaish.besampsico.model.dto.servicio.DTOServicioCrearEditarRequest;
import org.nicmaish.besampsico.model.dto.servicio.DTOServicioListar;
import org.nicmaish.besampsico.model.enums.OPCION_ELIMINAR;

import java.util.List;

public interface IServicioService {
    List<DTOServicioListar> listarTodos();

    void crear(DTOServicioCrearEditarRequest dto);

    void editar(Integer id, DTOServicioCrearEditarRequest dto);

    void habilitar(Integer id, OPCION_ELIMINAR tipo);
}
