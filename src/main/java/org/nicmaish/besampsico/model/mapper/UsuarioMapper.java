package org.nicmaish.besampsico.model.mapper;

import org.modelmapper.ModelMapper;
import org.nicmaish.besampsico.model.dto.usuario.DTOUsuarioCrearEditarRequest;
import org.nicmaish.besampsico.model.dto.usuario.DTOUsuarioListar;
import org.nicmaish.besampsico.model.dto.usuario.DTOUsuarioSesion;
import org.nicmaish.besampsico.model.entity.Usuario;

import java.util.List;

public class UsuarioMapper {

    private static final ModelMapper mapper = new ModelMapper();

    public static Usuario convertirDtoAEntity(DTOUsuarioCrearEditarRequest dto) {
        return mapper.map(dto, Usuario.class);
    }

    public static DTOUsuarioSesion convertirEntityADtoUsuarioSesion(Usuario usuario) {
        return mapper.map(usuario, DTOUsuarioSesion.class);
    }

    private static DTOUsuarioListar convertirEntityADtoUsuarioListar(Usuario entity){
        return mapper.map(entity,DTOUsuarioListar.class);
    }

    public static List<DTOUsuarioListar> convertirListaEntityAListaDTOUsuarioListar(List<Usuario> listaEntity) {
        return listaEntity.stream().map(UsuarioMapper::convertirEntityADtoUsuarioListar).toList();
    }
}
