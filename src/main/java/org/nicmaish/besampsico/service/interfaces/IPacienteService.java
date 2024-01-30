package org.nicmaish.besampsico.service.interfaces;

import org.nicmaish.besampsico.model.dto.paciente.DTOPacienteCrearEditarRequest;
import org.nicmaish.besampsico.model.dto.paciente.DTOPacienteEncontrado;
import org.nicmaish.besampsico.model.dto.paciente.DTOPacienteListar;
import org.nicmaish.besampsico.model.dto.usuario.DTOUsuarioListar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IPacienteService {

    Page<DTOPacienteListar> listarPaginacion(Pageable pageable, Map<String, Object> filtros);

    void editar(Integer id, DTOPacienteCrearEditarRequest dto);

    DTOPacienteEncontrado buscarPorDni(String dni);

    void crear(DTOPacienteCrearEditarRequest dto);

    DTOPacienteEncontrado buscarPorId(Integer id);
}
