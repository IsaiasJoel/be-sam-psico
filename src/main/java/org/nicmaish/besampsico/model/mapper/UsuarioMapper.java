package org.nicmaish.besampsico.model.mapper;

import org.modelmapper.ModelMapper;
import org.nicmaish.besampsico.model.dto.usuario.DTOUsuarioCrearEditarRequest;
import org.nicmaish.besampsico.model.dto.usuario.DTOUsuarioEncontrado;
import org.nicmaish.besampsico.model.dto.usuario.DTOUsuarioListar;
import org.nicmaish.besampsico.model.dto.usuario.DTOUsuarioSesion;
import org.nicmaish.besampsico.model.entity.Usuario;

import java.util.List;

public class UsuarioMapper {

    private UsuarioMapper() {
    }

    private static final ModelMapper mapper = new ModelMapper();

    public static Usuario convertirDtoAEntity(DTOUsuarioCrearEditarRequest dto) {
        return mapper.map(dto, Usuario.class);
    }

    public static DTOUsuarioSesion convertirEntityADtoUsuarioSesion(Usuario usuario) {
        return mapper.map(usuario, DTOUsuarioSesion.class);
    }

    public static DTOUsuarioListar convertirEntityADtoUsuarioListar(Usuario entity) {
        return mapper.map(entity, DTOUsuarioListar.class);
    }

    public static List<DTOUsuarioListar> convertirListaEntityAListaDTOUsuarioListar(List<Usuario> listaEntity) {
        return listaEntity.stream().map(UsuarioMapper::convertirEntityADtoUsuarioListar).toList();
    }

    public static DTOUsuarioEncontrado convertirEntityADtoUsuarioEncontrado(Usuario entity) {
        return DTOUsuarioEncontrado.builder()
                .id(entity.getId())
                .apPaterno(entity.getApPaterno())
                .apMaterno(entity.getApMaterno())
                .nombres(entity.getNombres())
                .dni(entity.getDni())
                .fechaNacimiento(entity.getFechaNacimiento())
                .sexo(entity.getSexo())
                .celular(entity.getCelular())
                .nacionalidad(entity.getNacionalidad())
                .carrera(entity.getCarrera())
                .especialidad(entity.getEspecialidad())
                .universidad(entity.getUniversidad())
                .anioEgreso(entity.getAnioEgreso())
                .colegiado(entity.isColegiado())
                .numeroColegiatura(entity.getNumeroColegiatura())
                .resumenProfesional(entity.getResumenProfesional())
                .habilitado(entity.isHabilitado())
                .correo(entity.getCorreo())
                .build();
    }
}
