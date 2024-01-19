package org.nicmaish.besampsico.model.dto.ubigeo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class DTOBuscarUbigeo {
    private String codigo;
    private String departamento;
    private String provincia;
    private String distrito;
}