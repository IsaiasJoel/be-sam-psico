package org.nicmaish.besampsico.model.mapper;

import org.modelmapper.ModelMapper;
import org.nicmaish.besampsico.model.dto.servicio.DTOServicioCrearEditarRequest;
import org.nicmaish.besampsico.model.dto.servicio.DTOServicioListar;
import org.nicmaish.besampsico.model.entity.Servicio;

import java.util.List;

public class ServicioMapper {

    private ServicioMapper() {
    }

    private static final ModelMapper mapper = new ModelMapper();

    public static DTOServicioListar convertirEntityADtoUsuarioListar(Servicio entity) {
        return mapper.map(entity, DTOServicioListar.class);
    }

    public static Servicio convertirDTOAEntity(DTOServicioCrearEditarRequest dto) {
        return mapper.map(dto, Servicio.class);
    }

    public static List<DTOServicioListar> convertirListaEntityAListaDTOServicioListar(List<Servicio> listaEntity) {
        return listaEntity.stream().map(ServicioMapper::convertirEntityADtoUsuarioListar).toList();
    }
}
