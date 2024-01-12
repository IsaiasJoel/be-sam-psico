package org.nicmaish.besampsico.service.interfaces;

import org.nicmaish.besampsico.model.dto.usuario.DTOUsuarioCrearEditarRequest;
import org.nicmaish.besampsico.model.dto.usuario.DTOUsuarioEncontrado;
import org.nicmaish.besampsico.model.dto.usuario.DTOUsuarioListar;
import org.nicmaish.besampsico.model.dto.usuario.DTOUsuarioSesion;
import org.nicmaish.besampsico.model.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IUsuarioService {
    DTOUsuarioEncontrado buscarPorId(Integer id);
    DTOUsuarioEncontrado buscarPorDni(String dni);

    DTOUsuarioSesion buscarPorCorreo(String correo);

    void crear(DTOUsuarioCrearEditarRequest dto);

    Page<DTOUsuarioListar> listarPaginacion(Pageable pageable, Map<String, Object> filtros);

    void editar(Integer id, DTOUsuarioCrearEditarRequest dto);

    void habilitar(Integer id, String tipo);
}
