package org.nicmaish.besampsico.service.interfaces;

import org.nicmaish.besampsico.model.dto.usuario.DTOUsuarioCrearEditarRequest;
import org.nicmaish.besampsico.model.dto.usuario.DTOUsuarioListar;
import org.nicmaish.besampsico.model.dto.usuario.DTOUsuarioSesion;
import org.nicmaish.besampsico.model.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUsuarioService {
    Usuario buscarPorId(Integer id);
    DTOUsuarioSesion buscarPorCorreo(String correo);
    Integer crear(DTOUsuarioCrearEditarRequest dto);
    Page<DTOUsuarioListar> listarPaginacion(Pageable pageable, String filtro);
    void editar(Integer id, DTOUsuarioCrearEditarRequest dto);
    void habilitar(Integer id, String tipo);
}
