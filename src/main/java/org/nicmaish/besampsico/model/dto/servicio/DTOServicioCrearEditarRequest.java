package org.nicmaish.besampsico.model.dto.servicio;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTOServicioCrearEditarRequest {
    private Integer id;
    private String descripcion;
    private Double costo;
    private boolean habilitado;
}