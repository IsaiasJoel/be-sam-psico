package org.nicmaish.besampsico.model.mapper;

import org.modelmapper.ModelMapper;
import org.nicmaish.besampsico.model.dto.ambiente.DTOAmbienteCrearEditarRequest;
import org.nicmaish.besampsico.model.dto.ambiente.DTOAmbienteListar;
import org.nicmaish.besampsico.model.entity.Ambiente;

import java.util.List;

public class AmbienteMapper {

    private AmbienteMapper() {
    }

    private static final ModelMapper mapper = new ModelMapper();

    public static DTOAmbienteListar convertirEntityADtoUsuarioListar(Ambiente entity) {
        return mapper.map(entity, DTOAmbienteListar.class);
    }

    public static Ambiente convertirDTOAEntity(DTOAmbienteCrearEditarRequest dto) {
        return mapper.map(dto, Ambiente.class);
    }

    public static List<DTOAmbienteListar> convertirListaEntityAListaDTOAmbienteListar(List<Ambiente> listaEntity) {
        return listaEntity.stream().map(AmbienteMapper::convertirEntityADtoUsuarioListar).toList();
    }
}
