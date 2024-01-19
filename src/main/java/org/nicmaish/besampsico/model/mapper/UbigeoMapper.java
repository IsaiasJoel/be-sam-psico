package org.nicmaish.besampsico.model.mapper;

import org.modelmapper.ModelMapper;
import org.nicmaish.besampsico.model.dto.ubigeo.DTOBuscarUbigeo;
import org.nicmaish.besampsico.model.entity.Ubigeo;

public class UbigeoMapper {

    private UbigeoMapper() {
    }

    private static final ModelMapper mapper = new ModelMapper();

    public static DTOBuscarUbigeo covnertirEntityADto(Ubigeo entity) {
        return mapper.map(entity, DTOBuscarUbigeo.class);
    }
}
