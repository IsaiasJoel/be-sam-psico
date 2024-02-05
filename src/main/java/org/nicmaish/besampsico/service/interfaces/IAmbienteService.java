package org.nicmaish.besampsico.service.interfaces;

import org.nicmaish.besampsico.model.dto.ambiente.DTOAmbienteCrearEditarRequest;
import org.nicmaish.besampsico.model.dto.ambiente.DTOAmbienteListar;

import java.util.List;

public interface IAmbienteService {
    DTOAmbienteListar buscarPorId(Integer id);
    List<DTOAmbienteListar> listarTodos();
    void crear(DTOAmbienteCrearEditarRequest dto);
    void editar(Integer id, DTOAmbienteCrearEditarRequest dto);
    void habilitar(Integer id, String tipo);
}
