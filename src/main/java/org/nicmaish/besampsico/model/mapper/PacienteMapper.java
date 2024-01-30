package org.nicmaish.besampsico.model.mapper;

import org.modelmapper.ModelMapper;
import org.nicmaish.besampsico.model.dto.paciente.DTOPacienteCrearEditarRequest;
import org.nicmaish.besampsico.model.dto.paciente.DTOPacienteEncontrado;
import org.nicmaish.besampsico.model.dto.paciente.DTOPacienteListar;
import org.nicmaish.besampsico.model.dto.usuario.DTOUsuarioCrearEditarRequest;
import org.nicmaish.besampsico.model.dto.usuario.DTOUsuarioEncontrado;
import org.nicmaish.besampsico.model.dto.usuario.DTOUsuarioListar;
import org.nicmaish.besampsico.model.dto.usuario.DTOUsuarioSesion;
import org.nicmaish.besampsico.model.entity.Paciente;
import org.nicmaish.besampsico.model.entity.Usuario;

import java.util.List;

public class PacienteMapper {

    private PacienteMapper() {
    }

    private static final ModelMapper mapper = new ModelMapper();

    public static Paciente convertirDtoAEntity(DTOPacienteCrearEditarRequest dto) {
        return mapper.map(dto, Paciente.class);
    }
//
//    public static DTOUsuarioSesion convertirEntityADtoUsuarioSesion(Usuario usuario) {
//        return mapper.map(usuario, DTOUsuarioSesion.class);
//    }

    public static DTOPacienteListar convertirEntityADtoPacienteListar(Paciente entity) {
        return mapper.map(entity, DTOPacienteListar.class);
    }

    public static List<DTOPacienteListar> convertirListaEntityAListaDTOUsuarioListar(List<Paciente> listaEntity) {
        return listaEntity.stream().map(PacienteMapper::convertirEntityADtoPacienteListar).toList();
    }

    public static DTOPacienteEncontrado convertirEntityADtoPacienteEncontrado(Paciente entity) {
        return mapper.map(entity, DTOPacienteEncontrado.class);
    }
}
